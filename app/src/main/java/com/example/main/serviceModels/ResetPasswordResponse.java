package com.example.main.serviceModels;

import com.google.gson.annotations.SerializedName;

public class ResetPasswordResponse {
    @SerializedName("msg")
    private String meassage ;

    public String getMeassage() {
        return meassage;
    }



    public ResetPasswordResponse(String meassage) {
        this.meassage = meassage;

    }
}
