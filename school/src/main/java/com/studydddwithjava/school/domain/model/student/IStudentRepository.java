package com.studydddwithjava.school.domain.model.student;

import com.studydddwithjava.school.domain.model.team.Team;

import java.util.List;

public interface IStudentRepository {
    void save(Student student);

    List<Student> findByTeamId(Team team);
    List<Student> findByStudentNumber(int studentNumber);

    int fetchMaxStudentNumber();
}
