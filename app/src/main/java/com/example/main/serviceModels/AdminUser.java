package com.example.main.serviceModels;

public class AdminUser {

    private String username ;
    private String password ;

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public AdminUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
