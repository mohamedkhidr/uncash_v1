package com.example.main.features.enquiry.map.model;

import com.example.main.features.enquiry.enquiry.view.EnquiryParameters;
import com.example.main.features.enquiry.map.presenter.Presenter;
import com.example.main.serviceModels.LocationPoint;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
    private List<LocationPoint> locationPoints;
    private Presenter presenter;

    public ModelImpl(Presenter presenter) {
        this.presenter = presenter;
        locationPoints = new ArrayList<LocationPoint>();
        getPoints();
    }

    @Override
    public void connect() {

    }

    @Override
    public void disConnect() {

    }

    @Override
    public void checkConnectionSetting() {

    }

    @Override
    public void request() {

    }

    @Override
    public void response() {

    }

    @Override
    public void jsonParser() {

    }

    @Override
    public void getPoints() {
        ArrayList<LocationPoint> allPoints = new ArrayList<LocationPoint>();
        allPoints = EnquiryParameters.getAllPoints();
        presenter.onGettingLocationPoints(allPoints);

    }
}
