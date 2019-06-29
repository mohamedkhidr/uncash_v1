package com.example.main.signup.stroreCreation.apiServices;

import com.example.main.serviceModels.AccountCreationResponse;
import com.example.main.serviceModels.NewUser;
import com.example.main.serviceModels.Store;
import com.example.main.serviceModels.StoreCreationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiNewStore {
    @POST("signup/create_store")
    Call<StoreCreationResponse> createNewAccount(@Header("Authorization") String accessToken, @Body Store store);
}
