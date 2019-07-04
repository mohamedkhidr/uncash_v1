package com.example.main.serviceModels;

import com.google.gson.annotations.SerializedName;

public class Operation {
    @SerializedName("source_username")
    private String source ;
    @SerializedName("destination_username")
    private String destination ;
    @SerializedName("provider_username")
    private String provider ;
    private  int amount ;
    private String time ;
    private int total;
    private int commission ;

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getProvider() {
        return provider;
    }

    public int getAmount() {
        return amount;
    }

    public String getTime() {
        return time;
    }

    public int getTotal() {
        return total;
    }

    public int getCommission() {
        return commission;
    }

    public Operation(String source, String destination, String provider, int amount, String time, int total, int commission) {
        this.source = source;
        this.destination = destination;
        this.provider = provider;
        this.amount = amount;
        this.time = time;
        this.total = total;
        this.commission = commission;
    }



}
