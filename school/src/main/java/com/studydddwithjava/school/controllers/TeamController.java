package com.studydddwithjava.school.controllers;

import com.studydddwithjava.school.application.team.TeamApplicationService;
import com.studydddwithjava.school.application.shared.ILogger;
import com.studydddwithjava.school.infrastructure.security.LoginTeacherDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth/team")
public class TeamController {
    @Autowired
    private TeamApplicationService teamApplicationService;

    @Autowired
    @Qualifier("slf4j")
    private ILogger logger;

    @GetMapping("/new")
    public String newTeam(Model model) {
        return "/auth/team/new";
    }

    @PostMapping("/register")
    public String register(
            @AuthenticationPrincipal LoginTeacherDetails teacherDetails,
            @RequestParam String groupName
    ) {
        teamApplicationService.register(groupName, teacherDetails.getUsername());

        return "redirect:/auth/";
    }
}
