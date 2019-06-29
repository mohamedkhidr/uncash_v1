package com.example.main.main.model.apiServices;


import com.example.main.serviceModels.AccessToken;
import com.example.main.serviceModels.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAuthentication {
    @POST("login/authentication")
    Call<AccessToken> getAccesToken(@Body User user);
}
