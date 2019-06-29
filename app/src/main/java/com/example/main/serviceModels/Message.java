package com.example.main.serviceModels;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("msg")
    private String meassage ;

    public String getMeassage() {
        return meassage;
    }

    public Message(String meassage) {
        this.meassage = meassage;
    }
}
