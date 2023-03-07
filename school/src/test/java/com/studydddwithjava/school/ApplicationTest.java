package com.studydddwithjava.school;

import com.studydddwithjava.school.application.teacher.TeacherApplicationService;
import com.studydddwithjava.school.application.teacher.TeacherData;
import com.studydddwithjava.school.domain.model.student.IStudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@AutoConfigureMockMvc
@SpringBootTest(classes = SchoolApplication.class)
public class ApplicationTest {

    @Autowired
    private TeacherApplicationService teacherApplicationService;

    @Autowired
    @Qualifier("mysql-student")
    private IStudentRepository studentRepository;

    private final String firstname = "test";
    private final String lastname = "user";
    private final String rawPw = "thisistestpassword";

    @BeforeEach
    public void setup() {
        try {
            teacherApplicationService.register(firstname, lastname, rawPw);
        } catch (IllegalStateException e) {
            System.out.println("既にテストユーザが登録済みです");
        }
    }

    @Test
    @DisplayName("テストデータが存在するか")
    void testTeacherLogin() throws NoSuchElementException {
        String username = teacherApplicationService.fetchFullName(firstname, lastname);
        Optional<TeacherData> td = teacherApplicationService.findByUserName(username);

        assertThat(td.get().getUserName(), is(username));
    }

    @Test
    @DisplayName("異なるpwだと削除できないか")
    void testCannotDeleteWrongPw() {
        String wrongPw = "wrongpw!";

        boolean res = teacherApplicationService.delete(firstname, lastname, wrongPw);

        assertThat(res, is(false));
    }

    @AfterEach
    public void remove() {
        teacherApplicationService.delete(firstname, lastname, rawPw);
    }
}
