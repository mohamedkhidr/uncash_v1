package com.example.main.main.presenter.httpConnection;

import android.util.Log;

import com.example.main.main.view.Iview;
import com.example.main.serviceModels.AccessToken;

public class ResetPresenterImpl implements ResetPresenter{

    private Iview iview;
    private ResetLogin resetLogin ;


    public ResetPresenterImpl(Iview iview) {
        this.iview = iview;
    }

    public void reset() {
        // get app access token
        Log.v("msg" , "presenter reset");
        resetLogin = new ResetLogin(this);
        resetLogin.exceute();

    }

    @Override
    public void OnAccessTokenReceived(AccessToken accessToken) {
        String token = accessToken.getToken();
        iview.OnResetAuthenticationComplete(token);
    }
}
