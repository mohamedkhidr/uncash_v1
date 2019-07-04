package com.example.main.main.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.main.features.view.DashBoard;
import com.example.main.main.presenter.httpConnection.LoginPresenterImpl;
import com.example.main.main.presenter.httpConnection.ResetPresenterImpl;
import com.example.main.main.presenter.httpConnection.SignupPresenterImpl;
import com.example.main.resetPassword.phoneVerificationReset.view.PhoneVerificationResetImpl;
import com.example.main.signup.phoneVerification.view.PhoneVerificationImpl;
import com.example.main.R;

import com.example.main.validator.Login;
import com.example.main.validator.Network;
import com.example.main.validator.Signup;

public class MainActivity extends AppCompatActivity implements Iview {
    private SignupPresenterImpl signupPresenter;
    private LoginPresenterImpl  loginPresenter ;
    private ResetPresenterImpl resetPresenter ;
    private EditText phoneEditText ;
    private EditText passEditText ;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneEditText = (EditText) findViewById(R.id.phoneNumberText);
        passEditText = (EditText) findViewById(R.id.passText);
        signupPresenter = new SignupPresenterImpl(this);
        loginPresenter = new LoginPresenterImpl(this);
        resetPresenter = new ResetPresenterImpl(this);
    }

    public void OnSignup(View view) {
        Network network = new Network(this);
        boolean networkStatus = network.isNetworkConnected();
        if(networkStatus) {
            Log.v("msg", "onsignup");
            dialog = new ProgressDialog(this);
            dialog.setTitle("Loading");
            dialog.setMessage("Please wait ");
            dialog.show();
            signupPresenter.signup();
        }else{
            Toast.makeText(this , "Check network connectivity" , Toast.LENGTH_SHORT).show();
        }


    }

    public void OnSignin(View view) {
        Network network = new Network(this);
        boolean networkStatus = network.isNetworkConnected();
        Login login = new Login(phoneEditText , passEditText);
        String phoneValue = phoneEditText.getText().toString();
        String passwordValue = passEditText.getText().toString();
        if(networkStatus) {
            if(login.validate()) {
                Log.v("msg", "onsignip");
                dialog = new ProgressDialog(this);
                dialog.setTitle("Loading");
                dialog.setMessage("Please wait ");
                dialog.show();
                loginPresenter.signin(phoneValue , passwordValue);
            }
        }else{
            Toast.makeText(this , "Check network connectivity" , Toast.LENGTH_SHORT).show();
        }


    }



    @Override
    public void OnSignupAuthenticationComplete(String accessToken) {
        Intent intent = new Intent(this , PhoneVerificationImpl.class);
        intent.putExtra("accessToken" ,accessToken );
        dialog.dismiss();
        startActivity(intent);
    }

    @Override
    public void OnSigninAuthenticationComplete(String accessToken) {
        Intent intent = new Intent(this , DashBoard.class);
        intent.putExtra("accessToken" , accessToken);
        dialog.dismiss();
        startActivity(intent);
    }

    @Override
    public void OnResetAuthenticationComplete(String accessToken) {
        Intent intent = new Intent(this , PhoneVerificationResetImpl.class);
        Toast.makeText(this , ""+accessToken , Toast.LENGTH_SHORT).show();
        intent.putExtra("accessToken" ,accessToken );
        dialog.dismiss();
        startActivity(intent);
    }

    @Override
    public void showWrongCredentialsHint() {
        dialog.dismiss();
        Toast.makeText(this , "Invalid user name or password " , Toast.LENGTH_SHORT).show();
    }

    public void OnForgetPassword(View view) {
        Network network = new Network(this);
        boolean networkStatus = network.isNetworkConnected();
        if(networkStatus) {
            Log.v("msg", "onforget");
            dialog = new ProgressDialog(this);
            dialog.setTitle("Loading");
            dialog.setMessage("Please wait ");
            dialog.show();
            resetPresenter.reset();
        }else{
            Toast.makeText(this , "Check network connectivity" , Toast.LENGTH_SHORT).show();
        }
    }
}
