package com.example.main.serviceModels;

public class User {
    private String username;
    private String password;
    private int app_id;


    public User(String username, String password, int app_id) {
        this.username = username;
        this.password = password;
        this.app_id = app_id;
    }


    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAppId() {
        return app_id;
    }
}
