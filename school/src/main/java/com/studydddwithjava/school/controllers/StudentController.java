package com.studydddwithjava.school.controllers;

import com.studydddwithjava.school.application.shared.Alert;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            @AuthenticationPrincipal LoginTeacherDetails teacherDetails,
            @ModelAttribute Alert alert
    ) {
        List<TeamData> teams = teamApplicationService.findByTeacher(teacherDetails.getUsername());

        model.addAttribute("teams", teams);

        var pageInfo = new PageInfo("生徒の新規追加");

        pageInfo.addAlert(alert);

        model.addAttribute("pageInfo", pageInfo);

        return "/auth/student/new";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute @Validated StudentRegisterParam studentRegisterParam,
            BindingResult result,
            @AuthenticationPrincipal LoginTeacherDetails teacherDetails,
            RedirectAttributes redirectAttributes
    ) {
        var redirectUrl = "/auth/student/new";

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(new Alert(
                    "フォームの不備",
                    "フォームの入力内容に不備があります。",
                    Alert.alertColor.warning
            ));

            return "redirect:" + redirectUrl;
        }

        try {
            studentApplicationService.register(studentRegisterParam, teacherDetails.getUsername());
        } catch (Exception e) {
            e.printStackTrace();

            redirectAttributes.addFlashAttribute(new Alert(
                    "追加失敗",
                    "生徒の追加に失敗しました。内容を確認の上、もう一度お試しください.",
                    Alert.alertColor.danger
            ));
            return "redirect:" + redirectUrl;
        }

        return "redirect:/auth/";
    }
}
