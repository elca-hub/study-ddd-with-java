package com.studydddwithjava.school.controllers;

import com.studydddwithjava.school.application.shared.ILogger;
import com.studydddwithjava.school.application.teacher.TeacherApplicationService;
import com.studydddwithjava.school.application.teacher.TeacherData;
import com.studydddwithjava.school.application.team.TeamApplicationService;
import com.studydddwithjava.school.application.team.TeamData;
import com.studydddwithjava.school.infrastructure.security.LoginTeacherDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private TeacherApplicationService teacherApplicationService;

    @Autowired
    private TeamApplicationService teamApplicationService;

    @Autowired
    @Qualifier("slf4j")
    private ILogger logger;

    @GetMapping("/")
    public String index(
            @AuthenticationPrincipal LoginTeacherDetails teacherDetails,
            Model model
    ) {
        Optional<TeacherData> optTeacher = teacherApplicationService.findByUserName(teacherDetails.getUsername());

        if (optTeacher.isEmpty()) return "redirect:/login?error";

        TeacherData teacher = optTeacher.get();

        List<TeamData> teams = teamApplicationService.findByTeacher(teacher.getUserName());

        model.addAttribute("teacher", teacher);
        model.addAttribute("teams", teams);

        logger.info(String.format("Succeed login. Teacher name: %s", teacher.getUserName()));

        return "auth/home";
    }
}
