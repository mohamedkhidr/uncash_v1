package com.example.main.features.enquiry.map.presenter;

import com.example.main.serviceModels.LocationPoint;


import com.google.android.gms.maps.GoogleMap;

import java.util.List;

public interface Presenter {
    public void setGreenRedPoints();
    public List<LocationPoint> getRedPoints();
    public List<LocationPoint> getGreenPoints();
    public void flyToDefaultLocation();
    public void spreadPointsOnMap();
    public void onGoogleMapRecieved(GoogleMap googleMap);
    public void onGettingLocationPoints(List<LocationPoint> points);

}
