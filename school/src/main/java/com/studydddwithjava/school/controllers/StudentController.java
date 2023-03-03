package com.studydddwithjava.school.controllers;

import com.studydddwithjava.school.application.student.StudentApplicationService;
import com.studydddwithjava.school.application.student.param.StudentRegisterParam;
import com.studydddwithjava.school.application.team.TeamApplicationService;
import com.studydddwithjava.school.application.shared.ILogger;
import com.studydddwithjava.school.application.team.TeamData;
import com.studydddwithjava.school.infrastructure.security.LoginTeacherDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/auth/student")
public class StudentController {
    @Autowired
    private TeamApplicationService teamApplicationService;

    @Autowired
    private StudentApplicationService studentApplicationService;

    @Autowired
    @Qualifier("slf4j")
    private ILogger logger;

    @GetMapping("/new")
    public String newStudent (
            Model model,
            @AuthenticationPrincipal LoginTeacherDetails teacherDetails
    ) {
        List<TeamData> teams = teamApplicationService.findByTeacher(teacherDetails.getUsername());

        model.addAttribute("teams", teams);

        return "/auth/student/new";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute @Validated StudentRegisterParam studentRegisterParam,
            BindingResult result,
            @AuthenticationPrincipal LoginTeacherDetails teacherDetails
    ) {
        if (result.hasErrors()) return "redirect:/auth/student/new?error";

        try {
            studentApplicationService.register(studentRegisterParam, teacherDetails.getUsername());
        } catch (Exception e) {
            e.printStackTrace();

            return "redirect:/auth/student/new?error";
        }

        return "redirect:/auth/";
    }
}
