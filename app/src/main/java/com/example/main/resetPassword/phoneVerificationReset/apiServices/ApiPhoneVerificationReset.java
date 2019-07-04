package com.example.main.resetPassword.phoneVerificationReset.apiServices;

import com.example.main.serviceModels.Message;
import com.example.main.serviceModels.PhoneNumber;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiPhoneVerificationReset {
    @POST("reset_password/verify_phone")
    Call<Message> verifyPhone(@Header("Authorization") String accessToken, @Body PhoneNumber number);
}