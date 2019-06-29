package com.example.main.main.presenter.httpConnection;

import com.example.main.serviceModels.AccessToken;

public interface LoginPresenter {
   public void OnAccessTokenReceived(AccessToken accessToken);

   public void OnWrongCredentials();
}
