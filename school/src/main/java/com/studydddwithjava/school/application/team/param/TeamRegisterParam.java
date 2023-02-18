package com.studydddwithjava.school.application.team.param;

import jakarta.validation.constraints.NotBlank;

public class TeamRegisterParam {
    @NotBlank
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
