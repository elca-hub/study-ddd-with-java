package com.studydddwithjava.school.domain.model.student;

import com.studydddwithjava.school.domain.model.team.Team;

import java.util.List;

public interface IStudentRepository {
    void save(Student student);

    List<Student> findByTeamId(Team team);
    boolean existsSameStudentNumberInTeam(Student student, Team team);

    int fetchMaxStudentNumber(Team team);
}
