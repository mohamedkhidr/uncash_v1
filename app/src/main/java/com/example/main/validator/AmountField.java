package com.example.main.validator;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class AmountField {
    private EditText amountEditText;
    private Context context ;


    public AmountField(Context context , EditText amountEditText) {
        this.amountEditText = amountEditText;
        this.context =context;
    }

    public boolean validate() {
        String amountText = amountEditText.getText().toString();
        if (amountText.equals("")) {

            Toast.makeText(context, "Enter a valid amount value  ", Toast.LENGTH_SHORT).show();
            return false;
        }else if(amountText.length()>4 ){
            Toast.makeText(context, "Max valid amount is 1000 EGP  ", Toast.LENGTH_SHORT).show();
            return false;
        }else if(amountText.length()<2 ){
            Toast.makeText(context, "Min valid amount is 10 EGP  ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}

