package com.example.main.signup.stroreCreation.httpConnection;

import android.content.Context;
import android.util.Log;

import com.example.main.signup.accountCreation.httpConnection.CreateAccount;
import com.example.main.signup.accountCreation.view.CreateUser;
import com.example.main.signup.stroreCreation.view.CreateStore;

public class PresenterImpl implements  Presenter {
    private CreateStore createStore ;
    private Context context ;
    private NewStore newStore ;
    private String storeName ;
    private String storePhone ;
    private String accessToken ;
    private String latitude ;
    private String longitude ;
    private String userName;


    public PresenterImpl(CreateStore createStore) {
        this.createStore = createStore;
    }

    public void createStore(Context context , String latitude , String longitude , String storeName, String storePhone , String accessToken , String userName) {
        this.context = context;
        this.storeName = storeName;
        this.storePhone = storePhone;
        this.accessToken = accessToken;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userName = userName;


        Log.v("msg" , "presenter signup");
        NewStore newStore = new NewStore(this , context , latitude , longitude , storeName , storePhone , accessToken , userName);
        newStore.exceute();

    }

    @Override
    public void OnComplete() {
        createStore.OnSuccess();
    }
}
