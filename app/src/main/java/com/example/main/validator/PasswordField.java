package com.example.main.validator;

import android.widget.EditText;

public class PasswordField {


    private EditText password;


    public PasswordField(EditText password) {
        this.password = password;
    }

    public boolean validate(){
        String passwordValue =  password.getText().toString();
        if(passwordValue.equals("")){
            password.setError("Empty field");
            return false;
        }else if(passwordValue.length()<10 || passwordValue.length() > 10){
            password.setError("password 10 digit");
            return false;
        }else if(!passwordValue.matches("^[A-Z]{3}[A-Za-z0-9]+$")){
            password.setError("password  should begin with 3 uppercase letters");
            return false;
        }
        return  true;
    }

    public boolean matchPassword(EditText password2){
        String passwordValue2 = password2.getText().toString();
        String passwordValue1 = password.getText().toString();
        if(!passwordValue2.equals(passwordValue1)){
            password2.setError("Passwords don't match");
            return false;
        }
        return  true;
    }
}
