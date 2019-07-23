package com.epam.petersburg.ncr41.model;


public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int role;


    public User() {

    }


    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = 2;

    }

    public User(String firstName, String lastName, String email, String password, int role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(int userId, String firstName, String lastName, String email, String password, int role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getFirstName().equals(user.getFirstName()) &&
                getLastName().equals(user.getLastName()) &&
                getEmail().equals(user.getEmail()) &&
                getPassword().equals(user.getPassword()) &&
                getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 13;
        result = prime * result + ((email == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((firstName == null) ? 0 : getFirstName().hashCode());
        result = prime * result + ((lastName == null) ? 0 : getLastName().hashCode());
        result = prime * result + ((password == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((role == 0) ? 0 : getRole());
        return result;
    }

    @Override
    public String toString() { //without field password;
        return "User: " +
                "userId=" + userId +
                ", firstName=" + firstName +
                ", lastName='" + lastName +
                ", email='" + email +
                ", role='" + role;
    }
}
