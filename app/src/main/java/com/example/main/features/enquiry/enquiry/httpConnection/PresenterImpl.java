package com.example.main.features.enquiry.enquiry.httpConnection;

import android.content.Context;
import android.util.Log;

import com.example.main.features.enquiry.enquiry.view.Iview;
import com.example.main.serviceModels.LocationPoint;
import com.example.main.signup.accountCreation.httpConnection.CreateAccount;
import com.example.main.signup.accountCreation.view.CreateUser;

import java.util.ArrayList;

public class PresenterImpl implements Presenter {
    private Iview iview;
    private Context context ;
    private GetPoints getPoints ;
    private String accessToken ;


    public PresenterImpl(Iview iview) {
        this.iview = iview;
    }

    public void getAllPoints(Context context ,  String accessToken) {
        this.context = context;
        this.accessToken = accessToken;


        Log.v("msg" , "presenter signup");
        getPoints = new GetPoints(this , context , accessToken);
        getPoints.exceute();

    }
    @Override
    public void OnComplete(ArrayList<LocationPoint> allPoints) {
        iview.OnGettingLocationPoints(allPoints);
    }
}
