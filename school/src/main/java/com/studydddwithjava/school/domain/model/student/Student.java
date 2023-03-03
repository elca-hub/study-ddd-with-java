package com.studydddwithjava.school.domain.model.student;

import com.studydddwithjava.school.domain.model.teacher.Teacher;
import com.studydddwithjava.school.domain.model.team.Team;
import com.studydddwithjava.school.domain.model.task.Task;
import com.studydddwithjava.school.domain.model.user.UserName;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Data
public class Student {
    private final int MAX_TASK_COUNT = 10;
    private final int STUDENT_MAX_NUMBER = 200;

    private final String id;
    private UserName name;
    @Nullable
    private Team team;
    private int studentNumber;
    @Nullable
    private Teacher teacher;
    private final ArrayList<Task> tasks = new ArrayList<Task>();

    public Student(String id, UserName name, int studentNumber, Team team, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.teacher = teacher;
        changeStudentNumber(studentNumber);
    }

    public Student(UserName name, int studentNumber, Team team, Teacher teacher) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.team = team;
        this.teacher = teacher;
        changeStudentNumber(studentNumber);
    }

    public void addTask(Task task) {
        if (this.tasks.size() >= this.MAX_TASK_COUNT) return;

        this.tasks.add(task);
    }

    public void changeStudentNumber(int studentNumber) {
        if (studentNumber < 0 || studentNumber > STUDENT_MAX_NUMBER) {
            throw new IllegalArgumentException("The student number has exceeded the limit.");
        }
        this.studentNumber = studentNumber;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Optional<Teacher> getTeacher() {
        return Optional.ofNullable(this.teacher);
    }
}
