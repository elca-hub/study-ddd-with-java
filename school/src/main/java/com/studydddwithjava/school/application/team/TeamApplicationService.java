package com.studydddwithjava.school.application.team;

import com.studydddwithjava.school.domain.model.student.IStudentRepository;
import com.studydddwithjava.school.domain.model.student.Student;
import com.studydddwithjava.school.domain.model.teacher.ITeacherRepository;
import com.studydddwithjava.school.domain.model.teacher.Teacher;
import com.studydddwithjava.school.domain.model.team.ITeamRepository;
import com.studydddwithjava.school.domain.model.team.Team;
import com.studydddwithjava.school.domain.model.team.TeamName;
import com.studydddwithjava.school.domain.model.user.UserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamApplicationService {
    @Autowired
    @Qualifier("mysql-team")
    private ITeamRepository teamRepository;

    @Autowired
    @Qualifier("mysql-teacher")
    private ITeacherRepository teacherRepository;

    @Autowired
    @Qualifier("mysql-student")
    private IStudentRepository studentRepository;

    public String register(String groupName, String teacherName) throws IllegalArgumentException {
        Team team = new Team(new TeamName(groupName));
        Optional<Teacher> opt = teacherRepository.findByUserName(new UserName(teacherName));

        opt.ifPresent(teacher -> teamRepository.save(teacher, team));

        return team.getId();
    }

    public Optional<TeamData> findById(String teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);

        return optionalTeam.map(TeamData::new);
    }

    public List<TeamData> findByTeacher(String teacherName) {
        Optional<Teacher> opt = teacherRepository.findByUserName(new UserName(teacherName));

        if (opt.isEmpty()) {
            return List.of();
        } else {
            List<Team> teams = teamRepository.findByTeacher(opt.get());

            List<TeamData> data = new java.util.ArrayList<>(List.of());

            for (Team team : teams) data.add(new TeamData(team));

            return data;
        }
    }

    public void removeStudent(String teamId, String studentId, String teacherName) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Optional<Teacher> optionalTeacher = teacherRepository.findByUserName(new UserName(teacherName));
        Optional<Student> optionalStudent = studentRepository.findById(studentId, teamId);

        if (optionalTeam.isEmpty() || optionalTeacher.isEmpty() || optionalStudent.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Team team = optionalTeam.get();
        Teacher teacher = optionalTeacher.get();
        Student student = optionalStudent.get();

        student.setTeam(team);
        student.setTeacher(teacher);

        teamRepository.removeStudent(student);

        /* チームに所属していない生徒を削除 */
        if (studentRepository.getJoinTeams(studentId).size() == 0) studentRepository.delete(studentId);
    }

    public void delete(String teamId, String teacherName) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Optional<Teacher> optionalTeacher = teacherRepository.findByUserName(new UserName(teacherName));

        if (optionalTeam.isEmpty() || optionalTeacher.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Team team = optionalTeam.get();
        Teacher teacher = optionalTeacher.get();

        /* teacherモデルを使用して、teacherにteamの削除権限があるかをチェック */
        teamRepository.delete(team);
    }
}
