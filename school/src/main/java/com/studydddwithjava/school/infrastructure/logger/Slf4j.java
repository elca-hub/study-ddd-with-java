package com.studydddwithjava.school.infrastructure.logger;

import com.studydddwithjava.school.controllers.SchoolController;
import com.studydddwithjava.school.application.shared.ILogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("slf4j")
public class Slf4j implements ILogger {
    private final Logger logger = LoggerFactory.getLogger(SchoolController.class);

    @Override
    public void info(String s) {
        logger.info(s);
    }
}
