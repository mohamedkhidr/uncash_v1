package com.example.main.signup.accountCreation.view;

public interface CreateUser {
    public void showUserNameTakenHint();

    public void goToVerifyPhone();

    public void showSuccessActivity(int userId , String role, String userName);
}
