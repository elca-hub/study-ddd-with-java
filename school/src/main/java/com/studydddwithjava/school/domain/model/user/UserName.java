package com.studydddwithjava.school.domain.model.user;

public class UserName {
    private final String firstName;
    private final String lastName;

    private final int MIN_NAME_LEN = 1;
    private final int MAX_NAME_LEN = 20;

    public UserName(String firstName, String lastName) {
        if (firstName.length() < MIN_NAME_LEN || firstName.length() > MAX_NAME_LEN) {
            throw new IllegalArgumentException(String.format("First name length must be between %d and %d characters.", MIN_NAME_LEN, MAX_NAME_LEN));
        }

        if (lastName.length() < MIN_NAME_LEN || lastName.length() > MAX_NAME_LEN) {
            throw new IllegalArgumentException(String.format("Last name length must be between %d and %d characters.", MIN_NAME_LEN, MAX_NAME_LEN));
        }

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }
}
