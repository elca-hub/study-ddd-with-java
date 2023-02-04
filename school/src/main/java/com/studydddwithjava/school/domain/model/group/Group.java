package com.studydddwithjava.school.domain.model.group;

import java.util.UUID;

public class Group {
    private final String id;
    private GroupName name;

    public Group(String id, GroupName name) {
        this.id = id;
        this.name = name;
    }

    public Group(GroupName name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
