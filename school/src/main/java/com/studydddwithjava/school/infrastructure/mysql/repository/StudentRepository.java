package com.studydddwithjava.school.infrastructure.mysql.repository;

import com.studydddwithjava.school.domain.model.student.IStudentRepository;
import com.studydddwithjava.school.domain.model.student.Student;
import com.studydddwithjava.school.domain.model.teacher.Teacher;
import com.studydddwithjava.school.domain.model.teacher.TeacherPw;
import com.studydddwithjava.school.domain.model.team.Team;
import com.studydddwithjava.school.domain.model.user.UserName;
import com.studydddwithjava.school.infrastructure.mysql.context.StudentContext;
import com.studydddwithjava.school.infrastructure.mysql.context.StudentTeamMembershipContext;
import com.studydddwithjava.school.infrastructure.mysql.context.TeacherContext;
import com.studydddwithjava.school.infrastructure.mysql.entity.StudentDataModel;
import com.studydddwithjava.school.infrastructure.mysql.entity.StudentTeamMembershipDataModel;
import com.studydddwithjava.school.infrastructure.mysql.entity.TeacherDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
            Optional<StudentDataModel> optionalStudentDataModel =
                    studentContext.findById(studentTeamMembershipDataModel.studentId);

            if (optionalStudentDataModel.isPresent()) {
                StudentDataModel studentDataModel = optionalStudentDataModel.get();

                res.add(new Student(
                        studentDataModel.id,
                        new UserName(studentDataModel.firstname, studentDataModel.lastname),
                        studentDataModel.studentNumber,
                        team,
                        null
                ));
            } else {
                throw new IllegalStateException();
            }
        }

        return res;
    }
}
