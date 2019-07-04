package com.example.main.resetPassword.codeVerificationReset.httpConnection;

import android.util.Log;

import com.example.main.resetPassword.codeVerificationReset.view.CodeVerificationReset;


public class ResetPresenterImpl implements  ResetPresenter{
    private CodeVerificationReset codeVerification;
    private VerifyResetCode verifyResetCode ;


    public ResetPresenterImpl(CodeVerificationReset codeVerification) {
        this.codeVerification = codeVerification;
    }

    public void VerifyCode(String code , String number , String accessToken) {
        // get app access token
        Log.v("msg" , "presenter signup");
        verifyResetCode = new VerifyResetCode(this, code , number , accessToken);
        verifyResetCode.exceute();

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

