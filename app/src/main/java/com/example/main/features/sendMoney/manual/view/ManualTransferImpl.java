package com.example.main.features.sendMoney.manual.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.main.R;
import com.example.main.features.sendMoney.httpConnection.CheckEligabiltyPresenterImpl;
import com.example.main.serviceModels.TransferEligabiltyResponse;
import com.example.main.validator.AmountField;
import com.example.main.validator.Login;
import com.example.main.validator.Network;
import com.example.main.validator.UserField;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ManualTransferImpl extends AppCompatActivity  implements ManualTransfer{
    private EditText userEditText ;
    private EditText amountEditText ;
    private String userName ;
    private int amount ;
    private String accessToken ;
    private ProgressDialog dialog ;
    private CheckEligabiltyPresenterImpl presenter ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_transfer_impl);
        userEditText = (EditText) findViewById(R.id.receiver_field);
        amountEditText = (EditText) findViewById(R.id.amount_field);
        accessToken = getIntent().getStringExtra("accessToken");
        presenter = new CheckEligabiltyPresenterImpl(this);

    }

    public void OnSubmit(View view) {
        Network network = new Network(this);
        boolean networkStatus = network.isNetworkConnected();
        UserField userField = new UserField(userEditText);
        AmountField amountField = new AmountField(this , amountEditText);
        if(networkStatus) {
            if(userField.validate() && amountField.validate()) {
               userName = userEditText.getText().toString();
                amount = Integer.parseInt(amountEditText.getText().toString());
                Log.v("msg", "onsignip");
                dialog = new ProgressDialog(this);
                dialog.setTitle("Loading");
                dialog.setMessage("Please wait ");
                dialog.show();
                presenter.checkEligabilty(userName , amount , accessToken);
            }
        }else{
            Toast.makeText(this , "Check network connectivity" , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnCheckSuccess(TransferEligabiltyResponse body) {
        Intent intent = new Intent(this , TrasnferSubmissionImpl.class);
        intent.putExtra("accessToken" , accessToken);
        intent.putExtra("receiverId" , body.getReceiverId());
        Log.v("msg" , ""+body.getReceiverId());
        intent.putExtra("amount" , body.getAmount());
        intent.putExtra("commission" , body.getCommission());
        intent.putExtra("total" , body.getTotal());
        intent.putExtra("providerId" , body.getProviderId());
        intent.putExtra("userName" , userName);


        startActivity(intent);
    }

    @Override
    public void showNotFoundReceiverHint() {
        dialog.dismiss();
        Toast.makeText(this , "receiver username not found" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNotEnoughCreditHint() {
        dialog.dismiss();
        Toast.makeText(this , "your credit is not sufficient" , Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && result.getContents() != null) {
            // get contents and send to manual
            Network network = new Network(this);
            boolean networkStatus = network.isNetworkConnected();
            if(networkStatus) {
                String params []  = result.getContents().split("/");
                    userName = params[0];
                    amount = Integer.parseInt(params[1]);
                    Log.v("msgggggggg" , ""+ userName + " "+amount);
                    Log.v("msg", "onsignip");
                    dialog = new ProgressDialog(this);
                    dialog.setTitle("Loading");
                    dialog.setMessage("Please wait ");
                    dialog.show();
                    presenter.checkEligabilty(userName , amount , accessToken);

            }else{
                Toast.makeText(this , "Check network connectivity" , Toast.LENGTH_SHORT).show();
            }


            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    public void OnUseQr(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setCameraId(0);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setPrompt("scannig");
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setBarcodeImageEnabled(true);
        intentIntegrator.initiateScan();
    }


}
