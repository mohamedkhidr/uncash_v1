package com.example.main.features.presenter;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

public interface Presenter {
    void startGettingLocation();

    public GoogleApiClient createGoogleApiClient();

    public LocationRequest createLocationRequest();


    public void sendLocationStatus(boolean status);

}

