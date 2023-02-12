package com.studydddwithjava.school;

import com.studydddwithjava.school.application.teacher.TeacherApplicationService;
import com.studydddwithjava.school.domain.model.teacher.TeacherService;
import com.studydddwithjava.school.infrastructure.mysql.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/login")
    public String login(
            @RequestParam("teacherFirstName") String firstName,
            @RequestParam("teacherLastName") String lastName,
            @RequestParam("teacherPw") String pw
    ) {
        boolean res = teacherApplicationService.login(firstName, lastName, pw);

        System.out.println(res);

        return "index";
    }
}
