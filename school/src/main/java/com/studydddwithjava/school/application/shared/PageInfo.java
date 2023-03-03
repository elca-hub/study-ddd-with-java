package com.studydddwithjava.school.application.shared;

public class PageInfo {
    private String title;

    public PageInfo(String title) {
        setTitle(title);
    }

    public void setTitle(String title) {
        this.title = String.format("%s | School Management", title);
    }

    public String getTitle() {
        return title;
    }
}
