package com.example.main.features.sendMoney.httpConnection;

import android.util.Log;

import com.example.main.features.sendMoney.apiService.ApiTransfer;
import com.example.main.serviceModels.AccessToken;
import com.example.main.serviceModels.TransferEligabiltyResponse;
import com.example.main.serviceModels.TransferCheckParams;
import com.example.main.serviceModels.TransferParams;
import com.example.main.serviceModels.TransferResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Transfer {
    private static final String BASE_URL = "https://uncashapp.herokuapp.com/";
    private int receiverId ;
    private int providerId;
    private int commission ;
    private String accessToken;
    private int amount;
    private TrasnferPresenter presenter ;


    public Transfer(TrasnferPresenter presenter , int receiverId , int amount ,int providerId , int commission , String accessToken) {
        this.presenter = presenter;
        this.accessToken = accessToken;
        this.receiverId = receiverId;
        this.amount = amount ;
        this.providerId = providerId;
        this.commission = commission;
        Log.v("msg" , ""+receiverId);
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
        TransferParams transferParams = new TransferParams(receiverId , amount , providerId ,commission);
        Log.v("msg" , ""+receiverId);
        //  Log.v("msg", "222" + code.getPhoneNumber());


        // api authentication service
        ApiTransfer apiTransfer = retrofit.create(ApiTransfer.class);
        AccessToken token = new AccessToken(accessToken);
        // start asking
        Call<TransferResponse> call = apiTransfer.transfer("Bearer " + token.getToken(), transferParams);


// revceive response
        call.enqueue(new Callback<TransferResponse>() {
            @Override
            public void onResponse(Call<TransferResponse> call, Response<TransferResponse> response) {
                Log.v("msg", "success");
                int statusCode = response.code();
                switch (statusCode) {
                    case 400:
                        // code block
                        Log.v("msg", "missing one or more parameters");
                        break;

                    case 500:
                        // code block
                        presenter.onInternalServerError();
                        Log.v("msg", "server error ");
                        break;

                    case 200:
                        // code block
                        presenter.OnComplete();
                        Log.v("msg", "success " );
                        break;
                    default:
                        // code block
                        Log.v("msg", "error code " + statusCode);
                }

            }

            @Override
            public void onFailure(Call<TransferResponse> call, Throwable t) {
                Log.v("msg", "error  " + t.getMessage());
            }
        });
    }
}


