package com.example.main.serviceModels;

public class Code {
    private String phone_number;
    private int code_number ;

    public int getCode() {
        return code_number;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public Code(int code_number , String phoneNumber) {
        this.code_number = code_number;
        this.phone_number = phoneNumber;
    }
}
