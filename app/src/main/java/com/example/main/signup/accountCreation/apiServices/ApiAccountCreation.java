package com.example.main.signup.accountCreation.apiServices;

import com.example.main.serviceModels.AccountCreationResponse;
import com.example.main.serviceModels.NewUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiAccountCreation {
    @POST("signup/create_account")
    Call<AccountCreationResponse> createNewAccount(@Header("Authorization") String accessToken, @Body NewUser user);
}
