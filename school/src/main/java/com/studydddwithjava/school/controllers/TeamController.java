package com.studydddwithjava.school.controllers;

import com.studydddwithjava.school.application.shared.Alert;
import com.studydddwithjava.school.application.shared.ILogger;
import com.studydddwithjava.school.application.shared.PageInfo;
import com.studydddwithjava.school.application.student.StudentApplicationService;
import com.studydddwithjava.school.application.student.StudentData;
import com.studydddwithjava.school.application.student.param.FetchTeamMemberParam;
import com.studydddwithjava.school.application.teacher.TeacherApplicationService;
import com.studydddwithjava.school.application.team.TeamApplicationService;
import com.studydddwithjava.school.application.team.TeamData;
import com.studydddwithjava.school.application.team.param.TeamRegisterParam;
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
@RequestMapping("/auth/team")
public class TeamController {
    @Autowired
    private TeamApplicationService teamApplicationService;

    @Autowired
    private StudentApplicationService studentApplicationService;

    @Autowired
    private TeacherApplicationService teacherApplicationService;

    @Autowired
    @Qualifier("slf4j")
    private ILogger logger;

    @GetMapping("/{teamId}")
    public String home(@PathVariable String teamId, Model model) {
        Optional<TeamData> team = teamApplicationService.findById(teamId);

        model.addAttribute("isEmpty", team.isEmpty());
        if (team.isPresent()) {
            var param = new FetchTeamMemberParam();
            param.setTeamId(teamId);
            List<StudentData> students = teamApplicationService.fetchTeamMember(param);

            model.addAttribute("team", team.get());
            model.addAttribute("students", students);
            model.addAttribute("pageInfo", new PageInfo(team.get().getName()));
        } else {
            model.addAttribute("pageInfo", new PageInfo("見つかりませんでした"));
        }

        return "/auth/team/home";
    }

    @GetMapping("/new")
    public String newTeam(
            Model model,
            @AuthenticationPrincipal LoginTeacherDetails loginTeacherDetails
    ) {
        List<StudentData> students = teacherApplicationService.fetchStudent(loginTeacherDetails.getUsername());

        model.addAttribute("students", students);
        model.addAttribute("pageInfo", new PageInfo("チームの新規追加"));
        return "/auth/team/new";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute @Validated TeamRegisterParam teamRegisterParam,
            BindingResult result,
            @AuthenticationPrincipal LoginTeacherDetails teacherDetails,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) return "redirect:/auth/team/new?error";
        try {
            String teamId = teamApplicationService.register(teamRegisterParam.getGroupName(), teacherDetails.getUsername());

            if (teamRegisterParam.getStudents() != null) {
                teamApplicationService.joinStudents(teamId, teamRegisterParam.getStudents());
            }

            redirectAttributes.addFlashAttribute("alert", new Alert(
                    "チームの追加完了",
                    "チームの追加が完了しました",
                    Alert.alertColor.success
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/auth/team/new?error";
        }

        return "redirect:/auth/";
    }

    @PostMapping("/{teamId}/{studentId}/remove")
    public String remove(
            @PathVariable String teamId,
            @PathVariable String studentId,
            @AuthenticationPrincipal LoginTeacherDetails teacherDetails
    ) {
        try {
            teamApplicationService.removeStudent(teamId, studentId, teacherDetails.getUsername());
        } catch(Exception e) {
            e.printStackTrace();

            return String.format("redirect:/auth/team/%s?error", teamId);
        }

        return "redirect:/auth/team/" + teamId;
    }

    @PostMapping("/{teamId}/delete")
    public String deleteTeam(
            @PathVariable String teamId,
            @AuthenticationPrincipal LoginTeacherDetails teacherDetails
    ) {
        try {
            teamApplicationService.delete(teamId, teacherDetails.getUsername());
        } catch (Exception e) {
            e.printStackTrace();

            return String.format("redirect:/auth/team/%s?error", teamId);
        }

        return "redirect:/auth/";
    }
}
