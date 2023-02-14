package com.studydddwithjava.school.domain.model.teacher;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TeacherHashPw{
    private String hash;

    public TeacherHashPw(String hash) {
        this.hash = hash;
    }

    TeacherHashPw(TeacherPw pw) {
        this.hash = new BCryptPasswordEncoder().encode(pw.getPw());
    }

    public String getHash() {
        return hash;
    }
}
