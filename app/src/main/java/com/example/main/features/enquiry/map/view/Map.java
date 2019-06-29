package com.example.main.features.enquiry.map.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.main.R;
import com.example.main.features.enquiry.map.presenter.Presenter;
import com.example.main.features.enquiry.map.presenter.PresenterImpl;

import com.example.main.features.view.DashBoard;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class Map extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Presenter presenter;
    private int rechargeAmount;
    private LatLng currentLocation;
    private Bundle extras;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        extras = getIntent().getExtras();
        if (extras != null) {
            rechargeAmount = extras.getInt("rechargeAmount");
        }
        currentLocation = DashBoard.getLocationCoordinates();
        presenter = new PresenterImpl(currentLocation,rechargeAmount,this);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        presenter.onGoogleMapRecieved(mMap);
    }


}
