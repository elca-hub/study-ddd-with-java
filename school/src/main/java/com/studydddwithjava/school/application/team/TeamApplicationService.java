package com.studydddwithjava.school.application.team;

import com.studydddwithjava.school.domain.model.teacher.ITeacherRepository;
import com.studydddwithjava.school.domain.model.teacher.Teacher;
import com.studydddwithjava.school.domain.model.team.Team;
import com.studydddwithjava.school.domain.model.team.TeamName;
import com.studydddwithjava.school.domain.model.team.ITeamRepository;
import com.studydddwithjava.school.domain.model.user.UserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeamApplicationService {
    @Autowired
    @Qualifier("mysql-team")
    private ITeamRepository groupRepository;

    @Autowired
    @Qualifier("mysql-teacher")
    private ITeacherRepository teacherRepository;

    @Transactional
    public void register(String groupName, String teacherName) throws IllegalArgumentException {
        Team team = new Team(new TeamName(groupName));
        Optional<Teacher> opt = teacherRepository.findByUserName(new UserName(teacherName));

        opt.ifPresent(teacher -> groupRepository.save(teacher, team));
    }

    public List<TeamData> findByTeacher(String teacherName) {
        Optional<Teacher> opt = teacherRepository.findByUserName(new UserName(teacherName));

        if (opt.isEmpty()) {
            return List.of();
        } else {
            List<Team> teams = groupRepository.findByTeacher(opt.get());

            List<TeamData> data = new java.util.ArrayList<>(List.of());

            for (Team team : teams) data.add(new TeamData(team));

            return data;
        }
    }
}
