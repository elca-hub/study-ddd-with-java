package com.studydddwithjava.school.controllers;

import com.studydddwithjava.school.application.shared.Alert;
import com.studydddwithjava.school.application.shared.ILogger;
import com.studydddwithjava.school.application.shared.PageInfo;
import com.studydddwithjava.school.application.teacher.TeacherApplicationService;
import com.studydddwithjava.school.application.teacher.param.TeacherRegisterParam;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SchoolController {
    @Autowired
    private TeacherApplicationService teacherApplicationService;

    @Autowired
    @Qualifier("slf4j")
    private ILogger logger;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageInfo", new PageInfo("ホーム"));
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model, @ModelAttribute Alert alert) {
        var pageInfo = new PageInfo("ログイン");
        pageInfo.addAlert(alert);
        model.addAttribute("pageInfo", pageInfo);
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model, @ModelAttribute Alert alert) {
        var pageInfo = new PageInfo("新規登録");

        pageInfo.addAlert(alert);

        model.addAttribute("pageInfo", pageInfo);
        return "signup";
    }

    @PostMapping("/signup")
    public String register(
            @ModelAttribute @Validated TeacherRegisterParam teacherRegisterParam,
            BindingResult result,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(new Alert(
                    "フォーム内容のエラー",
                    "フォームに入力された値が正しくありません。",
                    Alert.alertColor.warning
            ));

            return "redirect:/signup?error";
        }

        try {
            teacherApplicationService.register(teacherRegisterParam.getFirstname(), teacherRegisterParam.getLastname(), teacherRegisterParam.getPw());
            try {
                request.login(teacherApplicationService.fetchFullName(teacherRegisterParam.getFirstname(), teacherRegisterParam.getLastname()), teacherRegisterParam.getPw());
            } catch (ServletException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute(new Alert(
                        "ログインエラー",
                        "自動的にログインしようとしましたが、ログインに失敗しました。",
                        Alert.alertColor.warning
                ));
                return "redirect:/login?error";
            }

            return "redirect:/auth/";
        } catch (Exception e) {
            e.printStackTrace();

            Alert alert;

            if (e instanceof IllegalArgumentException) {
                alert = new Alert(
                        "登録エラー",
                        "パスワードもしくは教員名が条件を満たしていません。条件を満たしてから再度お試しください。",
                        Alert.alertColor.danger
                );
            } else if (e instanceof IllegalStateException) {
                alert = new Alert(
                        "登録エラー",
                        "既に同姓同名の教員が登録されています。別名で登録してください。",
                        Alert.alertColor.danger
                );
            } else {
                alert = new Alert(
                        "登録エラー",
                        "登録中にエラーが発生しました。しばらくしてから再度お試しください。",
                        Alert.alertColor.danger
                );
            }

            redirectAttributes.addFlashAttribute(alert);

            return "redirect:/signup?error";
        }
    }
}
