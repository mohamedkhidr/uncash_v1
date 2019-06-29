package com.example.main.features.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.main.R;
import com.example.main.features.enquiry.enquiry.view.EnquiryParameters;
import com.example.main.features.location.model.setting.LocationSettingHandlerImpl;
import com.example.main.features.location.model.setting.PermissionHandlerImpl;
import com.example.main.features.presenter.PresenterImpl;
import com.google.android.gms.maps.model.LatLng;


public class DashBoard extends AppCompatActivity implements com.example.main.features.view.Iview {

    public static LatLng centeralLocation;
    private Iview iview;
    private PresenterImpl presenter;
    private Context context;
    private Activity activity;
    private String accessToken;

    public static LatLng getLocationCoordinates() {
        return centeralLocation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        accessToken = getIntent().getStringExtra("accessToken");
        context = this;
        activity = this;
        iview = this;
        Log.v("Message : ", "oncreate");


        // default location of out company
        centeralLocation = new LatLng(30.0595581, 31.2934839);
    }

    public void OnBranchesButtonClicked(View view) {

        checkLocationAvailablity(context, activity);

    }

    // return the current location coordinates of the device used by a map

    // check if we can get the location
    public void checkLocationAvailablity(Context context, Activity activity) {
        presenter = new PresenterImpl(context, activity, iview);
        presenter.checkLocationRequirments();
    }


    // called if the device managed to get the current location

    @Override
    public void onLocationRecieved(String latitude, String longitude) {
        Log.v("Message : ", "location recived");
        double lat = Double.parseDouble(latitude);
        double lng = Double.parseDouble(longitude);
        centeralLocation = new LatLng(lat, lng);
        Log.v("Message : ", centeralLocation.toString());

    }

    @Override

    // get the current location of the device
    public void goGetLocation() {
        //branches feature
        Intent secondActivity = new Intent(this, EnquiryParameters.class);
        secondActivity.putExtra("accessToken" , accessToken);
        startActivity(secondActivity);
        // get location in the background
        LocationFinderTask task = new LocationFinderTask();
        task.execute();

    }

    @Override

    // can't get the current location so use the default one
    public void useDefaultLocation() {
        //branches feature
        Intent secondActivity = new Intent(this, EnquiryParameters.class);
        startActivity(secondActivity);
    }


    /*
     * called to handle the request permission operation
     * @param requestCode  the code used to handle the request with
     * @param permissions  a list of permissions to return the result of requesting them
     * @param grantResults  a list of granted permissions


     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.v("Message : ", "permission result");
        PermissionHandlerImpl.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
        LocationSettingHandlerImpl.onActivityResult(requestCode, resultCode, data);
    }


    // async task inner class to execute the location process in the background thread

    private class LocationFinderTask extends AsyncTask<Context, Void, Void> {

        @Override
        protected Void doInBackground(Context... contexts) {
            presenter.startGettingLocation();
            Log.v("Message : ", "background method");
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            Log.v("Message : ", "onPost");
        }
    }

}


