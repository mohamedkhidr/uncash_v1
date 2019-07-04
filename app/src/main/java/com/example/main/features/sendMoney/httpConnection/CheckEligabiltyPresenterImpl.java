package com.example.main.features.sendMoney.httpConnection;

import android.util.Log;

import com.example.main.features.sendMoney.manual.view.ManualTransfer;
import com.example.main.main.presenter.httpConnection.UserLogin;
import com.example.main.main.view.Iview;
import com.example.main.serviceModels.AccessToken;
import com.example.main.serviceModels.TransferEligabiltyResponse;

public class CheckEligabiltyPresenterImpl implements  CheckEligabiltyPresenter {
    private ManualTransfer manualTransfer ;
    private CheckEligabilty checkEligabilty ;
    private String userName;
    private int amount ;
    private String accessToken ;


    public CheckEligabiltyPresenterImpl(ManualTransfer manualTransfer) {
        this.manualTransfer = manualTransfer;
    }

    public void checkEligabilty(String userName , int amount , String accessToken) {
        this.userName = userName;
        this.amount = amount;
        this.accessToken = accessToken;
        // get app access token
        Log.v("msg" , "presenter signup");
        checkEligabilty = new CheckEligabilty(this ,  userName ,  amount ,  accessToken);
        checkEligabilty.exceute();

    }



    @Override
    public void OnComplete(TransferEligabiltyResponse body) {
        manualTransfer.OnCheckSuccess(body);

    }

    @Override
    public void onNotFoundReceiver() {
        manualTransfer.showNotFoundReceiverHint();
    }

    @Override
    public void OnNotEnoughCredit() {
        manualTransfer.showNotEnoughCreditHint();
    }
}
