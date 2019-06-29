package com.example.main.main.presenter.httpConnection;

import android.util.Log;

import com.example.main.main.view.Iview;
import com.example.main.serviceModels.AccessToken;

public class LoginPresenterImpl implements  LoginPresenter {
    private Iview iview;
    private UserLogin userLogin ;
    private String phoneNumber;
    private String password ;


    public LoginPresenterImpl(Iview iview) {
        this.iview = iview;
    }

    public void signin(String phoneNumber , String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        // get app access token
        Log.v("msg" , "presenter signup");
        userLogin = new UserLogin(this , phoneNumber , password);
        userLogin.exceute();

    }

    @Override
    public void OnAccessTokenReceived(AccessToken accessToken) {
        String token = accessToken.getToken();
        iview.OnSigninAuthenticationComplete(token);
    }

    @Override
    public void OnWrongCredentials() {
        iview.showWrongCredentialsHint();
    }
}
