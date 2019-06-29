package com.example.main.serviceModels;

public class AccessToken {
    private String access_token ;

    public AccessToken(String token){
        this.access_token = token;
    }

    public String getToken() {
        return access_token;
    }
}
