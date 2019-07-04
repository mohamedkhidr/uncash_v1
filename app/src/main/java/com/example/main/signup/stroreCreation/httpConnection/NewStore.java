package com.example.main.signup.stroreCreation.httpConnection;

import android.content.Context;
import android.util.Log;

import com.example.main.serviceModels.AccessToken;
import com.example.main.serviceModels.AccountCreationResponse;
import com.example.main.serviceModels.NewUser;
import com.example.main.serviceModels.Store;
import com.example.main.serviceModels.StoreCreationResponse;
import com.example.main.signup.accountCreation.apiServices.ApiAccountCreation;
import com.example.main.signup.stroreCreation.apiServices.ApiNewStore;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewStore {
    private static final String BASE_URL = "https://uncashapp.herokuapp.com/";
    private String storePhone;
    private String accessToken;
    private Context context;
    private String storeName;
    private String latitude;
    private String longitude;
    private String userName ;
    private com.example.main.signup.stroreCreation.httpConnection.Presenter presenter;


    public NewStore(Presenter presenter, Context context, String latitude , String longitude , String storeName ,
            String storePhone, String accessToken , String userName) {
        this.presenter = presenter;
        this.storePhone = storePhone;
        this.accessToken = accessToken;
        this.storeName = storeName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.context = context;
        this.userName = userName;
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
        Store store = new Store(storeName , storePhone , latitude , longitude , userName);
        //  Log.v("msg", "222" + code.getPhoneNumber());


        // api authentication service
       ApiNewStore apiNewStore = retrofit.create(ApiNewStore.class);
        AccessToken token = new AccessToken(accessToken);
        // start asking
        Call<StoreCreationResponse> call = apiNewStore.createNewAccount("Bearer " + token.getToken(), store);


// revceive response
        call.enqueue(new Callback<StoreCreationResponse>() {
            @Override
            public void onResponse(Call<StoreCreationResponse> call, Response<StoreCreationResponse> response) {
                Log.v("msg", "success");
                int statusCode = response.code();
                switch (statusCode) {
                    case 400:
                        // code block
                        Log.v("msg", "missing one or more parameters");
                        break;

                    case 200:
                        // code block
                        presenter.OnComplete();
                        Log.v("msg", "success " + response.body().getMeassage());
                        break;
                    default:
                        // code block
                        Log.v("msg", "error code " + statusCode);
                }

            }

            @Override
            public void onFailure(Call<StoreCreationResponse> call, Throwable t) {
                Log.v("msg", "error  " + t.getMessage());
            }
        });
    }
}
