package com.example.main.main.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.main.R;
import com.example.main.features.view.DashBoard;

public class FailedOperation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failed_operation);
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }
}
