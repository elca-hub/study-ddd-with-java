package com.studydddwithjava.school.infrastructure.mysql.repository;

import com.studydddwithjava.school.domain.model.student.IStudentRepository;
import com.studydddwithjava.school.domain.model.student.Student;
import com.studydddwithjava.school.domain.model.team.Team;
import com.studydddwithjava.school.domain.model.team.TeamName;
import com.studydddwithjava.school.domain.model.user.UserName;
import com.studydddwithjava.school.infrastructure.mysql.context.StudentContext;
import com.studydddwithjava.school.infrastructure.mysql.context.StudentTeamMembershipContext;
import com.studydddwithjava.school.infrastructure.mysql.context.TeacherContext;
import com.studydddwithjava.school.infrastructure.mysql.entity.StudentDataModel;
import com.studydddwithjava.school.infrastructure.mysql.entity.StudentTeamMembershipDataModel;
import com.studydddwithjava.school.infrastructure.mysql.entity.TeamDataModel;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("mysql-student")
public class StudentRepository implements IStudentRepository {
    @Autowired
    private StudentContext studentContext;

    @Autowired
    private StudentTeamMembershipContext studentTeamMembershipContext;

    @Autowired
    private TeacherContext teacherContext;
    @Override
    @Transactional
    public void save(Student student) {
        StudentDataModel studentDataModel = new StudentDataModel(student);

        studentContext.save(studentDataModel);

        StudentTeamMembershipDataModel studentTeamMembershipDataModel = new StudentTeamMembershipDataModel(student);

        studentTeamMembershipContext.save(studentTeamMembershipDataModel);
    }

    @Override
    public List<Student> findByTeamId(Team team) {
        List<StudentTeamMembershipDataModel> list = studentTeamMembershipContext.findByTeamId(team.getId());

        List<Student> res = new ArrayList<>();

        for (StudentTeamMembershipDataModel studentTeamMembershipDataModel : list) {
            /* 中間テーブル`student_team_membership`の`student_id`から`students`を検索 */
            Optional<StudentDataModel> optionalStudentDataModel =
                    studentContext.findById(studentTeamMembershipDataModel.studentId);

            /* 生徒モデルが存在した場合 */
            if (optionalStudentDataModel.isPresent()) {
                StudentDataModel studentDataModel = optionalStudentDataModel.get();

                res.add(new Student(
                        studentDataModel.id,
                        new UserName(studentDataModel.firstname, studentDataModel.lastname),
                        studentTeamMembershipDataModel.studentNumber,
                        team,
                        null
                ));
            } else {
                throw new IllegalStateException();
            }
        }

        return res;
    }

    @Override
    public boolean existsSameStudentNumberInTeam(Student student, Team team) {
        return studentTeamMembershipContext.existsByStudentNumberAndTeamId(student.getStudentNumber(), team.getId());
    }

    @Override
    public int fetchMaxStudentNumber(Team team) {
        Optional<Tuple> opt = studentTeamMembershipContext.findMaxStudentNumber(team.getId());

        return opt.map(res -> res.get("max", Integer.class)).orElse(0);
    }

    @Override
    public Optional<Student> findById(String studentId) {
        Optional<StudentDataModel> opt = studentContext.findById(studentId);

        return opt.map(res -> new Student(
                res.id,
                new UserName(res.firstname, res.lastname),
                -1,
                null,
                null
        ));
    }

    @Override
    public Optional<Student> findById(String studentId, String teamId) {
        var optStudentTeamMembership = studentTeamMembershipContext.findByStudentIdAndTeamId(studentId, teamId);

        return optStudentTeamMembership.map(studentTeamMembership -> {
            Optional<Student> optStudent = this.findById(studentId);
            if (optStudent.isEmpty()) throw new IllegalArgumentException();

            var student = optStudent.get();
            student.changeStudentNumber(studentTeamMembership.studentNumber);

            return student;
        });
    }

    @Override
    public List<Team> getJoinTeams(String studentId) {
        List<TeamDataModel> teamDataModels = studentTeamMembershipContext.findTeamByStudentId(studentId);

        List<Team> teams = new ArrayList<>();

        for (var column : teamDataModels) {
            teams.add(new Team(
                    column.id,
                    new TeamName(column.name)
            ));
        }

        return teams;
    }

    @Override
    @Transactional
    public void delete(String studentId) {
        studentTeamMembershipContext.deleteByStudentId(studentId);
        studentContext.deleteById(studentId);
    }

    @Override
    public List<Student> fetchStudents(List<String> studentIds) {
        List<StudentDataModel> studentDataModels = studentContext.findByIdIn(studentIds);

        return studentDataModels.stream().map(model -> new Student(
                model.id,
                new UserName(model.firstname, model.lastname),
                -1,
                null,
                null
        )).toList();
    }

    @Override
    public void joinTeam(Student student) {
        Optional<StudentTeamMembershipDataModel> optionalStudentTeamMembershipDataModel
                = studentTeamMembershipContext.findByStudentIdAndTeamId(student.getId(), student.getTeam().getId());

        if (optionalStudentTeamMembershipDataModel.isPresent()) return;

        var insertStudentTeamMembershipModel = new StudentTeamMembershipDataModel(student);

        studentTeamMembershipContext.save(insertStudentTeamMembershipModel);
    }
}
