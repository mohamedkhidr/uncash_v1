package com.example.main.signup.codeVerification.httpConnection;

import android.util.Log;

import com.example.main.serviceModels.AccessToken;
import com.example.main.serviceModels.Code;
import com.example.main.serviceModels.Message;

import com.example.main.signup.codeVerification.apiServices.ApiCodeVerification;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerifyCode {
    private static final String BASE_URL = "http://192.168.1.3:5000/";
    private String phoneNumber;
    private String accessToken;
    private String codeNumber;
    private com.example.main.signup.codeVerification.httpConnection.Presenter presenter;

    public VerifyCode(Presenter presenter, String codeNumber, String phoneNumber, String accessToken) {
        this.presenter = presenter;
        this.phoneNumber = phoneNumber;
        this.accessToken = accessToken;
        this.codeNumber = codeNumber;
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
        int codeValue = Integer.parseInt(codeNumber);

        Code code = new Code(codeValue, phoneNumber);
        Log.v("msg"  , "222" + code.getPhoneNumber());


        // api authentication service
        ApiCodeVerification apiCodeVerification = retrofit.create(ApiCodeVerification.class);
        AccessToken token = new AccessToken(accessToken);
        // start asking
        Call<Message> call = apiCodeVerification.verifyCode("Bearer " + token.getToken(), code);


// revceive response
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Log.v("msg", "success");
                int statusCode = response.code();
                switch (statusCode) {
                    case 400:
                        // code block
                        presenter.OnWrongCode();
                        Log.v("msg", "missing one or more parameters");

                    case 401:
                        // code block
                        Log.v("msg", "unauthorized or expired token ");
                        presenter.OnTokenExpired();
                        break;
                    case 410:
                        // code block
                        Log.v("msg", "code expired");
                        presenter.OnCodeExpired();
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
            public void onFailure(Call<Message> call, Throwable t) {
                Log.v("msg", "error  " + t.getMessage());
            }
        });
    }
}
