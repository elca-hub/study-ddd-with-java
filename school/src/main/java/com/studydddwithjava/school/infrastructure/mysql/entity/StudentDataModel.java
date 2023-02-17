package com.studydddwithjava.school.infrastructure.mysql.entity;

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

    @Column(nullable = false, unique = true, name = "student_number")
    public int studentNumber;
}
