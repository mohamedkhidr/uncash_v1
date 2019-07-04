package com.example.main.features.credit.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.main.R;
import com.example.main.features.credit.model.ApiCredit;
import com.example.main.features.myOperations.model.ApiOperation;
import com.example.main.features.view.DashBoard;
import com.example.main.serviceModels.AccessToken;
import com.example.main.serviceModels.CreditInfo;
import com.example.main.serviceModels.Operation;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreditGetter extends AppCompatActivity {
    private String accessToken;
    private TextView creditTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_getter);
        accessToken = getIntent().getStringExtra("accessToken");
        Log.v("msg", "" + accessToken);

        creditTextView = (TextView) findViewById(R.id.credit);

        getCredit(accessToken);
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }

    public void getCredit(String accessToken) {
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


        // user credentials
        //Log.v("msg"  , ""+phoneNumber);

        //  Log.v("msg", "222" + code.getPhoneNumber());


        // api authentication service
        ApiCredit apiCredit = retrofit.create(ApiCredit.class);

        // start asking
        Call<CreditInfo> call = apiCredit.getCredit("Bearer " + accessToken);


// revceive response
        call.enqueue(new Callback<CreditInfo>() {
            @Override
            public void onResponse(Call<CreditInfo> call, Response<CreditInfo> response) {
                Log.v("msg", "success");
                int statusCode = response.code();
                switch (statusCode) {

                    case 200:
                        // code block


                        OnComplete(response.body());
                        Log.v("msg", "success ");
                        break;
                    default:
                        // code block
                        Log.v("msg", "error code " + statusCode);
                }

            }

            @Override
            public void onFailure(Call<CreditInfo> call, Throwable t) {
                Log.v("msg", "error  " + t.getMessage());
            }
        });
    }

    public void OnComplete(CreditInfo creditInfo) {
        creditTextView.setText(creditInfo.getBalance() + "");

    }
}

