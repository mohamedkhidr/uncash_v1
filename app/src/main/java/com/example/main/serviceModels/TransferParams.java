package com.example.main.serviceModels;

import com.google.gson.annotations.SerializedName;

public class TransferParams {
    @SerializedName("receiver_id")
    private  int receiverId ;
    private int amount ;
    @SerializedName("provider_id")
    private int providerId ;
    @SerializedName("provider_commission")
    private int commission ;

    public int getReceiverId() {
        return receiverId;
    }

    public int getAmount() {
        return amount;
    }

    public int getProviderId() {
        return providerId;
    }

    public int getCommission() {
        return commission;
    }

    public TransferParams(int receiverId, int amount, int providerId, int commission) {
        this.receiverId = receiverId;
        this.amount = amount;
        this.providerId = providerId;
        this.commission = commission;
    }
}
