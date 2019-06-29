package com.example.main.validator;

import android.widget.EditText;

public class PhoneNumberField {
    private EditText phoneNumber;


    public PhoneNumberField(EditText phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean validate(){
        String number =  phoneNumber.getText().toString();
        if(number.equals("")){
            phoneNumber.setError("Empty field");
            return false;
        }else if(number.length()<11 || number.length() > 11){
            phoneNumber.setError("phone number 11 digit");
            return false;
        }else if(!number.matches("^01[0125][0-9]{8}$")){
            phoneNumber.setError("wrong number pattern");
            return false;
        }
        return  true;
    }
}
