package com.studydddwithjava.school;

import com.studydddwithjava.school.domain.model.team.Team;
import com.studydddwithjava.school.domain.model.team.TeamName;
import com.studydddwithjava.school.domain.model.student.Student;
import com.studydddwithjava.school.domain.model.task.Task;
import com.studydddwithjava.school.domain.model.teacher.Teacher;
import com.studydddwithjava.school.domain.model.teacher.TeacherPw;
import com.studydddwithjava.school.domain.model.user.UserName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ModelTest {
    @Test
    @DisplayName("教員モデルを用いて生徒モデルにタスクを付与")
    void testTeacherControl() throws IllegalArgumentException {
        UserName teacherName = new UserName("Domain-Driven", "Design");
        TeacherPw tp = new TeacherPw("passwordpassword");
        Teacher teacher = new Teacher("teacher-id", teacherName, tp);

        TeamName teamName = new TeamName("test-group");
        Team team = new Team(teamName);

        UserName studentName = new UserName("ado", "asmi");
        Student student = new Student("student-id", studentName, 1, team, teacher);

        Task testTask = new Task("Come to 3F3", "I know you broke all window of the school. I'll talk with you.");

        teacher.addTasks(new Student[]{student}, new Task[]{testTask});

        assertThat(student.getTasks().get(0), is(testTask));
    }
}
