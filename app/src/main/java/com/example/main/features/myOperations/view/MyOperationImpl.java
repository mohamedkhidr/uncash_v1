package com.example.main.features.myOperations.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.main.R;
import com.example.main.features.myOperations.model.ApiOperation;
import com.example.main.features.sendMoney.apiService.ApiTransfer;
import com.example.main.features.view.DashBoard;
import com.example.main.serviceModels.AccessToken;
import com.example.main.serviceModels.Operation;
import com.example.main.serviceModels.TransferCheckParams;
import com.example.main.serviceModels.TransferEligabiltyResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyOperationImpl extends AppCompatActivity {
    private String accessToken ;
    private TextView receiverTextView ;
    private TextView amountTextView ;
    private TextView senderTextView ;
    private TextView commissionTextView ;
    private Operation operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_operation);
        accessToken = getIntent().getStringExtra("accessToken");
        Log.v("msg"  , ""+accessToken);
        receiverTextView = (TextView) findViewById(R.id.receiver);
        amountTextView = (TextView) findViewById(R.id.aa);
        senderTextView = (TextView) findViewById(R.id.sender);
        commissionTextView = (TextView) findViewById(R.id.commission);
        getOperation();
    }

    public void goHome(View view) {
        Intent intent = new Intent(this , DashBoard.class);
        startActivity(intent);
    }

    public void getOperation(){
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
            ApiOperation apiOperation = retrofit.create(ApiOperation.class);
            AccessToken token = new AccessToken(this.accessToken);
            // start asking
            Call<List<Operation>> call = apiOperation.getOperation("Bearer " + token.getToken());


// revceive response
            call.enqueue(new Callback<List<Operation>>() {
                @Override
                public void onResponse(Call<List<Operation>> call, Response<List<Operation>> response) {
                    Log.v("msg", "success");
                    int statusCode = response.code();
                    switch (statusCode) {
                        case 404:
                            // code block
                            OnNoOperationFound();
                            Log.v("msg", "no operation found");
                            break;
                        case 200:
                            // code block
                            ArrayList<Operation> operationArrayList = new ArrayList<Operation>();
                            operationArrayList = (ArrayList<Operation>) response.body();
                            operation = operationArrayList.get(0);
                            OnComplete();
                            Log.v("msg", "success " );
                            break;
                        default:
                            // code block
                            Log.v("msg", "error code " + statusCode);
                    }

                }

                @Override
                public void onFailure(Call<List<Operation>> call, Throwable t) {
                    Log.v("msg", "error  " + t.getMessage());
                }
            });
        }

    public void OnComplete() {
        receiverTextView.setText("To :"+operation.getDestination());
        amountTextView.setText("Amount :"+operation.getAmount()+"");
        senderTextView.setText("From :"+operation.getSource());
        commissionTextView.setText("Commission :"+operation.getCommission()+"");
    }

    public void OnNoOperationFound() {
        receiverTextView.setText("no operation found ");
    }
}

