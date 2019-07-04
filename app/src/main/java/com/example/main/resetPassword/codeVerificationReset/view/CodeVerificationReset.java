package com.example.main.resetPassword.codeVerificationReset.view;

public interface CodeVerificationReset {
    public void OnCodeVerified();
    public void showCodeExpiredHint();
    public void goHome();
    public void showWrongCodeHint();
}
