package com.studydddwithjava.school.infrastructure.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="teachers")
public class TeacherDataModel {
    @Id
    @Column
    public String id;

    @Column(nullable = false)
    public String pw;

    @Column(length = 25, nullable = false)
    public String firstname;

    @Column(length = 25, nullable = false)
    public String lastname;

    @Column(length = 50, nullable = false)
    public String username;
}
