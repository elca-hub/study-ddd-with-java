package com.studydddwithjava.school.domain.model.team;

import java.util.Optional;

public interface ITeamRepository {
    Optional<Team> findByName(GroupName name);
    void save(Team team);
}
