package com.studydddwithjava.school.application.student.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FetchTeamMemberParam {
    private String teamId;
}
