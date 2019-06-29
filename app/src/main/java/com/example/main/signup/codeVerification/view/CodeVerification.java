package com.example.main.signup.codeVerification.view;

public interface CodeVerification {
    public void OnCodeVerified();
    public void showCodeExpiredHint();
    public void goHome();
    public void showWrongCodeHint();
}
