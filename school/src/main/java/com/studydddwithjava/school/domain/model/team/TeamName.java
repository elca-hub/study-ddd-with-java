package com.studydddwithjava.school.domain.model.team;

public class TeamName {
    private final int MAX_GROUP_NAME_LEN = 60;

    private final String name;

    public TeamName(String name) {
        if (name.length() == 0 || name.length() > MAX_GROUP_NAME_LEN) {
            throw new IllegalArgumentException(String.format("Group name length must be between %d and %d characters.", 0, MAX_GROUP_NAME_LEN));
        }

        if (name.contains(" ")) {
            throw new IllegalArgumentException("Group name cannot contain spaces.");
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
