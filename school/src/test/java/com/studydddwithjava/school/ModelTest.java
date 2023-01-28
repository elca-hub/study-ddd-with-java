package com.studydddwithjava.school;

import com.studydddwithjava.school.domain.model.Student;
import com.studydddwithjava.school.domain.model.Task;
import com.studydddwithjava.school.domain.model.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ModelTest {
    @Test
    @DisplayName("教員モデルを用いて生徒モデルにタスクを付与")
    void testTeacherControl() throws Exception {
        Teacher teacher = new Teacher("elca", "password");

        Student[] students = {
                new Student("test1", 3, 1),
                new Student("test2", 3, 2)
        };

        Task testTask = new Task("Come to 3F3", "I know you broke all window of the school. I'll talk with you.");

        teacher.call(students[0], testTask);

        assertThat(students[0].getTasks().get(0), is(testTask));
    }
}
