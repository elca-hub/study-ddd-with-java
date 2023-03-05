package com.studydddwithjava.school.application.shared;

import lombok.Data;

@Data
public class Alert {
    public enum alertColor {
        success,
        primary,
        warning,
        danger
    }

    private String message;
    private String title;
    private alertColor alertColor;

    private String iconName;

    public Alert(String title, String message, alertColor alertColor) {
        this.title = title;
        this.message = message;
        this.alertColor = alertColor;


        if (alertColor != null) {
            switch (alertColor) {
                case primary -> this.iconName="info-circle-fill";
                case success -> this.iconName="check-circle-fill";
                case danger, warning -> this.iconName="exclamation-triangle-fill";
            }
        } else {
            this.iconName = "megaphone-fill";
        }
    }
}
