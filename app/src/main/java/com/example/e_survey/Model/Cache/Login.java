package com.example.e_survey.Model.Cache;

public class Login {
    private String username;
    private String password;

    public Login(){

    }

    public Login(String parseInt, String string) {
        this.username = parseInt;
        this.password = string;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
