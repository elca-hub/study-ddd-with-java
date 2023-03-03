package com.studydddwithjava.school.application.student;

import com.studydddwithjava.school.domain.model.student.Student;
import lombok.Data;

@Data
public class StudentData {
    private String firstname;
    private String lastname;
    private String username;

    private int studentNumber;
    public StudentData(Student student) {
        this.firstname = student.getName().getFirstName();
        this.lastname = student.getName().getLastName();
        this.username = student.getName().getFullName();
        this.studentNumber = student.getStudentNumber();
    }
}
