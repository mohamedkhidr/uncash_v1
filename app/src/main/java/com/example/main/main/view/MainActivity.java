package com.example.main.main.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.main.signup.phoneVerification.view.PhoneVerificationImpl;
import com.example.main.R;
import com.example.main.main.presenter.httpConnection.PresenterImpl;
import com.example.main.validator.Network;

public class MainActivity extends AppCompatActivity implements Iview {
    private PresenterImpl presenter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new PresenterImpl(this);
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
            presenter.signup();
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
}
