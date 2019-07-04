package com.example.main.features.sendMoney;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.main.R;
import com.example.main.features.sendMoney.manual.view.ManualTransferImpl;
import com.example.main.features.sendMoney.useQR.QRTransferImpl;

public class ChooseTransferWay extends AppCompatActivity {
    private String accessToken ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_transfer_way);
        accessToken = getIntent().getStringExtra("accessToken");
    }

    public void OnManualClicked(View view) {
        Intent secondActivity = new Intent(this, ManualTransferImpl.class);
        secondActivity.putExtra("accessToken" , accessToken);
        startActivity(secondActivity);
    }

    public void OnQRcodeClicked(View view) {
        Intent secondActivity = new Intent(this, QRTransferImpl.class);
        secondActivity.putExtra("accessToken" , accessToken);
        startActivity(secondActivity);
    }
}
