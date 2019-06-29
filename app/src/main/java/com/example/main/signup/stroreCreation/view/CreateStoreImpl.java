package com.example.main.signup.stroreCreation.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.main.R;
import com.example.main.main.view.SucceededOperation;
import com.example.main.signup.stroreCreation.Location.model.LocationSettingHandler;
import com.example.main.signup.stroreCreation.Location.model.PermissionHandler;
import com.example.main.signup.stroreCreation.Location.presenter.PresenterImpl;
import com.example.main.signup.stroreCreation.httpConnection.Presenter;
import com.example.main.validator.Network;
import com.example.main.validator.PasswordField;
import com.example.main.validator.PhoneNumberField;
import com.example.main.validator.RoleRadioButton;
import com.example.main.validator.UserField;

public class CreateStoreImpl extends AppCompatActivity implements CreateStore {
    private EditText nameEditText;
    private EditText phoneEditText;
    private String storeName ;
    private String storePhone ;
    private String userName;
    private String accessToken ;
    private ProgressDialog dialog;
    private String latitude ;
    private String longitude ;
    private boolean isRunning = false;
    private boolean isLocationHere ;
    private boolean isDataeHere ;
    private com.example.main.signup.stroreCreation.httpConnection.PresenterImpl storePresenter;
    private com.example.main.signup.stroreCreation.Location.presenter.PresenterImpl locationPresenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_store);
        checkLocationAvailablity(this ,this);
        storePresenter = new com.example.main.signup.stroreCreation.httpConnection.PresenterImpl(this);
        isRunning = false ;
        isLocationHere = false;
        isDataeHere = false;
        nameEditText = (EditText) findViewById(R.id.nameText);
        phoneEditText = (EditText) findViewById(R.id.phoneText);
        Intent intent =getIntent();
        accessToken = intent.getStringExtra("accessToken");
        userName = intent.getStringExtra("userName");

    }

    public void OnSubmit(View view) {

        Network network = new Network(this);
        if (network.isNetworkConnected()) {

            UserField userField = new UserField(nameEditText);
            PhoneNumberField phoneNumberField = new PhoneNumberField(phoneEditText);


            if (userField.validate() && phoneNumberField.validate()) {

                storePhone = phoneEditText.getText().toString();
                storeName = nameEditText.getText().toString();
                isDataeHere = true;
                if (isLocationHere) {
                    storePresenter.createStore(this , latitude ,longitude , storeName , storePhone , accessToken , userName);
                    if (isRunning) {
                        dialog = new ProgressDialog(this);
                        dialog.setTitle("Loading");
                        dialog.setMessage("Please wait ");
                        dialog.show();
                    }
                }


            }
        }else {

                Toast.makeText(this, "Check Network Connectivity", Toast.LENGTH_SHORT).show();
            }


    }
    public void checkLocationAvailablity(Context context, Activity activity) {
        locationPresenter = new PresenterImpl(context, activity, this);
    }

    @Override

    // get the current location of the device
    public void goGetLocation() {

        LocationFinderTask task = new LocationFinderTask();
        task.execute();
        isRunning = true;

    }

    @Override
    public void OnSuccess() {
        Intent intent = new Intent(this , SucceededOperation.class);
        startActivity(intent);
    }

    @Override
    public void onNoLocationRecieved() {
        Toast.makeText(this, "Some thing went wrong please Check Network Connectivity and permission settings" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationRecieved(String latitude, String longitude) {
        isLocationHere = true;
        this.latitude = latitude;
        this.longitude = longitude;
        if (isDataeHere) {
            storePresenter.createStore(this  , latitude , longitude , storeName , storePhone , accessToken , userName);
        }
    }



        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            Log.v("Message : ", "permission result");
            PermissionHandler.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        /*

         * called to handle the check stting operation
         * @param requestCode  the code used to handle the request with
         * @param resultCode  a list of permissions to return the result of requesting them
         * @param intent  a list of granted permissions


         */

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            Log.v("Message : ", "activity result");
            LocationSettingHandler.onActivityResult(requestCode, resultCode, data);
        }


        // async task inner class to execute the location process in the background thread

        private class LocationFinderTask extends AsyncTask<Context, Void, Void> {

            @Override
            protected Void doInBackground(Context... contexts) {
                locationPresenter.startGettingLocation();
                Log.v("Message : ", "background method");
                return null;
            }

            @Override
            protected void onPostExecute(Void v) {
                Log.v("Message : ", "onPost");
            }
        }



}
