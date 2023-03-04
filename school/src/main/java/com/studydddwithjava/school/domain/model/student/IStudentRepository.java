package com.studydddwithjava.school.domain.model.student;

import com.studydddwithjava.school.domain.model.team.Team;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository {
    void save(Student student);

    List<Student> findByTeamId(Team team);
    boolean existsSameStudentNumberInTeam(Student student, Team team);

    int fetchMaxStudentNumber(Team team);

    Optional<Student> findById(String studentId);

    Optional<Student> findById(String studentId, String teamId);

    List<Team> getJoinTeams(String studentId);

    void delete(String studentId);

    List<Student> findAll();

    List<Student> fetchStudents(List<String> studentIds);

    void joinTeam(Student student);
}
