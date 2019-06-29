package com.example.main.signup.accountCreation.httpConnection;

import android.content.Context;
import android.util.Log;

import com.example.main.serviceModels.AccessToken;
import com.example.main.serviceModels.AccountCreationResponse;
import com.example.main.serviceModels.NewUser;
import com.example.main.signup.accountCreation.apiServices.ApiAccountCreation;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateAccount {
    private static final String BASE_URL = "http://192.168.1.3:5000/";
    private String phoneNumber;
    private String accessToken;
    private Context context;
    private String userName;
    private String password;
    private String role;
    private com.example.main.signup.accountCreation.httpConnection.Presenter presenter;


    public CreateAccount(Presenter presenter, Context context, String userName, String password, String role
            , String phoneNumber, String accessToken) {
        this.presenter = presenter;
        this.phoneNumber = phoneNumber;
        this.accessToken = accessToken;
        this.userName = userName;
        this.role = role;
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
        Log.v("msg"  , ""+phoneNumber);
        final NewUser user = new NewUser(userName ,password ,role ,phoneNumber);
      //  Log.v("msg", "222" + code.getPhoneNumber());


        // api authentication service
        ApiAccountCreation apiAccountCreation = retrofit.create(ApiAccountCreation.class);
        AccessToken token = new AccessToken(accessToken);
        // start asking
        Call<AccountCreationResponse> call = apiAccountCreation.createNewAccount("Bearer " + token.getToken(), user);


// revceive response
        call.enqueue(new Callback<AccountCreationResponse>() {
            @Override
            public void onResponse(Call<AccountCreationResponse> call, Response<AccountCreationResponse> response) {
                Log.v("msg", "success");
                int statusCode = response.code();
                switch (statusCode) {
                    case 400:
                        // code block
                        Log.v("msg", "missing one or more parameters");
                        break;

                    case 410:
                        // code block
                        Log.v("msg", "unauthorized or expired token ");
                        presenter.OnPhoneExpired();
                        break;
                    case 409:
                        // code block
                        Log.v("msg", "code expired");
                        presenter.OnTakenUserName();
                        break;
                    case 200:
                        // code block
                        presenter.OnComplete(response.body().getId() , user.getRole() , userName);
                        Log.v("msg", "success " + response.body().getMeassage());
                        break;
                    default:
                        // code block
                        Log.v("msg", "error code " + statusCode);
                }

            }

            @Override
            public void onFailure(Call<AccountCreationResponse> call, Throwable t) {
                Log.v("msg", "error  " + t.getMessage());
            }
        });
    }
}
