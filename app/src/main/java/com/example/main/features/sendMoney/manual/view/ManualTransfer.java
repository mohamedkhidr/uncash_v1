package com.example.main.features.sendMoney.manual.view;

import com.example.main.serviceModels.TransferEligabiltyResponse;

public interface ManualTransfer {
    public void OnCheckSuccess(TransferEligabiltyResponse body);

    public void showNotFoundReceiverHint();

    public void showNotEnoughCreditHint();
}
