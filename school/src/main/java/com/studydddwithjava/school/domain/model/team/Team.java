package com.studydddwithjava.school.domain.model.team;

import java.util.UUID;

public class Team {
    private final String id;
    private TeamName name;

    public Team(String id, TeamName name) {
        this.id = id;
        this.name = name;
    }

    public Team(TeamName name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public TeamName getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
