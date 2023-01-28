package com.studydddwithjava.school.domain.model;

public class Teacher {
    private String name;
    private String pw;

    public Teacher(String name, String pw) {
        this.name = name;
        this.pw = pw;
    }

    public void call(Student student, Task task) {
        student.addTask(task);
    }
}
