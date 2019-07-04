package com.example.main.resetPassword.codeVerificationReset.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.main.R;
import com.example.main.main.view.MainActivity;
import com.example.main.resetPassword.codeVerificationReset.httpConnection.ResetPresenterImpl;
import com.example.main.resetPassword.passwordReset.view.ResetPasswordImpl;
import com.example.main.resetPassword.phoneVerificationReset.httpConnection.PresenterImpl;
import com.example.main.resetPassword.phoneVerificationReset.view.PhoneVerificationResetImpl;
import com.example.main.signup.accountCreation.view.CreateUserImpl;
import com.example.main.signup.phoneVerification.view.PhoneVerification;
import com.example.main.validator.CodeField;
import com.example.main.validator.Network;

public class CodeVerificationResetImpl extends AppCompatActivity implements CodeVerificationReset {

    private EditText codeEditText;
    private ProgressDialog dialog;
    private ResetPresenterImpl presenter;
    private String accessToken;
    private String phoneNumber;
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_verification_reset_impl);
        context = this;
        Intent intent = getIntent();
        accessToken = intent.getStringExtra("accessToken");
        phoneNumber = intent.getStringExtra("phoneNumber");
        Log.v("msg", "11111" + phoneNumber);
        codeEditText = (EditText) findViewById(R.id.code_field_reset);
        presenter = new ResetPresenterImpl(this);
    }

    public void OnSubmit(View view) {
        Network network = new Network(this);
        if (network.isNetworkConnected()) {

            CodeField field = new CodeField(codeEditText);
            if (field.validate()) {
                // verify phone
                String code = codeEditText.getText().toString();
                dialog = new ProgressDialog(this);
                dialog.setTitle("Loading");
                dialog.setMessage("Please wait ");
                dialog.show();
                presenter.VerifyCode(code, phoneNumber, accessToken);


            }
        } else {
            Toast.makeText(this, "Check Network Connectivity", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnCodeVerified() {
        Intent intent = new Intent(this, ResetPasswordImpl.class);
        intent.putExtra("phoneNumber", phoneNumber);
        intent.putExtra("accessToken", accessToken);
        dialog.dismiss();
        startActivity(intent);

    }

    @Override
    public void showCodeExpiredHint() {

        Toast.makeText(this, " Code Expired", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PhoneVerificationResetImpl.class);
        startActivity(intent);
    }

    @Override
    public void goHome() {
        dialog.dismiss();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showWrongCodeHint() {
        Toast.makeText(this, "Wrong Code ", Toast.LENGTH_SHORT).show();
    }

}
