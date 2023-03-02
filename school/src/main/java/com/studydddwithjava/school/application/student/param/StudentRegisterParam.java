package com.studydddwithjava.school.application.student.param;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class StudentRegisterParam {
    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    private String teamId;

    @Range(min = 1, max = 200)
    private int studentNumber;

    private boolean autoInc;
}
