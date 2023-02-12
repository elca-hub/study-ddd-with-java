package com.studydddwithjava.school.domain.model.teacher;

public class TeacherPw {
    private final String pw;
    private final int MIN_PW_LEN = 16;
    private final int MAX_PW_LEN = 32;

    public TeacherPw(String pw) {
        if (pw.length() < MIN_PW_LEN || pw.length() > MAX_PW_LEN) {
            throw new IllegalArgumentException(String.format("Password length must be between %d and %d characters.", MIN_PW_LEN, MAX_PW_LEN));
        }

        this.pw = pw;
    }

    public String getPw() {
        return pw;
    }
}
