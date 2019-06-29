package com.example.main.validator;

import android.widget.EditText;

public class UserField {

    private EditText userName;


    public UserField(EditText userName) {
        this.userName = userName;
    }

    public boolean validate(){
        String name =  userName.getText().toString();
        if(name.equals("")){
            userName.setError("Empty field");
            return false;
        }else if(name.length()<8 ){
            userName.setError("user name min 8 digit");
            return false;
        }else if(name.length() > 10){
            userName.setError("user name max 10 digit");
            return false;
        }else if(!name.matches("^[A-Za-z0-9]+$")){
            userName.setError("user name  should contain number and letters only");
            return false;
        }
        return  true;
    }
}
