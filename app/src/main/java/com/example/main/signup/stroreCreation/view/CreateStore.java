package com.example.main.signup.stroreCreation.view;

public interface CreateStore {
    public void onNoLocationRecieved();

    public  void onLocationRecieved(String latitude, String longitude);

    public void goGetLocation();

     public void OnSuccess();
}
