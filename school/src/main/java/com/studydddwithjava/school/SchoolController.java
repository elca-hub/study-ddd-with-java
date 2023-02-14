package com.studydddwithjava.school;

import com.studydddwithjava.school.application.shared.ILogger;
import com.studydddwithjava.school.application.teacher.TeacherApplicationService;
import com.studydddwithjava.school.application.teacher.TeacherData;
import com.studydddwithjava.school.infrastructure.security.LoginTeacherDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

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

    @GetMapping("/auth/")
    public String AuthHome(
            @AuthenticationPrincipal LoginTeacherDetails teacherDetails,
            Model model
    ) {
        Optional<TeacherData> optTeacher = teacherApplicationService.findByUserName(teacherDetails.getUsername());

        if (optTeacher.isEmpty()) return "redirect:/login?error";

        TeacherData teacher = optTeacher.get();

        model.addAttribute("teacher", teacher);

        logger.info(String.format("Succeed login. Teacher name: %s", teacher.getUserName()));

        return "auth/home";
    }
}
