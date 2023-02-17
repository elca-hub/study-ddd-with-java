package com.studydddwithjava.school.infrastructure.mysql.entity;

import com.studydddwithjava.school.domain.model.team.Team;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="teams")
public class TeamDataModel {
    @Id
    @Column
    public String id;

    @Column
    public String name;

    public TeamDataModel() {}

    public TeamDataModel(Team team) {
        this.id = team.getId();
        this.name = team.getName().getName();
    }
}
