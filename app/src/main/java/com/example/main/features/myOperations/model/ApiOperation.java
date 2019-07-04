package com.example.main.features.myOperations.model;

import com.example.main.serviceModels.Operation;
import com.example.main.serviceModels.TransferParams;
import com.example.main.serviceModels.TransferResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiOperation {
    @GET("feature/enquiry/operations_info")
    Call<List<Operation>> getOperation(@Header("Authorization") String accessToken);
}