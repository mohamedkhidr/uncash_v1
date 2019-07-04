package com.example.main.features.sendMoney.useQR;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.main.R;
import com.example.main.features.view.DashBoard;
import com.example.main.main.view.MainActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenerateQR extends AppCompatActivity {
    private String userName ;
    private String accessToken ;
    private int amount ;
    private ImageView imageView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);
        imageView = (ImageView) findViewById(R.id.qr_image);
        Intent intent = getIntent() ;
        userName = intent.getStringExtra("userName");
        amount = intent.getIntExtra("amount" , -1) ;
        String text = userName +"/"+amount ;
        if(text != null ){
            try {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                BitMatrix bitMatrix = multiFormatWriter.encode(text , BarcodeFormat.QR_CODE , 500 , 500);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder() ;
                Bitmap bitmap  = barcodeEncoder.createBitmap(bitMatrix);
                imageView.setImageBitmap(bitmap);
            }catch (WriterException e){
                e.printStackTrace();
            }
        }

    }

    public void goHome(View view) {
        Intent intent = new Intent(this , DashBoard.class);
        startActivity(intent);
    }
}
