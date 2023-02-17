package com.studydddwithjava.school.application.team;

import com.studydddwithjava.school.domain.model.teacher.ITeacherRepository;
import com.studydddwithjava.school.domain.model.teacher.Teacher;
import com.studydddwithjava.school.domain.model.team.Team;
import com.studydddwithjava.school.domain.model.team.GroupName;
import com.studydddwithjava.school.domain.model.team.ITeamRepository;
import com.studydddwithjava.school.domain.model.user.UserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void register(String groupName, String teacherName) {
        Team team = new Team(new GroupName(groupName));
        Optional<Teacher> opt = teacherRepository.findByUserName(new UserName(teacherName));

        opt.ifPresent(teacher -> groupRepository.save(teacher, team));
    }
}
