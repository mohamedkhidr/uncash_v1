package com.example.main.serviceModels;

import com.google.gson.annotations.SerializedName;

public class NewPassword {
    @SerializedName("phone_number")
    private String phoneNumber ;
    private String password;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public NewPassword(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
