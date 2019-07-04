package com.example.main.resetPassword.passwordReset.httpConnection;

public interface Presenter {
    public void OnComplete();

    public void OnNotValidPassword();

    public void OnExpiredPhoneVerification();
}
