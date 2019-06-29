package com.example.main.features.location.model.setting;

import android.app.Activity;
import android.content.Context;
import android.util.Log;


import com.example.main.features.presenter.Presenter;
import com.example.main.features.presenter.PresenterImpl;
import com.google.android.gms.location.LocationRequest;

public class ModelImpl implements Model {


    private PresenterImpl presenterImpl;
    private Presenter presenter;
    private Context context;
    private Activity activity;
    private LocationRequest locationRequest;


    public ModelImpl(Presenter iPresenter, Context context, Activity activity) {
        this.activity = activity;
        this.context = context;
        this.presenter = iPresenter;
        Log.v("Message : ", "model method");
    }

    /*
     * check the permission and the location settings
     */

    @Override
    public void checkLocationRequirments(String permission, LocationRequest locationRequest) {
        Log.v("Message : ", "check location req ");
        this.locationRequest = locationRequest;

        PermissionHandlerImpl.requestPermission(this, context, activity, permission);


    }

    public void checkLocationSetting(LocationRequest locationRequest) {
        Log.v("Message : ", "location setting ");
        LocationSettingHandlerImpl.checkLocationSetting(context, activity, locationRequest, this);
    }

    /*
     * called when requesting permission operation completed
     */
    public void onPermissionRequestCompleted(boolean result) {
        if (result) {
            Log.v("Message : ", "ask for setting ");
            checkLocationSetting(locationRequest);
        }
        else
            presenter.sendLocationStatus(false);


    }
    /*
     * called after checking location setting
     */
    public void onLocationSettingChecked(boolean result) {
        if (result)
            presenter.sendLocationStatus(true);
        else
            presenter.sendLocationStatus(false);
    }


}
