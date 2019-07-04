package com.example.main.resetPassword.passwordReset.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.main.R;
import com.example.main.main.view.SucceededOperation;
import com.example.main.resetPassword.passwordReset.httpConnection.Presenter;
import com.example.main.resetPassword.passwordReset.httpConnection.PresenterImpl;
import com.example.main.resetPassword.phoneVerificationReset.view.PhoneVerificationReset;
import com.example.main.signup.stroreCreation.Location.model.LocationSettingHandler;
import com.example.main.signup.stroreCreation.Location.model.PermissionHandler;
import com.example.main.signup.stroreCreation.view.CreateStoreImpl;
import com.example.main.validator.Network;
import com.example.main.validator.PasswordField;
import com.example.main.validator.PhoneNumberField;
import com.example.main.validator.UserField;

public class ResetPasswordImpl extends AppCompatActivity  implements ResetPassword{
    private EditText passwordEditText;
    private EditText password2EditText;
    private String phoneNumber ;
    private String password ;
    private String accessToken ;
    private ProgressDialog dialog;
    private com.example.main.resetPassword.passwordReset.httpConnection.PresenterImpl presenter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_impl2);
        presenter = new PresenterImpl(this);

        passwordEditText = (EditText) findViewById(R.id.passText);
        password2EditText = (EditText) findViewById(R.id.passText2);
        Intent intent =getIntent();
        accessToken = intent.getStringExtra("accessToken");
        phoneNumber = intent.getStringExtra("phoneNumber");


    }

    public void OnSubmit(View view) {

        Network network = new Network(this);
        if (network.isNetworkConnected()) {

            PasswordField passwordField = new PasswordField(passwordEditText);


            if (passwordField.validate() && passwordField.matchPassword(password2EditText)) {

                password = passwordEditText.getText().toString();
                presenter.resetPassword(this ,  password , phoneNumber , accessToken);
                        dialog = new ProgressDialog(this);
                        dialog.setTitle("Loading");
                        dialog.setMessage("Please wait ");
                        dialog.show();
                    }
                }
        else {

            Toast.makeText(this, "Check Network Connectivity", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void OnSuccess() {
        dialog.dismiss();
        Intent intent = new Intent(this , SucceededOperation.class);
        startActivity(intent);
    }

    @Override
    public void showNotValidPasswordHint() {
        dialog.dismiss();
        Toast.makeText(this , "Not valid password" , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showExpiredPhoneHint() {
        dialog.dismiss();
        Toast.makeText(this , "Expired phone verification" , Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this , PhoneVerificationReset.class);
        startActivity(intent);
    }

}




