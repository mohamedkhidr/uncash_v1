package com.example.main.features.enquiry.enquiry.httpConnection;

import android.content.Context;
import android.util.Log;

import com.example.main.features.enquiry.enquiry.apiServices.ApiGettingPoints;
import com.example.main.serviceModels.LocationPoint;
import com.example.main.serviceModels.AccessToken;
import com.example.main.serviceModels.AccountCreationResponse;


import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetPoints {

    private static final String BASE_URL = "http://192.168.1.3:5000/";
    private String phoneNumber;
    private String accessToken;
    private Context context;
    private String userName;
    private String password;
    private String role;
    private com.example.main.features.enquiry.enquiry.httpConnection.Presenter presenter;


    public GetPoints(Presenter presenter, Context context, String accessToken) {
        this.presenter = presenter;
        this.accessToken = accessToken;
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


        // api authentication service
        ApiGettingPoints apiGettingPoints = retrofit.create(ApiGettingPoints.class);
        AccessToken token = new AccessToken(accessToken);
        // start asking
        Call<List<LocationPoint>> call = apiGettingPoints.getAllPoints("Bearer " + token.getToken());


// revceive response
        call.enqueue(new Callback<List<LocationPoint>>() {
            @Override
            public void onResponse(Call<List<LocationPoint>> call, Response<List<LocationPoint>> response) {
                Log.v("msg", "success");
                int statusCode = response.code();
                switch (statusCode) {
                    case 200:
                        // code block
                        ArrayList<LocationPoint> allPoints = new ArrayList<LocationPoint>();
                        allPoints = (ArrayList<LocationPoint>) response.body();
                        presenter.OnComplete(allPoints);
                        Log.v("msg", "success " + response.code());
                        break;
                    default:
                        // code block
                        Log.v("msg", "error code " + statusCode);
                }

            }

            @Override
            public void onFailure(Call<List<LocationPoint>> call, Throwable t) {
                Log.v("msg", "error  " + t.getMessage());
            }
        });
    }
}
