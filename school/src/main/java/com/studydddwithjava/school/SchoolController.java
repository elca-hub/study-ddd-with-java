package com.studydddwithjava.school;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SchoolController {
    @GetMapping("/")
    public String index(Model model) {
        Logger logger = LoggerFactory.getLogger(SchoolController.class);
        logger.info("test!");
        return "index";
    }
}
