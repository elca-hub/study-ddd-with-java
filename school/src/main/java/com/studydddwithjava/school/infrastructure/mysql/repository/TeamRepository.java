package com.studydddwithjava.school.infrastructure.mysql.repository;

import com.studydddwithjava.school.domain.model.team.Team;
import com.studydddwithjava.school.domain.model.team.GroupName;
import com.studydddwithjava.school.domain.model.team.ITeamRepository;
import com.studydddwithjava.school.infrastructure.mysql.context.TeamContext;
import com.studydddwithjava.school.infrastructure.mysql.entity.TeamDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository("mysql-team")
public class TeamRepository implements ITeamRepository {
    @Autowired
    private TeamContext teamContext;
    @Override
    public Optional<Team> findByName(GroupName name) {
        Optional<TeamDataModel> opt = teamContext.findByName(name.getName());

        return opt.map(entity -> new Team(entity.id, new GroupName(entity.name)));
    }

    @Override
    public void save(Team team) {
        TeamDataModel model = new TeamDataModel(team);

        teamContext.save(model);
    }
}
