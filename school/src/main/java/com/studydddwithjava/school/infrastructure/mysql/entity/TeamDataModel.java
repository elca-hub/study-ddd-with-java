package com.studydddwithjava.school.infrastructure.mysql.entity;

import com.studydddwithjava.school.domain.model.team.Team;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="teams")
public class TeamDataModel {
    @Id
    @Column
    public String id;

    @Column
    public String name;

    @Setter(AccessLevel.NONE)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teamId")
    private List<StudentTeamMembershipDataModel> studentTeamMembershipDataModelList = new ArrayList<>();

    public TeamDataModel() {}

    public TeamDataModel(Team team) {
        this.id = team.getId();
        this.name = team.getName().getName();
    }
}
