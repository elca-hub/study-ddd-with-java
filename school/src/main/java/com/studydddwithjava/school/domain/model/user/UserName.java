package com.studydddwithjava.school.domain.model.user;

public class UserName {
    private String firstName;
    private String lastName;

    private final int MIN_NAME_LEN = 1;
    private final int MAX_NAME_LEN = 20;

    public UserName(String firstName, String lastName) {
        ChangeFirstName(firstName);
        ChangeLastName(lastName);
    }

    public UserName(String fullName) {
        String[] nameArr = fullName.split(" ", 2); // フルネームで取得するので、firstnameとlastnameに分割
        ChangeFirstName(nameArr[0]);
        ChangeLastName(nameArr[1]);
    }

    public void ChangeFirstName(String firstName) {
        if (firstName.length() < MIN_NAME_LEN || firstName.length() > MAX_NAME_LEN) {
            throw new IllegalArgumentException(String.format("First name length must be between %d and %d characters.", MIN_NAME_LEN, MAX_NAME_LEN));
        }

        if (firstName.contains(" ")) throw new IllegalArgumentException("User name cannot contain spaces.");

        this.firstName = firstName;
    }

    public void ChangeLastName(String lastName) {
        if (lastName.length() < MIN_NAME_LEN || lastName.length() > MAX_NAME_LEN) {
            throw new IllegalArgumentException(String.format("Last name length must be between %d and %d characters.", MIN_NAME_LEN, MAX_NAME_LEN));
        }

        if (lastName.contains(" ")) throw new IllegalArgumentException("User name cannot contain spaces.");

        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof UserName test)) return false;

        if (!(this.getFullName().equals(test.getFullName()))) return false;

        return true;
    }

    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }
}
