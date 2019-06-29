package com.example.main.signup.codeVerification.httpConnection;

import android.util.Log;

import com.example.main.signup.codeVerification.view.CodeVerification;
import com.example.main.signup.phoneVerification.httpConnection.VerifyPhone;
import com.example.main.signup.phoneVerification.view.PhoneVerification;

public class PresenterImpl implements  Presenter {

    private CodeVerification codeVerification;
    private VerifyCode verifyCode ;


    public PresenterImpl(CodeVerification codeVerification) {
        this.codeVerification = codeVerification;
    }

    public void VerifyCode(String code , String number , String accessToken) {
        // get app access token
        Log.v("msg" , "presenter signup");
        verifyCode = new VerifyCode(this, code , number , accessToken);
        verifyCode.exceute();

    }

    @Override
    public void OnComplete() {
        codeVerification.OnCodeVerified();
    }

    @Override
    public void OnTokenExpired() {
        codeVerification.goHome();
    }

    @Override
    public void OnCodeExpired() {
        codeVerification.showCodeExpiredHint();
    }

    @Override
    public void OnWrongCode() {

    }
}
