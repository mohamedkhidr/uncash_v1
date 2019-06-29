package com.example.main.features.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.main.features.location.model.setting.ModelImpl;
import com.example.main.features.location.model.setting.PermissionHandlerImpl;
import com.example.main.features.view.Iview;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class PresenterImpl implements Presenter , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener{
    private Context context;
    private Activity activity;
    private ModelImpl model;
    private PermissionHandlerImpl permissionHandlerImpl;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private String latitude;
    private String longitude;
    private Iview iview;


    public PresenterImpl(Context context, Activity activity, Iview iview) {
        this.activity = activity;
        this.context = context;
        this.iview = iview;
        Log.v("Message : ", "presenter method");
        mLocationRequest = createLocationRequest();

    }

    /*
    check location requirments if they are satisfied
    main method one
     */
    public void checkLocationRequirments() {
        model = new ModelImpl(this, context, activity);
        model.checkLocationRequirments(Manifest.permission.ACCESS_FINE_LOCATION, mLocationRequest);

    }

    @Override

    // called by the model to send the result to the main activity


    public void sendLocationStatus(boolean status) {
        if (status)
            // available
            iview.goGetLocation();
        else {
            // not available
            iview.useDefaultLocation();
        }
    }


    /*
    start getting location
    main method 2
     */
    public void startGettingLocation() {
        Log.v("Message : ", "start get loc method");

        mGoogleApiClient = createGoogleApiClient();
        mGoogleApiClient.connect();


    }

    public GoogleApiClient createGoogleApiClient() {
        Log.v("Message : ", "google api client");
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        return mGoogleApiClient;
    }


    public LocationRequest createLocationRequest() {
        LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // set the periority to the accuracy of the location.
        mLocationRequest.setInterval(10000);// update location every 1 second.
        mLocationRequest.setFastestInterval(5000);

        return mLocationRequest;
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.v("Message : ", "connected");

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        } else {
            //no location

        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        // receive location here

        latitude = Double.toString(location.getLatitude());
        longitude = Double.toString(location.getLongitude());

        // call onLocationRecieved after recieving the location
        Log.v("Message : ", "location is here ");
        iview.onLocationRecieved(latitude, longitude);
        mGoogleApiClient.disconnect();


    }

}
