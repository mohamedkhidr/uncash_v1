package com.example.main.resetPassword.passwordReset.httpConnection;

import android.content.Context;
import android.util.Log;

import com.example.main.resetPassword.passwordReset.view.ResetPassword;
import com.example.main.signup.stroreCreation.httpConnection.NewStore;
import com.example.main.signup.stroreCreation.view.CreateStore;

public class PresenterImpl implements  Presenter {

    private ResetPassword resetPassword ;
    private Context context ;
    private String phoneNumber ;
    private String accessToken;
    private String password ;



    public PresenterImpl(ResetPassword resetPassword) {
        this.resetPassword = resetPassword;
    }

    public void resetPassword(Context context ,String password ,  String phoneNumber , String accessToken) {
        this.context = context;

        this.phoneNumber = phoneNumber;
        this.accessToken = accessToken;
        this.password = password;


        Log.v("msg" , "presenter signup");
        Reset reset = new Reset(this , context , password , phoneNumber , accessToken);
        reset.exceute();

    }

    @Override
    public void OnComplete() {
        resetPassword.OnSuccess();
    }

    @Override
    public void OnNotValidPassword() {
        resetPassword.showNotValidPasswordHint();
    }

    @Override
    public void OnExpiredPhoneVerification() {
        resetPassword.showExpiredPhoneHint();
    }
}
