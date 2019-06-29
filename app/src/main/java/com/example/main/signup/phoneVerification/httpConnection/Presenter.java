package com.example.main.signup.phoneVerification.httpConnection;

public interface Presenter {
    public void OnComplete();
    public void OnTokenExpired();
    public void OnPhoneInUse();


}