package com.studydddwithjava.school.domain.model;

public class Teacher {
    private final int MIN_PW_LEN = 16;
    private final int MAX_PW_LEN = 32;

    private final String id;
    private UserName name;
    private String pw;

    public Teacher(String id, UserName name, String pw) {
        this.id = id;
        this.name = name;
        if (pw.length() < MIN_PW_LEN || pw.length() > MAX_PW_LEN) {
            throw new IllegalArgumentException(String.format("Password length must be between %d and %d characters.", MIN_PW_LEN, MAX_PW_LEN));
        }
        this.pw = pw;
    }

    public void call(Student student, Task... task) {
        student.addTask(task);
    }
}
