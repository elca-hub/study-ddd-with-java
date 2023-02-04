package com.studydddwithjava.school.domain.model.group;

public class GroupName {
    private final int MAX_GROUP_NAME_LEN = 60;

    private final String name;

    public GroupName(String name) {
        if (name.length() == 0 || name.length() > MAX_GROUP_NAME_LEN) {
            throw new IllegalArgumentException(String.format("Password length must be between %d and %d characters.", 0, MAX_GROUP_NAME_LEN));
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
