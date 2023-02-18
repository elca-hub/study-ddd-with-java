package com.studydddwithjava.school.infrastructure.mysql.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_team_membership")
public class StudentTeamMembershipDataModel {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(nullable = false, name = "student_id")
    public String studentId;

    @Column(nullable = false, name = "team_id")
    public String teamId;
}
