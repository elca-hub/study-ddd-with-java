package com.studydddwithjava.school.infrastructure.mysql.entity;

import com.studydddwithjava.school.domain.model.student.Student;import jakarta.persistence.*;

@Entity
@Table(name = "student_team_membership")
public class StudentTeamMembershipDataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(nullable = false, name = "student_id")
    public String studentId;

    @Column(nullable = false, name = "team_id")
    public String teamId;

    @Column(nullable = false, name = "student_number")
    public int studentNumber;

    public StudentTeamMembershipDataModel() {}

    public StudentTeamMembershipDataModel(Student student) {
        this.studentId = student.getId();
        this.teamId = student.getTeam().getId();
        this.studentNumber = student.getStudentNumber();
    }
}
