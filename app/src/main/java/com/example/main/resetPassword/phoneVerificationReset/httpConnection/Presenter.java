package com.example.main.resetPassword.phoneVerificationReset.httpConnection;

public interface Presenter {
    public void OnComplete();
    public void OnTokenExpired();
    public void OnPhoneInUse();


}
