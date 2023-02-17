package com.studydddwithjava.school.controllers;

import com.studydddwithjava.school.application.team.TeamApplicationService;
import com.studydddwithjava.school.application.shared.ILogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public String index(Model model) {
        return "/auth/team/new";
    }

    @PostMapping("/register")
    public String index(
            @RequestParam String groupName
    ) {
        teamApplicationService.register(groupName);

        return "redirect:/auth/";
    }
}
