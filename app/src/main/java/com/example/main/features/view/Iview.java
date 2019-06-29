package com.example.main.features.view;

public interface Iview {
    public void onLocationRecieved(String latitude, String longitude);

    public void goGetLocation();

    public void useDefaultLocation();
}
