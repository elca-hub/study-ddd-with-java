package com.studydddwithjava.school.application.team;

import com.studydddwithjava.school.domain.model.team.Team;
import com.studydddwithjava.school.domain.model.team.GroupName;
import com.studydddwithjava.school.domain.model.team.ITeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamApplicationService {
    @Autowired
    @Qualifier("mysql-team")
    private ITeamRepository groupRepository;

    @Transactional
    public void register(String groupName) {
        Team team = new Team(new GroupName(groupName));

        groupRepository.save(team);
    }
}
