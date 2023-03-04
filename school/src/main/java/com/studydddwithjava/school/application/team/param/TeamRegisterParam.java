package com.studydddwithjava.school.application.team.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class TeamRegisterParam {
    @NotBlank
    private String groupName;

    private List<String> students;
}
