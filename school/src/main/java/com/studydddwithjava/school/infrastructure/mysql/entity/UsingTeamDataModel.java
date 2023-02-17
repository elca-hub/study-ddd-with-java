package com.studydddwithjava.school.infrastructure.mysql.entity;

import com.studydddwithjava.school.domain.model.teacher.Teacher;
import com.studydddwithjava.school.domain.model.team.Team;
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

    public UsingTeamDataModel() {}

    public UsingTeamDataModel(Teacher teacher, Team team) {
        this.teacherId = teacher.getId();
        this.teamId = team.getId();
    }
}
