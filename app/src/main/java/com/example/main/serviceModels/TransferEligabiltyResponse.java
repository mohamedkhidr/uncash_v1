package com.example.main.serviceModels;

import com.google.gson.annotations.SerializedName;

public class TransferEligabiltyResponse {
    @SerializedName("sender_id")
    private int senderId;
    @SerializedName("d_id")
    private int receiverId;
    @SerializedName("provider_id")
    private int providerId;
    private int amount;
    private int total;
    private int commission;

    public TransferEligabiltyResponse(int senderId, int receiverId, int providerId, int amount, int total, int commission) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.providerId = providerId;
        this.amount = amount;
        this.total = total;
        this.commission = commission;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public int getProviderId() {
        return providerId;
    }

    public int getAmount() {
        return amount;
    }

    public int getTotal() {
        return total;
    }

    public int getCommission() {
        return commission;
    }
}


