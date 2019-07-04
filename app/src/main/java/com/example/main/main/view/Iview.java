package com.example.main.main.view;

public interface Iview {
    public void OnSignupAuthenticationComplete(String accessToken);
    public void OnSigninAuthenticationComplete(String accessToken);
    public void  OnResetAuthenticationComplete(String accessToken);
    public void showWrongCredentialsHint();
}
