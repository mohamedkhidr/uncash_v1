package com.example.main.features.sendMoney.httpConnection;

import android.util.Log;

import com.example.main.features.sendMoney.apiService.ApiTransfer;
import com.example.main.serviceModels.AccessToken;
import com.example.main.serviceModels.TransferEligabiltyResponse;
import com.example.main.serviceModels.TransferCheckParams;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckEligabilty {
    private static final String BASE_URL = "https://uncashapp.herokuapp.com/";
    private String userName ;
    private String accessToken;
    private int amount;
    private CheckEligabiltyPresenter presenter ;


    public CheckEligabilty(CheckEligabiltyPresenter presenter , String userName , int amount , String accessToken) {
        this.presenter = presenter;
        this.accessToken = accessToken;
        this.userName = userName;
        this.amount = amount ;
    }


    public void exceute() {
        Log.v("msg", "exceute api login");

//
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();


        // user credentials
        //Log.v("msg"  , ""+phoneNumber);
        TransferCheckParams transferCheckParams = new TransferCheckParams(userName , amount);
        //  Log.v("msg", "222" + code.getPhoneNumber());


        // api authentication service
        ApiTransfer apiTransfer = retrofit.create(ApiTransfer.class);
        AccessToken token = new AccessToken(accessToken);
        // start asking
        Call<TransferEligabiltyResponse> call = apiTransfer.checkEligabilty("Bearer " + token.getToken(), transferCheckParams);


// revceive response
        call.enqueue(new Callback<TransferEligabiltyResponse>() {
            @Override
            public void onResponse(Call<TransferEligabiltyResponse> call, Response<TransferEligabiltyResponse> response) {
                Log.v("msg", "success");
                int statusCode = response.code();
                switch (statusCode) {
                    case 400:
                        // code block
                        Log.v("msg", "missing one or more parameters");
                        break;
                    case 401:
                        // code block
                        presenter.OnNotEnoughCredit();
                        Log.v("msg", "insufficient credit");
                        break;
                    case 404:
                        // code block
                        presenter.onNotFoundReceiver();
                        Log.v("msg", "receiver user not found ");
                        break;

                    case 200:
                        // code block
                        presenter.OnComplete(response.body());
                        Log.v("msg", "success " );
                        break;
                    default:
                        // code block
                        Log.v("msg", "error code " + statusCode);
                }

            }

            @Override
            public void onFailure(Call<TransferEligabiltyResponse> call, Throwable t) {
                Log.v("msg", "error  " + t.getMessage());
            }
        });
    }
}

