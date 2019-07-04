package com.example.main.serviceModels;

import com.google.gson.annotations.SerializedName;

public class TransferResponse {
    private String message ;
    @SerializedName("id")
    private int operationId ;

    public String getMessage() {
        return message;
    }

    public int getOperationId() {
        return operationId;
    }

    public TransferResponse(String message, int operationId) {
        this.message = message;
        this.operationId = operationId;
    }
}
