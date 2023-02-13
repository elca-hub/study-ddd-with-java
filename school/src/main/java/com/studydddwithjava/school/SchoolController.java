package com.studydddwithjava.school;

import com.studydddwithjava.school.application.teacher.TeacherApplicationService;
import com.studydddwithjava.school.application.teacher.TeacherData;
import com.studydddwithjava.school.infrastructure.security.LoginTeacherDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class SchoolController {
    @Autowired
    private TeacherApplicationService teacherApplicationService;

    @GetMapping("/")
    public String index(Model model) {
        Logger logger = LoggerFactory.getLogger(SchoolController.class);
        logger.info("test!");
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
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

        return "auth/home";
    }
}
