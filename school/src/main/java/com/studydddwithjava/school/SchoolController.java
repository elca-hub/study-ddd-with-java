package com.studydddwithjava.school;

import com.studydddwithjava.school.application.teacher.TeacherApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String AuthHome() {
        return "auth/home";
    }
}
