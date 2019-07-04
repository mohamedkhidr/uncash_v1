package com.example.main.features.sendMoney.httpConnection;

import com.example.main.serviceModels.TransferEligabiltyResponse;

public interface CheckEligabiltyPresenter {
    public void OnComplete(TransferEligabiltyResponse body);

    public void onNotFoundReceiver();

    public void OnNotEnoughCredit();
}
