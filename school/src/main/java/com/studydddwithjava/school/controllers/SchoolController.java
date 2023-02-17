package com.studydddwithjava.school.controllers;

import com.studydddwithjava.school.application.shared.ILogger;
import com.studydddwithjava.school.application.teacher.TeacherApplicationService;
import com.studydddwithjava.school.application.team.param.TeamParam;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SchoolController {
    @Autowired
    private TeacherApplicationService teacherApplicationService;

    @Autowired
    @Qualifier("slf4j")
    private ILogger logger;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String register(
            @ModelAttribute @Validated TeamParam teamParam,
            BindingResult result,
            HttpServletRequest request
    ) {
        if (result.hasErrors()) return "redirect:/signup?error";

        boolean isDone = teacherApplicationService.register(teamParam.getFirstname(), teamParam.getLastname(), teamParam.getPw());
        if (isDone) {
            try {
                request.login(teacherApplicationService.fetchFullName(teamParam.getFirstname(), teamParam.getLastname()), teamParam.getPw());
            } catch (ServletException e) {
                e.printStackTrace();
            }

            return "redirect:/auth/";
        }

        return "redirect:/signup?error";
    }
}
