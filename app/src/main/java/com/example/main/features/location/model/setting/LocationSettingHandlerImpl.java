package com.example.main.features.location.model.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import static android.app.Activity.RESULT_OK;

public class LocationSettingHandlerImpl implements LocationSettingHandler {
    private static LocationRequest locationRequest;
    private static Activity activity;
    private static Context context;
    private static LocationSettingsRequest.Builder mLocationSettingsRequestBulider;
    private static SettingsClient mSettingsClient;
    private static Model iModel;
    private static final int REQUEST_CHECK_SETTINGS = 10;


    /*
     * Check if all location settings are satisfied
     */
    public static void checkLocationSetting(Context settingContext, Activity settingActivity, LocationRequest settingLocationRequest, Model settingModel) {
        context = settingContext;
        activity = settingActivity;
        locationRequest = settingLocationRequest;
        iModel = settingModel;
        Log.v("Message : ", "check location setting ");
        mLocationSettingsRequestBulider = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

        mSettingsClient = LocationServices.getSettingsClient(activity);


        Task<LocationSettingsResponse> task = mSettingsClient.checkLocationSettings(mLocationSettingsRequestBulider.build());
        Log.v("Message : ", "check location setting  2");
        //All settings are fine
        task.addOnSuccessListener(activity, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // All location settings are satisfied. The client can initialize
                // location requests here.
                // ...
                Log.v("Message : ", "location setting  checked");
                iModel.onLocationSettingChecked(true);


            }
        });

        task.addOnFailureListener(activity, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.

                    try {

                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        //prompt the user to enable the unsatisfied settings
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(activity, REQUEST_CHECK_SETTINGS);
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
            }
        });

    }


    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                Log.v("Message : ", "setting checked method");
                iModel.onLocationSettingChecked(true);


                // Do something with the contact here (bigger example below)
            } else {
                LocationSettingHandlerImpl.checkLocationSetting(context, activity, locationRequest, iModel);


            }

        }
    }

}
