package com.studydddwithjava.school.application.student;

import com.studydddwithjava.school.domain.model.student.Student;
import lombok.Data;

@Data
public class StudentData {
    private String firstname;
    private String lastname;
    private String username;
    public StudentData(Student student) {
        this.firstname = student.getName().getFirstName();
        this.lastname = student.getName().getLastName();
        this.username = student.getName().getFullName();
    }
}
