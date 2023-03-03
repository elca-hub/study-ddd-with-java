package com.studydddwithjava.school.controllers;

import com.studydddwithjava.school.application.shared.PageInfo;
import com.studydddwithjava.school.application.team.TeamApplicationService;
import com.studydddwithjava.school.application.shared.ILogger;
import com.studydddwithjava.school.application.team.param.TeamRegisterParam;
import com.studydddwithjava.school.infrastructure.security.LoginTeacherDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("pageInfo", new PageInfo("チームの新規追加"));
        return "/auth/team/new";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute @Validated TeamRegisterParam teamRegisterParam,
            BindingResult result,
            @AuthenticationPrincipal LoginTeacherDetails teacherDetails
    ) {
        if (result.hasErrors()) return "redirect:/auth/team/new?error";
        try {
            teamApplicationService.register(teamRegisterParam.getGroupName(), teacherDetails.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/auth/team/new?error";
        }

        return "redirect:/auth/";
    }
}
