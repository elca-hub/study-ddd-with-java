package com.studydddwithjava.school;

import com.studydddwithjava.school.application.teacher.TeacherApplicationService;
import com.studydddwithjava.school.domain.model.teacher.TeacherService;
import com.studydddwithjava.school.infrastructure.mysql.repository.TeacherRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@AutoConfigureMockMvc
@SpringBootTest(classes = SchoolApplication.class)
public class ApplicationTest {

    @Autowired
    private TeacherApplicationService teacherApplicationService;

    @Test
    @DisplayName("教員モデルがDB上に存在するか")
    void testTeacherLogin() throws IllegalArgumentException {
        assertThat(teacherApplicationService.login("test", "elca", "testtesttesttesttest"), is(false));
    }
}
