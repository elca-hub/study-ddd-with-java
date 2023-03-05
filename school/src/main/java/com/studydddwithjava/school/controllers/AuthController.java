package com.studydddwithjava.school.controllers;

import com.studydddwithjava.school.application.shared.Alert;
import com.studydddwithjava.school.application.shared.ILogger;
import com.studydddwithjava.school.application.shared.PageInfo;
import com.studydddwithjava.school.application.teacher.TeacherApplicationService;
import com.studydddwithjava.school.application.teacher.TeacherData;
import com.studydddwithjava.school.application.teacher.param.TeacherUpdateParam;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private TeacherApplicationService teacherApplicationService;

    @Autowired
    private TeamApplicationService teamApplicationService;

    @Autowired
    @Qualifier("slf4j")
    private ILogger logger;

    @GetMapping("/")
    public String index(
            @AuthenticationPrincipal LoginTeacherDetails teacherDetails,
            Model model,
            @ModelAttribute("alert") Alert alert
            ) {
        Optional<TeacherData> optTeacher = teacherApplicationService.findByUserName(teacherDetails.getUsername());

        if (optTeacher.isEmpty()) return "redirect:/login?error";

        TeacherData teacher = optTeacher.get();

        List<TeamData> teams = teamApplicationService.findByTeacher(teacher.getUserName());

        var pageInfo = new PageInfo("管理画面");

        pageInfo.addAlert(alert);

        model.addAttribute("teacher", teacher);
        model.addAttribute("teams", teams);
        model.addAttribute("pageInfo", pageInfo);

        logger.info(String.format("Succeed login. Teacher name: %s", teacher.getUserName()));

        return "auth/home";
    }

    @GetMapping("/update")
    public String update(
            @AuthenticationPrincipal LoginTeacherDetails loginTeacherDetails,
            Model model,
            @ModelAttribute("alert") Alert alert
    ) {
        Optional<TeacherData> optionalTeacherData = teacherApplicationService.findByUserName(loginTeacherDetails.getUsername());

        if (optionalTeacherData.isEmpty()) return "redirect:/login?error";

        model.addAttribute("teacher", optionalTeacherData.get());

        var pageInfo = new PageInfo("教員情報を更新");

        pageInfo.addAlert(alert);

        model.addAttribute("pageInfo", pageInfo);

        return "auth/update";
    }

    @PostMapping("/update")
    public String update(
            @ModelAttribute @Validated TeacherUpdateParam teacherUpdateParam,
            BindingResult result,
            @AuthenticationPrincipal LoginTeacherDetails loginTeacherDetails,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(new Alert(
                    "フォームの不備",
                    "フォームの入力内容に不備があります。",
                    Alert.alertColor.warning
            ));

            return "redirect:/auth/update";
        }

        try {
            teacherApplicationService.update(teacherUpdateParam, loginTeacherDetails);
        } catch (Exception e) {
            e.printStackTrace();

            redirectAttributes.addFlashAttribute(new Alert(
                    "更新失敗",
                    "更新中にエラーが発生しました。入力内容を確認し、再度お試しください。",
                    Alert.alertColor.danger
            ));

            return "redirect:/auth/update";
        }

        redirectAttributes.addFlashAttribute(new Alert(
                "更新完了",
                "データの更新が完了しました",
                Alert.alertColor.success
        ));

        return "redirect:/login";
    }
}
