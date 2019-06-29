package com.example.main.main.model.apiServices;

import com.example.main.serviceModels.AccessToken;
import com.example.main.serviceModels.AdminUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiAuthentication {
    @POST("signup/authentication")
    Call<AccessToken> getAccesToken(@Body AdminUser user);
}
