package com.studydddwithjava.school.application.student.param;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class StudentRegisterParam {
    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    private String teamId;
    private int studentNumber;

    private boolean autoInc;
}
