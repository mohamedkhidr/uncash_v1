package com.example.main.resetPassword.passwordReset.httpConnection;

import android.content.Context;
import android.util.Log;

import com.example.main.resetPassword.passwordReset.apiServices.ApiResetPassword;
import com.example.main.serviceModels.AccessToken;
import com.example.main.serviceModels.NewPassword;
import com.example.main.serviceModels.ResetPasswordResponse;
import com.example.main.serviceModels.Store;
import com.example.main.serviceModels.StoreCreationResponse;
import com.example.main.signup.stroreCreation.apiServices.ApiNewStore;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Reset {
    private static final String BASE_URL = "https://uncashapp.herokuapp.com/";
    private String phoneNumber;
    private String accessToken;
    private Context context;
    private String password;
    private com.example.main.resetPassword.passwordReset.httpConnection.Presenter presenter ;


    public Reset(Presenter presenter, Context context, String password , String phoneNumber, String accessToken ) {
        this.presenter = presenter;
        this.phoneNumber = phoneNumber;
        this.accessToken = accessToken;
        this.password = password;
        this.context = context;

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
        NewPassword newPassword = new NewPassword(phoneNumber , password);
        //  Log.v("msg", "222" + code.getPhoneNumber());


        // api authentication service
        ApiResetPassword apiResetPassword = retrofit.create(ApiResetPassword.class);
        AccessToken token = new AccessToken(accessToken);
        // start asking
        Call<ResetPasswordResponse> call = apiResetPassword.resetMyPassword("Bearer " + token.getToken(), newPassword);


// revceive response
        call.enqueue(new Callback<ResetPasswordResponse>() {
            @Override
            public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                Log.v("msg", "success");
                int statusCode = response.code();
                switch (statusCode) {
                    case 400:
                        // code block
                        Log.v("msg", "missing one or more parameters");
                        break;
                    case 409:
                        // code block
                        presenter.OnNotValidPassword();
                        Log.v("msg", "not valid password");
                        break;

                    case 410:
                        // code block
                        presenter.OnExpiredPhoneVerification();
                        Log.v("msg", "expired phone verification");
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
            public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {
                Log.v("msg", "error  " + t.getMessage());
            }
        });
    }
}
