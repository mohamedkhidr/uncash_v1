package com.example.main.validator;

import android.content.Context;
import android.widget.RadioButton;
import android.widget.Toast;

public class RoleRadioButton {
private RadioButton userRadio ;
private RadioButton providerRadio;
private Context context;

    public RoleRadioButton(RadioButton userRadio, RadioButton providerRadio , Context context) {
        this.userRadio = userRadio;
        this.providerRadio = providerRadio;
        this.context = context;
    }

    public boolean validate(){
        if(userRadio.isChecked() || providerRadio.isChecked()){
            return true;
        }else {
            Toast.makeText(context , "Please check user of provider" , Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
