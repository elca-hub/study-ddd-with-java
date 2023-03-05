package com.studydddwithjava.school.controllers;

import com.studydddwithjava.school.application.shared.ILogger;
import com.studydddwithjava.school.application.shared.PageInfo;
import com.studydddwithjava.school.application.student.StudentApplicationService;
import com.studydddwithjava.school.application.student.StudentData;
import com.studydddwithjava.school.application.student.param.StudentRegisterParam;
import com.studydddwithjava.school.application.team.TeamApplicationService;
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
import java.util.Optional;

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

    @GetMapping("/{studentId}")
    public String home(
            @PathVariable String studentId,
            Model model
    ) {
        Optional<StudentData> optionalStudentData = studentApplicationService.findById(studentId);

        model.addAttribute("isEmpty", optionalStudentData.isEmpty());

        var pageInfo = new PageInfo("");

        if (optionalStudentData.isPresent()) {
            StudentData student = optionalStudentData.get();

            model.addAttribute("student", student);

            List<TeamData> teams = teamApplicationService.findByStudentId(studentId);

            model.addAttribute("teams", teams);

            pageInfo.setTitle(student.getUsername() + "の情報");
        } else {
            pageInfo.setTitle("生徒情報が見つかりませんでした");
        }

        model.addAttribute("pageInfo", pageInfo);

        return "/auth/student/home";
    }

    @GetMapping("/new")
    public String newStudent (
            Model model,
            @AuthenticationPrincipal LoginTeacherDetails teacherDetails
    ) {
        List<TeamData> teams = teamApplicationService.findByTeacher(teacherDetails.getUsername());

        model.addAttribute("teams", teams);

        model.addAttribute("pageInfo", new PageInfo("生徒の新規追加"));

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
