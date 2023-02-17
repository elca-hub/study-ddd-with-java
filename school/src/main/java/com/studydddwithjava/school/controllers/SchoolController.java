package com.studydddwithjava.school.controllers;

import com.studydddwithjava.school.application.shared.ILogger;
import com.studydddwithjava.school.application.teacher.TeacherApplicationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String pw,
            HttpServletRequest request
    ) {
        boolean isDone = teacherApplicationService.register(firstname, lastname, pw);
        if (isDone) {
            try {
                request.login(teacherApplicationService.fetchFullName(firstname, lastname), pw);
            } catch (ServletException e) {
                e.printStackTrace();
            }

            return "redirect:/auth/";
        }

        return "redirect:/signup?error";
    }
}
