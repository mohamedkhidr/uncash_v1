package com.example.main.signup.accountCreation.httpConnection;


import android.content.Context;
import android.util.Log;

import com.example.main.signup.accountCreation.view.CreateUser;
import com.example.main.signup.codeVerification.httpConnection.VerifyCode;

public class PresenterImpl implements  Presenter{
    private CreateUser createUser ;
    private Context context ;
    private CreateAccount createAccount ;
    private String userName ;
    private String password ;
    private String role ;
    private String phoneNumber ;
    private String accessToken ;


    public PresenterImpl(CreateUser createUser) {
        this.createUser = createUser;
    }

    public void createAcc(Context context , String userName , String password , String role
            , String phoneNumber , String accessToken) {
        this.context = context;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.accessToken = accessToken;


        Log.v("msg" , "presenter signup");
        createAccount = new CreateAccount(this , context , userName , password , role
                , phoneNumber , accessToken);
        createAccount.exceute();

    }

    @Override
    public void OnComplete(int userId , String role , String userName) {
        createUser.showSuccessActivity(userId , role , userName);
    }

    @Override
    public void OnPhoneExpired() {
        createUser.goToVerifyPhone();
    }

    @Override
    public void OnTakenUserName() {
        createUser.showUserNameTakenHint();
    }
}
