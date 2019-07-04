package com.example.main.features.sendMoney.httpConnection;

import com.example.main.serviceModels.TransferResponse;

public interface TrasnferPresenter {
    public void OnComplete();

    public void onInternalServerError();
}
