package com.studydddwithjava.school.application.shared;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageInfo {
    private String title;

    private List<Alert> alerts = new ArrayList<>();

    public PageInfo(String title) {
        setTitle(title);
    }

    public PageInfo(String title, List<Alert> alerts) {
        setTitle(title);
        this.alerts = alerts;
    }

    public void setTitle(String title) {
        this.title = String.format("%s | School Management", title);
    }

    public void addAlert(Alert alert) {
        if (alert.getMessage() == null) return;
        alerts.add(alert);
    }
}
