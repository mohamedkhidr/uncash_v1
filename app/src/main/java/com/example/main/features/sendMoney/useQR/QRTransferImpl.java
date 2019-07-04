package com.example.main.features.sendMoney.useQR;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.main.R;
import com.example.main.features.sendMoney.apiService.ApiTransfer;
import com.example.main.main.view.MainActivity;
import com.example.main.serviceModels.AccessToken;
import com.example.main.serviceModels.TransferCheckParams;
import com.example.main.serviceModels.TransferEligabiltyResponse;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QRTransferImpl extends AppCompatActivity {

    private String accessToken ;
    private String userName ;
    private  int amount ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrtransfer_impl);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && result.getContents() != null) {
            // get contents and send to manual
            String contents [] = result.getContents().split("/");
            userName =  contents[0];
            amount = Integer.parseInt(contents[1]);


            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    public void OnUseQr(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setCameraId(0);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setPrompt("scannig");
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setBarcodeImageEnabled(true);
        intentIntegrator.initiateScan();
    }

    public  void checkEligabilty(){
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
            TransferCheckParams transferCheckParams = new TransferCheckParams(userName , amount);
            //  Log.v("msg", "222" + code.getPhoneNumber());


            // api authentication service
            ApiTransfer apiTransfer = retrofit.create(ApiTransfer.class);
            AccessToken token = new AccessToken(accessToken);
            // start asking
            Call<TransferEligabiltyResponse> call = apiTransfer.checkEligabilty("Bearer " + token.getToken(), transferCheckParams);


// revceive response
            call.enqueue(new Callback<TransferEligabiltyResponse>() {
                @Override
                public void onResponse(Call<TransferEligabiltyResponse> call, Response<TransferEligabiltyResponse> response) {
                    Log.v("msg", "success");
                    int statusCode = response.code();
                    switch (statusCode) {
                        case 400:
                            // code block
                            Log.v("msg", "missing one or more parameters");
                            break;
                        case 401:
                            // code block
                            OnNotEnoughCredit();
                            Log.v("msg", "insufficient credit");
                            break;
                        case 404:
                            // code block
                            onNotFoundReceiver();
                            Log.v("msg", "receiver user not found ");
                            break;

                        case 200:
                            // code block
                            OnComplete(response.body());
                            Log.v("msg", "success " );
                            break;
                        default:
                            // code block
                            Log.v("msg", "error code " + statusCode);
                    }

                }

                @Override
                public void onFailure(Call<TransferEligabiltyResponse> call, Throwable t) {
                    Log.v("msg", "error  " + t.getMessage());
                }
            });
        }

    private void OnNotEnoughCredit() {

    }

    private void onNotFoundReceiver() {
    }

    private void OnComplete(TransferEligabiltyResponse body) {

    }
}



