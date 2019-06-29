package com.example.main.validator;

import android.widget.EditText;

public class Login {
    private EditText phoneEditText ;
    private EditText passwordEditText ;

    public Login(EditText phoneEditText, EditText passwordEditText) {
        this.phoneEditText = phoneEditText;
        this.passwordEditText = passwordEditText;
    }

    public boolean validate() {
        String phone = phoneEditText.getText().toString();
        String passsword = passwordEditText.getText().toString();
        if (phone.equals("")) {
            phoneEditText.setError("Empty phone field ");
            return false;
        }
        if (passsword.equals("")) {
            passwordEditText.setError("Empty password field ");
            return false;
        }
        return  true ;
    }

}
