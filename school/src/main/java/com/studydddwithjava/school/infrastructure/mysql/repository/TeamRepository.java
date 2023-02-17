package com.studydddwithjava.school.infrastructure.mysql.repository;

import com.studydddwithjava.school.domain.model.teacher.Teacher;
import com.studydddwithjava.school.domain.model.team.Team;
import com.studydddwithjava.school.domain.model.team.GroupName;
import com.studydddwithjava.school.domain.model.team.ITeamRepository;
import com.studydddwithjava.school.infrastructure.mysql.context.TeamContext;
import com.studydddwithjava.school.infrastructure.mysql.context.UsingTeamContext;
import com.studydddwithjava.school.infrastructure.mysql.entity.TeamDataModel;
import com.studydddwithjava.school.infrastructure.mysql.entity.UsingTeamDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository("mysql-team")
public class TeamRepository implements ITeamRepository {
    @Autowired
    private TeamContext teamContext;

    @Autowired
    private UsingTeamContext usingTeamContext;
    @Override
    public Optional<Team> findByName(GroupName name) {
        Optional<TeamDataModel> opt = teamContext.findByName(name.getName());

        return opt.map(entity -> new Team(entity.id, new GroupName(entity.name)));
    }

    @Override
    public void save(Teacher teacher, Team team) {
        TeamDataModel teamDataModel = new TeamDataModel(team);
        UsingTeamDataModel usingTeamDataModel = new UsingTeamDataModel(teacher, team);

        teamContext.save(teamDataModel);
        usingTeamContext.save(usingTeamDataModel);
    }
}
