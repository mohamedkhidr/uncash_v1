package com.example.main.serviceModels;

import com.google.gson.annotations.SerializedName;

public class AccountCreationResponse {
    @SerializedName("msg")
    private String meassage ;
    private int id ;

    public String getMeassage() {
        return meassage;
    }

    public int getId() {
        return id;
    }

    public AccountCreationResponse(String meassage, int id) {
        this.meassage = meassage;
        this.id = id;
    }
}
