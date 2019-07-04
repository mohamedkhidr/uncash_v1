package com.example.main.main.model.apiServices;

import com.example.main.serviceModels.AccessToken;
import com.example.main.serviceModels.AdminUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiResetAuthentication {
    @POST("reset_password/authentication")
    Call<AccessToken> getAccesToken(@Body AdminUser user);
}
