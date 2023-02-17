package com.studydddwithjava.school.domain.model.student;

import com.studydddwithjava.school.domain.model.team.Team;
import com.studydddwithjava.school.domain.model.task.Task;
import com.studydddwithjava.school.domain.model.user.UserName;

import java.util.ArrayList;
import java.util.UUID;

public class Student {
    private final int MAX_TASK_COUNT = 10;

    private final String id;
    private UserName name;
    private Team team;
    private final ArrayList<Task> tasks = new ArrayList<Task>();

    public Student(String id, UserName name, Team team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }

    public Student(UserName name, Team team) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.team = team;
    }

    public void addTask(Task task) {
        if (this.tasks.size() >= this.MAX_TASK_COUNT) return;

        this.tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
