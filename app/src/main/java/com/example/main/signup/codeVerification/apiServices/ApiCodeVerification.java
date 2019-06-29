package com.example.main.signup.codeVerification.apiServices;

import com.example.main.serviceModels.Code;
import com.example.main.serviceModels.Message;
import com.example.main.serviceModels.PhoneNumber;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiCodeVerification {
    @POST("signup/verify_code")
    Call<Message> verifyCode(@Header("Authorization") String accessToken, @Body Code code);
}
