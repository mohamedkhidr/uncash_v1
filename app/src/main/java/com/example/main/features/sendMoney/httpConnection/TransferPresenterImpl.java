package com.example.main.features.sendMoney.httpConnection;

import android.util.Log;

import com.example.main.features.sendMoney.manual.view.ManualTransfer;
import com.example.main.features.sendMoney.manual.view.TrasnferSubmission;
import com.example.main.serviceModels.TransferEligabiltyResponse;
import com.example.main.serviceModels.TransferResponse;

public class TransferPresenterImpl implements TrasnferPresenter {
    private TrasnferSubmission trasnferSubmission ;
    private Transfer transfer ;
    private int providerId ;
    private int commission ;
    private String receiverUser;
    private int receiverId;
    private int amount ;
    private String accessToken ;


    public TransferPresenterImpl(TrasnferSubmission trasnferSubmission) {
        this.trasnferSubmission = trasnferSubmission;
    }

    public void transfer(int receiverId , int amount , int providerId , int commission , String accessToken) {
        this.receiverId = receiverId;
        Log.v("msg" , ""+receiverId);
        this.amount = amount;
        this.providerId = providerId;
        this.commission = commission;
        this.accessToken = accessToken;
        // get app access token
        Log.v("msg" , "presenter signup");
        transfer = new Transfer(this ,  receiverId ,  amount , providerId , commission , accessToken);
        Log.v("msg" , ""+receiverId);
        transfer.exceute();

    }


    @Override
    public void OnComplete() {
        trasnferSubmission.showSuccess();
    }

    @Override
    public void onInternalServerError() {
        trasnferSubmission.showFailure();
    }
}
