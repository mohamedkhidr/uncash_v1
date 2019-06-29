package com.example.main.serviceModels;

import com.google.gson.annotations.SerializedName;

public class NewUser {
    @SerializedName("username")
    private String userName ;
    private String password ;
    private String role ;
    @SerializedName("phone_number")
    private String phoneNumber ;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public NewUser(String userName, String password, String role, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }
}
