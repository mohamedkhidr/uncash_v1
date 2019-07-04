package com.example.main.resetPassword.passwordReset.apiServices;

import com.example.main.serviceModels.NewPassword;
import com.example.main.serviceModels.ResetPasswordResponse;
import com.example.main.serviceModels.Store;
import com.example.main.serviceModels.StoreCreationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiResetPassword {
    @POST("reset_password/reset")
    Call<ResetPasswordResponse> resetMyPassword(@Header("Authorization") String accessToken, @Body NewPassword password);
}
