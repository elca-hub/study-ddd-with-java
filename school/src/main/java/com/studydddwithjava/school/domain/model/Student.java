package com.studydddwithjava.school.domain.model;

import java.util.ArrayList;

public class Student {
    private final int MAX_TASK_COUNT = 10;

    private String name;
    private int year;
    private int classCode;
    private final ArrayList<Task> tasks = new ArrayList<Task>();

    public Student(String name, int year, int classCode) {
        this.name = name;
        this.year = year;
        this.classCode = classCode;
    }

    public void addTask(Task task) {
        if (this.tasks.size() >= this.MAX_TASK_COUNT) return;
        this.tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
