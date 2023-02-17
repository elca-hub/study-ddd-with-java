package com.studydddwithjava.school.application.team.param;

import jakarta.validation.constraints.NotBlank;

public class TeamParam {
    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    private String pw;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
