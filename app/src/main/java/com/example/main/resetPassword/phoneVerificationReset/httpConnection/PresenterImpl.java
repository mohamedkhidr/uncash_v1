package com.example.main.resetPassword.phoneVerificationReset.httpConnection;

import android.util.Log;


import com.example.main.resetPassword.phoneVerificationReset.view.PhoneVerificationReset;
import com.example.main.signup.phoneVerification.view.PhoneVerification;

public class PresenterImpl implements  Presenter {
    private PhoneVerificationReset phoneVerificationReset;
    private VerifyPhone verifyPhone ;


    public PresenterImpl(PhoneVerificationReset phoneVerificationReset) {
        this.phoneVerificationReset = phoneVerificationReset;
    }

    public void VerifyPhone(String number, String accessToken) {
        // get app access token
        Log.v("msg", "presenter signup");
        verifyPhone = new VerifyPhone(this, number, accessToken);
        verifyPhone.exceute();

    }

    @Override
    public void OnComplete() {
        phoneVerificationReset.OnPhoneNumberSent();
    }

    @Override
    public void OnTokenExpired() {
        phoneVerificationReset.goHome();
    }

    @Override
    public void OnPhoneInUse() {
        phoneVerificationReset.showNotFoundPhoneHint();
    }
}
