package com.studydddwithjava.school.domain.model.team;

import java.util.UUID;

public class Team {
    private final String id;
    private GroupName name;

    public Team(String id, GroupName name) {
        this.id = id;
        this.name = name;
    }

    public Team(GroupName name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public GroupName getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
