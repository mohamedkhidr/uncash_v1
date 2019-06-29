package com.example.main.serviceModels;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("phone_number")
    private String phoneNumber;
    private String password;
    @SerializedName("app_id")
    private int appId;


    public User(String phoneNumber, String password, int appId) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.appId = appId;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public int getAppId() {
        return appId;
    }
}
