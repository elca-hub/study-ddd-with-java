package com.studydddwithjava.school.application.teacher;

import com.studydddwithjava.school.domain.model.teacher.Teacher;

public class TeacherData {
    private String firstName;
    private String lastName;
    private String userName;

    public TeacherData(Teacher teacher) {
        this.firstName = teacher.getName().getFirstName();
        this.lastName = teacher.getName().getLastName();
        this.userName = teacher.getName().getFullName();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }
}
