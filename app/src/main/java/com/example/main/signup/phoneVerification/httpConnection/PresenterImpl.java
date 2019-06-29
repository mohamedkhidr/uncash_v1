package com.example.main.signup.phoneVerification.httpConnection;

import android.util.Log;

import com.example.main.signup.phoneVerification.view.PhoneVerification;

public class PresenterImpl implements Presenter {

    private PhoneVerification phoneVerification;
    private VerifyPhone verifyPhone ;


    public PresenterImpl(PhoneVerification phoneVerification) {
        this.phoneVerification = phoneVerification;
    }

    public void VerifyPhone(String number , String accessToken) {
        // get app access token
        Log.v("msg" , "presenter signup");
       verifyPhone = new VerifyPhone(this , number , accessToken);
       verifyPhone.exceute();

    }

    @Override
    public void OnComplete() {
        phoneVerification.OnPhoneNumberSent();
    }

    @Override
    public void OnTokenExpired() {
        phoneVerification.goHome();
    }

    @Override
    public void OnPhoneInUse() {
        phoneVerification.showUsedPhoneHint();
    }
}
