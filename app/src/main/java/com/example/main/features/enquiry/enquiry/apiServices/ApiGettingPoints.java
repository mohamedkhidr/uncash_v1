package com.example.main.features.enquiry.enquiry.apiServices;

import com.example.main.serviceModels.LocationPoint;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiGettingPoints {

    @GET("feature/getPoints")
    Call<List<LocationPoint>> getAllPoints(@Header("Authorization") String accessToken);
}
