package com.example.main.serviceModels;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

public class LocationPoint {
    private String latitude;
    private String longitude;
    private int balance;
    @SerializedName("store_name")
    private String storeName;
    @SerializedName("store_phone")
    private String storePhone;

    //private String id;

    public LocationPoint(String latitude, String longtitude, int balance, String storeName, String storePhone) {
        this.latitude = latitude;
        this.longitude = longtitude;
        this.balance = balance;
        this.storeName = storeName;
        this.storePhone = storePhone;
        //this.id = id;
    }

    public double getLatitude() {
        return Double.parseDouble(latitude);
    }

    public double getLongitude() {
        return Double.parseDouble(longitude);
    }

    public int getBalance() {
        return balance;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public LatLng getCoordinates() {
        double latitudeValue = Double.parseDouble(latitude);
        double longitudeValue = Double.parseDouble(longitude);
        return new LatLng(latitudeValue, longitudeValue);
    }



    /*public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    */
}
