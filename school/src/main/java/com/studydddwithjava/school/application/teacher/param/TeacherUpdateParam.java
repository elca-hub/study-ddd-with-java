package com.studydddwithjava.school.application.teacher.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TeacherUpdateParam {
    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    private String pw;

    @NotBlank
    private String beforePw;
}
