package com.studydddwithjava.school;

import com.studydddwithjava.school.domain.model.Student;
import com.studydddwithjava.school.domain.model.Task;
import com.studydddwithjava.school.domain.model.Teacher;
import com.studydddwithjava.school.domain.model.UserName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ModelTest {
    @Test
    @DisplayName("教員モデルを用いて生徒モデルにタスクを付与")
    void testTeacherControl() throws IllegalArgumentException {
        UserName teacherName = new UserName("Domain-Driven", "Design");
        Teacher teacher = new Teacher("teacher-id", teacherName, "passwordpassword");

        UserName studentName = new UserName("ado", "asmi");
        Student student = new Student("student-id", studentName, 3, 1);

        Task testTask = new Task("Come to 3F3", "I know you broke all window of the school. I'll talk with you.");

        teacher.call(student, testTask);

        assertThat(student.getTasks().get(0), is(testTask));
    }
}
