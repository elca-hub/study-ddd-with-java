package com.studydddwithjava.school.controllers;

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
    @Qualifier("slf4j")
    private ILogger logger;

    @GetMapping("/new")
    public String newStudent (
            Model model,
            @AuthenticationPrincipal LoginTeacherDetails teacherDetails
    ) {
        List<TeamData> teams = teamApplicationService.findByTeacher(teacherDetails.getUsername());

        /* TODO: 出席番号をデフォルトでセットするための値の取得 */
        model.addAttribute("teams", teams);

        return "/auth/student/new";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute @Validated StudentRegisterParam studentRegisterParam,
            BindingResult result
    ) {
        if (result.hasErrors()) return "redirect:/auth/student/new?error";

        return "redirect:/auth/";
    }
}
