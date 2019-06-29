package com.example.main.signup.codeVerification.httpConnection;

public interface Presenter {
    public void OnComplete();
    public void OnTokenExpired();
    public void OnCodeExpired();
    public void OnWrongCode();


}
