package com.example.main.signup.codeVerification.smsDetector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.main.main.view.MainActivity;
import com.example.main.signup.codeVerification.view.CodeVerificationImpl;

public class SmsReceiver extends BroadcastReceiver {
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SmsBroadcastReceiver";
    private String msg;
    private String phoneNumber;

    @Override
    public void onReceive(Context context, Intent intent) {
        //retrieves the general action to be performed and display on log
        if(context == CodeVerificationImpl.getContext()){

        }

        if (intent.getAction() == SMS_RECEIVED) {
            //retrieves a map of extended data from the intent
            Bundle dataBundle = intent.getExtras();
            if (dataBundle != null) {
                //creating PDU(Protocol Data Unit) object which is a protocol for transferring message
                Object[] mypdu = (Object[]) dataBundle.get("pdus");
                final SmsMessage[] message = new SmsMessage[mypdu.length];

                for (int i = 0; i < mypdu.length; i++) {
                    //for build versions >= API Level 23
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = dataBundle.getString("format");
                        //From PDU we get all object and SmsMessage Object using following line of code
                        message[i] = SmsMessage.createFromPdu((byte[]) mypdu[i], format);
                    } else {
                        //<API level 23
                        message[i] = SmsMessage.createFromPdu((byte[]) mypdu[i]);
                    }

                    msg = message[i].getMessageBody();
                    phoneNumber = message[i].getOriginatingAddress();
                }
                Toast.makeText(context, "Message: " + msg + "\nNumber: " + phoneNumber, Toast.LENGTH_LONG).show();
            }
        }
    }
}
