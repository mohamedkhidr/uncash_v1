package com.example.main.resetPassword.codeVerificationReset.httpConnection;

public interface ResetPresenter {
    public void OnComplete();

    public void OnTokenExpired();

    public void OnCodeExpired();

    public void OnWrongCode();
}
