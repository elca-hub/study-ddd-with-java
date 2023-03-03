package com.studydddwithjava.school.infrastructure.mysql.entity;

import com.studydddwithjava.school.domain.model.student.Student;
import com.studydddwithjava.school.domain.model.teacher.Teacher;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false, name = "teacher_id")
    public String teacherId;

    @Setter(AccessLevel.NONE)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private List<StudentTeamMembershipDataModel> studentTeamMembershipDataModelList = new ArrayList<>();

    public StudentDataModel() {}

    public StudentDataModel(Student student) {
        this.id = student.getId();
        this.firstname = student.getName().getFirstName();
        this.lastname = student.getName().getLastName();
        this.username = student.getName().getFullName();
        this.teacherId = student.getTeacher().map(Teacher::getId).orElse(null);
    }
}
