package com.example.main.main.presenter.httpConnection;

import android.util.Log;

import com.example.main.serviceModels.AccessToken;
import com.example.main.main.view.Iview;

public class PresenterImpl implements Presenter {
    private Iview iview;
    private ApiLogin apiLogin ;


    public PresenterImpl(Iview iview) {
        this.iview = iview;
    }

    public void signup() {
        // get app access token
        Log.v("msg" , "presenter signup");
        apiLogin = new ApiLogin(this);
        apiLogin.exceute();

    }

    @Override
    public void OnAccessTokenReceived(AccessToken accessToken) {
        String token = accessToken.getToken();
        iview.OnSignupAuthenticationComplete(token);
    }
}
