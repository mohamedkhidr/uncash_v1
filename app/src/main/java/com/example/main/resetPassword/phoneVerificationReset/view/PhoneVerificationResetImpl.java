package com.example.main.resetPassword.phoneVerificationReset.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.main.R;
import com.example.main.main.view.MainActivity;
import com.example.main.resetPassword.codeVerificationReset.view.CodeVerificationResetImpl;
import com.example.main.resetPassword.phoneVerificationReset.httpConnection.PresenterImpl;
import com.example.main.signup.codeVerification.view.CodeVerificationImpl;
import com.example.main.signup.phoneVerification.view.PhoneVerification;
import com.example.main.validator.Network;
import com.example.main.validator.PhoneNumberField;

public class PhoneVerificationResetImpl extends AppCompatActivity implements PhoneVerificationReset {
    private EditText phoneEditText ;
    private String accessToken ;
    private com.example.main.resetPassword.phoneVerificationReset.httpConnection.PresenterImpl presenter;
    private ProgressDialog dialog;
    private String phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification2);
        Intent intent = getIntent();
        accessToken = intent.getStringExtra("accessToken");
        Toast.makeText(this,accessToken , Toast.LENGTH_SHORT).show();
        phoneEditText = (EditText) findViewById(R.id.phone_number_reset);
        presenter = new PresenterImpl(this);

    }



    public void OnVerify(View view) {
        Network network = new Network(this);
        if(network.isNetworkConnected()) {

            PhoneNumberField field = new PhoneNumberField(phoneEditText);
            if (field.validate()) {
                // verify phone
                phoneNumber = phoneEditText.getText().toString();
                dialog = new ProgressDialog(this);
                dialog.setTitle("Loading");
                dialog.setMessage("Please wait ");
                dialog.show();
                presenter.VerifyPhone(phoneNumber , accessToken);


            }
        }else{
            Toast.makeText(this, "Check Network Connectivity" , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnPhoneNumberSent() {
        Intent intent = new Intent(this , CodeVerificationResetImpl.class);
        intent.putExtra("phoneNumber" ,phoneNumber );
        Log.v("msg"  , phoneNumber);
        intent.putExtra("accessToken" ,accessToken );

        dialog.dismiss();
        startActivity(intent);
    }

    @Override
    public void goHome() {
        dialog.dismiss();
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showNotFoundPhoneHint() {
        dialog.dismiss();
        Toast.makeText(this , "this phone number is not found" , Toast.LENGTH_SHORT).show();
    }

}
