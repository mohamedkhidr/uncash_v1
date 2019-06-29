package com.example.main.features.enquiry.map.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.example.main.R;
import com.example.main.features.enquiry.map.model.Model;
import com.example.main.features.enquiry.map.model.ModelImpl;
import com.example.main.serviceModels.LocationPoint;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class PresenterImpl implements Presenter {
    private final LatLng DEFAULT_LOCATION = new LatLng(30.0595581, 31.2934839);
    private List<LocationPoint> greenPoints;
    private List<LocationPoint> redPoints;
    private List<LocationPoint> locationPoints;
    private LatLng currentLocation;
    private int rechargeAmount;
    private GoogleMap googleMap;
    private Context context;
    private LatLng coordinates;
    private boolean mapReady = false;
    private boolean pointsReady = false;


    public PresenterImpl(LatLng currentLocation, int rechargeAmount, Context context) {
        locationPoints = new ArrayList<LocationPoint>();
        redPoints = new ArrayList<LocationPoint>();
        greenPoints = new ArrayList<LocationPoint>();
        this.currentLocation = currentLocation;
        this.rechargeAmount = rechargeAmount;
        this.context = context;
        Model model = new ModelImpl(this);


    }

    @Override
    public void setGreenRedPoints() {
        Log.v("Messagge :", "" + rechargeAmount);
        for (LocationPoint locationPoint : locationPoints) {
            if (locationPoint.getBalance() >= rechargeAmount) {
                Log.v("Messagge :", "" + rechargeAmount);

                greenPoints.add(locationPoint);
                Toast.makeText(context, "green : " + locationPoint.getBalance() + " " + rechargeAmount, Toast.LENGTH_SHORT).show();
            } else {
                redPoints.add(locationPoint);
                Toast.makeText(context, "red : " + locationPoint.getBalance() + " " + rechargeAmount, Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public List<LocationPoint> getRedPoints() {
        return redPoints;
    }

    @Override
    public List<LocationPoint> getGreenPoints() {
        return greenPoints;
    }

    @Override
    public void flyToDefaultLocation() {
        double lat = currentLocation.latitude;
        double lng = currentLocation.longitude;
        if (lat !=0 && lng !=0 ) {
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder().target(currentLocation).zoom(12f).build()));
        } else {
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder().target(DEFAULT_LOCATION).zoom(12f).build()));
        }
    }

    @Override
    public void spreadPointsOnMap() {
        for (LocationPoint greenLocationPoint : greenPoints) {

            googleMap.addMarker(new MarkerOptions().position(greenLocationPoint.
                    getCoordinates()).title(greenLocationPoint.getStoreName())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.green_marker)));
        }
        for (LocationPoint redLocationPoint : redPoints) {
            googleMap.addMarker(new MarkerOptions().position(redLocationPoint.
                    getCoordinates()).title(redLocationPoint.getStoreName()));
        }

    }

    @Override
    public void onGoogleMapRecieved(GoogleMap googleMap) {

        this.googleMap = googleMap;
        mapReady = true;
        if (pointsReady)
            renderMap();
    }


    @Override
    public void onGettingLocationPoints(List<LocationPoint> points) {
        locationPoints = points;
        pointsReady = true;
        if (mapReady)
            renderMap();
    }

    public void renderMap() {
        setGreenRedPoints();
        spreadPointsOnMap();
        flyToDefaultLocation();
    }
}
