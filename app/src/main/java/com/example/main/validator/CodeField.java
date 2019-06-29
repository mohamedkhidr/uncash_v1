package com.example.main.validator;

import android.widget.EditText;

public class CodeField {
    private EditText codeNumber;


    public CodeField(EditText codeNumber) {
        this.codeNumber = codeNumber;
    }

    public boolean validate(){
        String code =  codeNumber.getText().toString();
        if(code.equals("")){
            codeNumber.setError("Empty field");
            return false;
        }else if(code.length()<8 || code.length() > 8){
            codeNumber.setError("code 8 digit");
            return false;
        }else if(!code.matches("^[0-9]{8}$")){
            codeNumber.setError("wrong code pattern");
            return false;
        }
        return  true;
    }
}
