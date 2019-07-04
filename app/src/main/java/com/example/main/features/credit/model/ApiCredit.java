package com.example.main.features.credit.model;

import com.example.main.serviceModels.CreditInfo;
import com.example.main.serviceModels.Operation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiCredit {
    @GET("feature/enquiry/credit")
    Call<CreditInfo> getCredit(@Header("Authorization") String accessToken);
}