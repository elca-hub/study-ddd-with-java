package com.studydddwithjava.school.domain.model.task;

public class Task {
    private String title;
    private String content;
    private boolean isDone = false;

    public Task(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
