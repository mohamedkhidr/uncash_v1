package com.example.main.serviceModels;

public class CreditInfo {
    private int balance ;
    private int prebalnce ;

    public int getBalance() {
        return balance;
    }

    public int getPrebalnce() {
        return prebalnce;
    }

    public CreditInfo(int balance, int prebalnce) {
        this.balance = balance;
        this.prebalnce = prebalnce;
    }
}
