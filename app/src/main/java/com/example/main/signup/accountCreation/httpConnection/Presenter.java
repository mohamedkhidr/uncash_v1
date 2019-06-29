package com.example.main.signup.accountCreation.httpConnection;

public interface Presenter {
    public void OnComplete(int userId , String role , String userName);
    public void OnPhoneExpired();
    public void OnTakenUserName();


}
