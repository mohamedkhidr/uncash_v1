package com.example.main.features.sendMoney.apiService;

import com.example.main.serviceModels.TransferEligabiltyResponse;
import com.example.main.serviceModels.TransferCheckParams;
import com.example.main.serviceModels.TransferParams;
import com.example.main.serviceModels.TransferResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiTransfer {
    @POST("feature/ta7weel/check_eligabilty")
    Call<TransferEligabiltyResponse> checkEligabilty(@Header("Authorization") String accessToken, @Body TransferCheckParams transferCheckParams);

    @POST("feature/ta7weel/transfer")
    Call<TransferResponse> transfer(@Header("Authorization") String accessToken, @Body TransferParams transferParams);
}