package com.example.main.features.sendMoney.useQR;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.main.R;
import com.example.main.features.enquiry.enquiry.apiServices.ApiGettingPoints;
import com.example.main.features.enquiry.enquiry.httpConnection.Presenter;
import com.example.main.features.sendMoney.apiService.ApiGetUser;
import com.example.main.serviceModels.AccessToken;
import com.example.main.serviceModels.LocationPoint;
import com.example.main.serviceModels.User;
import com.example.main.serviceModels.UserName;
import com.example.main.validator.AmountField;
import com.example.main.validator.Network;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransferParamsActivity extends AppCompatActivity {
    private  int amount ;
    private String userName ;
    private EditText amountEditText ;
    private String accessToken ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_params);
        amountEditText = (EditText) findViewById(R.id.amountField);
        accessToken = getIntent().getStringExtra("accessToken");

    }

    public void OnSubmit(View view) {
        Network network = new Network(this);
        boolean networkStatus = network.isNetworkConnected();
        AmountField amountField = new AmountField(this , amountEditText);
        if(networkStatus) {
            if (amountField.validate()) {
                amount = Integer.parseInt(amountEditText.getText().toString());
                getUserName();

            }
        }else{
            Toast.makeText(this , "Check network connectivity" , Toast.LENGTH_SHORT).show();
        }
    }


public void getUserName(){
    String url = "https://uncashapp.herokuapp.com/";


        Log.v("msg", "exceute api login");

//
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();




        // api authentication service
        ApiGetUser apiGetUser = retrofit.create(ApiGetUser.class);
        AccessToken token = new AccessToken(accessToken);
        // start asking
        Call<UserName> call = apiGetUser.getUser("Bearer " + token.getToken());


// revceive response
        call.enqueue(new Callback<UserName>() {
            @Override
            public void onResponse(Call<UserName> call, Response<UserName> response) {
                Log.v("msg", "success");
                int statusCode = response.code();
                switch (statusCode) {
                    case 200:
                        // code block
                        OnComplete(response.body().getUserName());
                        Log.v("msg", "success " + response.code() + response.body().getUserName());
                        break;
                    default:
                        // code block
                        Log.v("msg", "error code " + statusCode);
                }

            }

            @Override
            public void onFailure(Call<UserName> call, Throwable t) {
                Log.v("msg", "error  " + t.getMessage());
            }
        });
    }

    public  void OnComplete (String userName) {
        Intent intent = new Intent(this , GenerateQR.class);
        intent.putExtra("userName" , userName);
        intent.putExtra("amount" , amount);
        startActivity(intent);
    }
}
