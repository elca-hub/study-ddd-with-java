package com.studydddwithjava.school.infrastructure.mysql.repository;

import com.studydddwithjava.school.domain.model.teacher.Teacher;
import com.studydddwithjava.school.domain.model.team.Team;
import com.studydddwithjava.school.domain.model.team.TeamName;
import com.studydddwithjava.school.domain.model.team.ITeamRepository;
import com.studydddwithjava.school.infrastructure.mysql.context.TeamContext;
import com.studydddwithjava.school.infrastructure.mysql.context.TeacherTeamMembership;
import com.studydddwithjava.school.infrastructure.mysql.entity.TeamDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("mysql-team")
public class TeamRepository implements ITeamRepository {
    @Autowired
    private TeamContext teamContext;

    @Autowired
    private TeacherTeamMembership teacherTeamMembership;
    @Override
    public Optional<Team> findByName(TeamName name) {
        Optional<TeamDataModel> opt = teamContext.findByName(name.getName());

        return opt.map(entity -> new Team(entity.id, new TeamName(entity.name)));
    }

    @Override
    public void save(Teacher teacher, Team team) {
        TeamDataModel teamDataModel = new TeamDataModel(team);
        com.studydddwithjava.school.infrastructure.mysql.entity.TeacherTeamMembership teacherTeamMembership = new com.studydddwithjava.school.infrastructure.mysql.entity.TeacherTeamMembership(teacher, team);

        teamContext.save(teamDataModel);
        teacherTeamMembership.save(teacherTeamMembership);
    }

    @Override
    public List<Team> findByTeacher(Teacher teacher) {
        List<com.studydddwithjava.school.infrastructure.mysql.entity.TeacherTeamMembership> usingModels = teacherTeamMembership.findByTeacherId(teacher.getId());

        List<Team> res = new java.util.ArrayList<>(List.of());

        for (com.studydddwithjava.school.infrastructure.mysql.entity.TeacherTeamMembership model : usingModels) {
            Optional<TeamDataModel> opt = teamContext.findById(model.teamId);

            if (opt.isPresent()) {
                TeamDataModel teamDataModel = opt.get();
                Team team = new Team(teamDataModel.id, new TeamName(teamDataModel.name));

                res.add(team);
            } else {
                throw new IllegalStateException("A teamId that exists in the intermediate table does not exist in the teams table.");
            }
        }

        return res;
    }
}
