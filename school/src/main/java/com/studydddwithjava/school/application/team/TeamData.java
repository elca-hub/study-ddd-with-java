package com.studydddwithjava.school.application.team;

import com.studydddwithjava.school.domain.model.team.Team;

public class TeamData {
    private final String name;
    private final String id;

    public TeamData(Team team) {
        this.name = team.getName().getName();
        this.id = team.getId();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
