package com.example.main.main.presenter.httpConnection;

import android.util.Log;

import com.example.main.main.model.apiServices.ApiAuthentication;
import com.example.main.serviceModels.AccessToken;
import com.example.main.serviceModels.AdminUser;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiLogin {
    private static final String BASE_URL = "https://uncashapp.herokuapp.com/";
    private static final String APP_USER_NAME = "uncashapp";
    private static final String APP_USER_PASSWORD = "234568643456786950" ;
    private SignupPresenter signupPresenter;

    public ApiLogin(SignupPresenter signupPresenter) {
        this.signupPresenter = signupPresenter;
    }

    public void OnLoginCompleted(AccessToken token){
        signupPresenter.OnAccessTokenReceived(token);
    }
    public void exceute() {
        Log.v("msg" , "exceute api login");
        this.signupPresenter = signupPresenter;
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

        AdminUser user = new AdminUser(APP_USER_NAME, APP_USER_PASSWORD);

        // api authentication service
        ApiAuthentication apiAuthentication = retrofit.create(ApiAuthentication.class);


        // start asking
        Call<AccessToken> call = apiAuthentication.getAccesToken(user);


// revceive response
        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                Log.v("msg" , "success");
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
                       // Log.v("msg" , ""+response.body().getToken());

                        //call SignupPresenter interface.OnAccessTokenReceived
                        OnLoginCompleted(response.body());
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

