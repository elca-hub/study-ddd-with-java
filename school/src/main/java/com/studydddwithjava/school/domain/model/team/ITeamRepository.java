package com.studydddwithjava.school.domain.model.team;

import com.studydddwithjava.school.domain.model.teacher.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeamRepository {
    Optional<Team> findByName(TeamName name);
    void save(Teacher teacher, Team team);

    List<Team> findByTeacher(Teacher teacher);
}
