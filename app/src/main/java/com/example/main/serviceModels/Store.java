package com.example.main.serviceModels;

import com.google.gson.annotations.SerializedName;

public class Store {
    public String getUserName() {
        return userName;
    }

    @SerializedName("username")
    private String userName ;
    @SerializedName("store_name")
    private String storeNume;
    @SerializedName("store_phone")
    private String storePhone;
    private String latitude ;
    private String longitude;

    public String getStoreNume() {
        return storeNume;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public Store(String storeNume, String storePhone, String latitude, String longitude , String userName) {
        this.userName = userName;
        this.storeNume = storeNume;
        this.storePhone = storePhone;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
