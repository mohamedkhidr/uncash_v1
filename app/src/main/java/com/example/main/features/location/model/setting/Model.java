package com.example.main.features.location.model.setting;

import com.google.android.gms.location.LocationRequest;

public interface Model {
    void checkLocationRequirments(String permission, LocationRequest locationRequest);


    void onPermissionRequestCompleted(boolean result);

    void onLocationSettingChecked(boolean result);

}
