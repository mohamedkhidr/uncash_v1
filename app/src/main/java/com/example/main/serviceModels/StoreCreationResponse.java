package com.example.main.serviceModels;

import com.google.gson.annotations.SerializedName;

public class StoreCreationResponse {
    @SerializedName("msg")
    private String meassage ;

    public String getMeassage() {
        return meassage;
    }



    public StoreCreationResponse(String meassage) {
        this.meassage = meassage;

    }
}
