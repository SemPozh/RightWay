package com.example.rightway.Models;

public class User {
    private String login;
    private String email;
    private String password;
    private int level;
    private int points;

    public User() {}

    public User(String email, String password) {
        this.login = email;
        this.email = email;
        this.password = password;
        this.level = 0;
        this.points = 0;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getLevel() {
        return level;
    }

    public int getPoints() {
        return points;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
