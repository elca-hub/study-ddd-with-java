package com.studydddwithjava.school.infrastructure.mysql.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "using_teams")
public class UsingTeamDataModel {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(nullable = false, name = "teacher_id")
    public String teacherId;

    @Column(nullable = false, name = "team_id")
    public String teamId;
}
