package com.studydddwithjava.school.controllers;

import com.studydddwithjava.school.application.shared.ILogger;
import com.studydddwithjava.school.application.student.StudentData;
import com.studydddwithjava.school.application.student.param.FetchTeamMemberParam;
import com.studydddwithjava.school.application.team.TeamApplicationService;
import com.studydddwithjava.school.infrastructure.security.LoginTeacherDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private TeamApplicationService teamApplicationService;

    @Autowired
    @Qualifier("slf4j")
    private ILogger logger;

    @PostMapping("/team/fetch")
    public List<StudentData> fetchTeamMember(
            @RequestBody @Validated FetchTeamMemberParam fetchTeamMemberParam,
            BindingResult result,
            @AuthenticationPrincipal LoginTeacherDetails teacherDetails
    ) {
        if (result.hasErrors()) throw new IllegalArgumentException();
        logger.info("called api, fetch team member");

        return teamApplicationService.fetchTeamMember(fetchTeamMemberParam);
    }
}
