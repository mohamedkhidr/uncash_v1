package com.example.main.signup.stroreCreation.Location.model;

import com.google.android.gms.location.LocationRequest;

public interface Model {
    public void onPermissionRequestCompleted(boolean b);

    public void onLocationSettingChecked(boolean b);
    public void checkLocationRequirments(String permission, LocationRequest locationRequest);
}
