package com.studydddwithjava.school.infrastructure.mysql.entity;

import com.studydddwithjava.school.domain.model.teacher.Teacher;
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

    @Column(length = 100, nullable = false)
    public String username;

    public TeacherDataModel() {}
    public TeacherDataModel(Teacher teacher) {
        this.id = teacher.getId();
        this.pw = teacher.getHashPw().getHash();
        this.firstname = teacher.getName().getFirstName();
        this.lastname = teacher.getName().getLastName();
        this.username = teacher.getName().getFullName();
    }
}
