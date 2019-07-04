package com.example.main.features.sendMoney.apiService;

import com.example.main.serviceModels.TransferCheckParams;
import com.example.main.serviceModels.TransferEligabiltyResponse;
import com.example.main.serviceModels.UserName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiGetUser {

    @GET("login/getUser")
    Call<UserName> getUser(@Header("Authorization") String accessToken);
}
