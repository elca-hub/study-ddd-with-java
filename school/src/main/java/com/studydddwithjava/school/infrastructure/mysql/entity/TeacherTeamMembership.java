package com.studydddwithjava.school.infrastructure.mysql.entity;

import com.studydddwithjava.school.domain.model.teacher.Teacher;
import com.studydddwithjava.school.domain.model.team.Team;
import jakarta.persistence.*;

@Entity
@Table(name = "teacher_team_membership")
public class TeacherTeamMembership {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(nullable = false, name = "teacher_id")
    public String teacherId;

    @Column(nullable = false, name = "team_id")
    public String teamId;

    public TeacherTeamMembership() {}

    public TeacherTeamMembership(Teacher teacher, Team team) {
        this.teacherId = teacher.getId();
        this.teamId = team.getId();
    }
}
