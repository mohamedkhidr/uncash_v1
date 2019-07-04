package com.example.main.serviceModels;

import com.google.gson.annotations.SerializedName;

public class TransferCheckParams {
    @SerializedName("receiver_username")
    private  String receiverUsername;
    private int amount ;

    public TransferCheckParams(String receiverUsername, int amount) {
        this.receiverUsername = receiverUsername;
        this.amount = amount;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public int getAmount() {
        return amount;
    }
}
