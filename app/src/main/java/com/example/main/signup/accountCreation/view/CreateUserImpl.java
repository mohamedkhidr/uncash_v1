package com.example.main.signup.accountCreation.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.main.R;
import com.example.main.main.view.SucceededOperation;
import com.example.main.signup.stroreCreation.view.CreateStoreImpl;
import com.example.main.signup.accountCreation.httpConnection.PresenterImpl;
import com.example.main.signup.phoneVerification.view.PhoneVerification;
import com.example.main.validator.Network;
import com.example.main.validator.PasswordField;
import com.example.main.validator.RoleRadioButton;
import com.example.main.validator.UserField;

public class CreateUserImpl extends AppCompatActivity implements CreateUser{
    private EditText userEditText;
    private EditText passwordEditText;
    private EditText passwordEditText2;
    private String accessToken;
    private String phoneNumber;
    private String password;
    private String userName;
    private String role;
    private ProgressDialog dialog;
    private boolean isUser ;
    private boolean isProvider ;
    private RadioButton userRadio ;
    private RadioButton providerRadio;
    private PresenterImpl presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        isProvider = false;
        isUser = false;
        userRadio = (RadioButton) findViewById(R.id.userRadio);
        providerRadio = (RadioButton) findViewById(R.id.providerRadio);
        userEditText = (EditText) findViewById(R.id.userText);
        passwordEditText = (EditText) findViewById(R.id.passwordText);
        passwordEditText2 = (EditText) findViewById(R.id.reTypePasswordText);
        Intent intent = getIntent();
        accessToken = intent.getStringExtra("accessToken");
        phoneNumber = intent.getStringExtra("phoneNumber");
        presenter = new PresenterImpl(this);




    }


    public void onProviderClicked(View view) {
        isProvider = true ;
    }

    public void onUserClicked(View view) {
        isUser = true;
    }

    public void OnSubmit(View view) {
        Network network = new Network(this);
        if(network.isNetworkConnected()) {

            UserField userField = new UserField(userEditText);
            PasswordField passwordField = new PasswordField(passwordEditText);
            PasswordField passwordField2 = new PasswordField(passwordEditText2);
            RoleRadioButton roleRadioButton = new RoleRadioButton(userRadio , providerRadio , this );

            if (userField.validate() && passwordField.validate()
                    && passwordField2.matchPassword(passwordEditText)
            && roleRadioButton.validate()) {
                // verify phone
                password = passwordEditText.getText().toString();
                userName = userEditText.getText().toString();
                if(isUser){
                    role = "user" ;
                }else if(isProvider){
                    role = "provider" ;
                }
                dialog = new ProgressDialog(this);
                dialog.setTitle("Loading");
                dialog.setMessage("Please wait ");
                dialog.show();
                presenter.createAcc(this , userName , password , role  , phoneNumber , accessToken);


            }
        }else{

            Toast.makeText(this, "Check Network Connectivity" , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showUserNameTakenHint() {
        dialog.dismiss();
        Toast.makeText(this , "Username  already taken " , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToVerifyPhone() {
        Toast.makeText(this , " Phone verification expired" , Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this , PhoneVerification.class);
        startActivity(intent);
    }

    @Override
    public void showSuccessActivity(int userId , String role , String userName) {
        if(role.equals("user")) {
            Intent intent = new Intent(this, SucceededOperation.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, CreateStoreImpl.class);
            intent.putExtra("accessToken" , accessToken);
            intent.putExtra("userName" , userName);
            startActivity(intent);
        }
    }
}
