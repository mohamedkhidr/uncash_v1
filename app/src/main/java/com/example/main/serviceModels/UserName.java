package com.example.main.serviceModels;

import com.google.gson.annotations.SerializedName;

public class UserName {
    @SerializedName("username")
    private String userName ;

    public String getUserName() {
        return userName;
    }

    public UserName(String userName) {
        this.userName = userName;
    }
}
