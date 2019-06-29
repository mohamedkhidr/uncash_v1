package com.example.main.main.presenter.httpConnection;

import android.util.Log;

import com.example.main.main.model.apiServices.LoginAuthentication;
import com.example.main.serviceModels.AccessToken;
import com.example.main.serviceModels.User;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserLogin {
    private static final String BASE_URL = "http://192.163.1.3:5000/";
    private static final int APP_ID = 43256708;
    private String userName ;
    private String password ;
    private int appId ;


    public void exceute() {
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

        User user = new User(userName, password ,appId );

        // api authentication service
        LoginAuthentication loginAuthentication = retrofit.create(LoginAuthentication.class);


        // start asking
        Call<AccessToken> call = loginAuthentication.getAccesToken(user);


// revceive response
        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                int statusCode = response.code();
                switch(statusCode) {
                    case 400:
                        // code block
                        Log.v("msg" , "missing one or more parameters");
                        break;
                    case 401:
                        // code block
                        Log.v("msg" , "bad one or more parameters");
                        break;
                    case 200:
                        // code block
                        AccessToken token  = response.body();
                        //call Presenter interface.OnAccessTokenReceived
                        Log.v("msg" , "success");
                        break;
                    default:
                        // code block
                        Log.v("msg" , "error code " + statusCode);
                }

            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.v("msg" , "error  " + t.getMessage());
            }
        });
    }
}

