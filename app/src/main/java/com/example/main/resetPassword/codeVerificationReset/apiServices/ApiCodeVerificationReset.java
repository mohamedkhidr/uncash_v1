package com.example.main.resetPassword.codeVerificationReset.apiServices;

import com.example.main.serviceModels.Code;
import com.example.main.serviceModels.Message;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiCodeVerificationReset {
    @POST("reset_password/verify_code")
    Call<Message> verifyCode(@Header("Authorization") String accessToken, @Body Code code);
}

