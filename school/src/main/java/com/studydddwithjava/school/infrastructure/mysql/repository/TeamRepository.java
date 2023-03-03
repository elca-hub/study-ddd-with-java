package com.studydddwithjava.school.infrastructure.mysql.repository;

import com.studydddwithjava.school.domain.model.teacher.Teacher;
import com.studydddwithjava.school.domain.model.team.ITeamRepository;
import com.studydddwithjava.school.domain.model.team.Team;
import com.studydddwithjava.school.domain.model.team.TeamName;
import com.studydddwithjava.school.infrastructure.mysql.context.TeacherTeamMembershipContext;
import com.studydddwithjava.school.infrastructure.mysql.context.TeamContext;
import com.studydddwithjava.school.infrastructure.mysql.entity.TeacherTeamMembershipDataModel;
import com.studydddwithjava.school.infrastructure.mysql.entity.TeamDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository("mysql-team")
public class TeamRepository implements ITeamRepository {
    @Autowired
    private TeamContext teamContext;

    @Autowired
    private TeacherTeamMembershipContext teacherTeamMembershipContext;

    @Override
    @Transactional
    public void save(Teacher teacher, Team team) {
        TeamDataModel teamDataModel = new TeamDataModel(team);
        TeacherTeamMembershipDataModel teacherTeamMembership = new TeacherTeamMembershipDataModel(teacher, team);

        teamContext.save(teamDataModel);
        teacherTeamMembershipContext.save(teacherTeamMembership);
    }

    @Override
    public List<Team> findByTeacher(Teacher teacher) {
        List<TeacherTeamMembershipDataModel> usingModels = teacherTeamMembershipContext.findByTeacherId(teacher.getId());

        List<Team> res = new java.util.ArrayList<>(List.of());

        for (TeacherTeamMembershipDataModel model : usingModels) {
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

    @Override
    public Optional<Team> findById(String id) {
        Optional<TeamDataModel> opt = teamContext.findById(id);
        return opt.map(model -> new Team(model.id, new TeamName(model.name)));
    }
}
