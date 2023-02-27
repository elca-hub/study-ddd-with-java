package com.studydddwithjava.school.infrastructure.mysql.entity;

import com.studydddwithjava.school.domain.model.student.Student;
import com.studydddwithjava.school.domain.model.teacher.Teacher;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="students")
public class StudentDataModel {
    @Id
    @Column
    public String id;

    @Column(length = 25, nullable = false)
    public String firstname;

    @Column(length = 25, nullable = false)
    public String lastname;

    @Column(length = 100, nullable = false)
    public String username;

    @Column(nullable = false, name = "student_number")
    public int studentNumber;

    @Column(nullable = false, name = "teacher_id")
    public String teacherId;

    public StudentDataModel() {}

    public StudentDataModel(Student student) {
        this.id = student.getId();
        this.studentNumber = student.getStudentNumber();
        this.firstname = student.getName().getFirstName();
        this.lastname = student.getName().getLastName();
        this.username = student.getName().getFullName();
        this.teacherId = student.getTeacher().map(Teacher::getId).orElse(null);
    }
}
