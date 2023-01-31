package com.studydddwithjava.school.domain.model;

public class UserName {
    private final String firstName;
    private final String lastName;

    public UserName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }
}
