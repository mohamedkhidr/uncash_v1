package com.example.main.features.enquiry.enquiry.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.main.R;
import com.example.main.features.enquiry.enquiry.httpConnection.Presenter;
import com.example.main.features.enquiry.enquiry.httpConnection.PresenterImpl;
import com.example.main.features.enquiry.map.view.Map;
import com.example.main.serviceModels.LocationPoint;
import com.example.main.validator.AmountField;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class EnquiryParameters extends AppCompatActivity implements Iview {


    public static ArrayList<LocationPoint> allPoints;
    private final String FETCH_POINTS_API = "http://192.168.1.3:8080/api/getPoints";
    ProgressDialog loadingDialog;
    private EditText txtIntput;
    private int rechargeAmount;
    private PresenterImpl presenter;
    private String accessToken;
    private boolean isRunnig = false;
    private boolean isDataHere;

    public static ArrayList<LocationPoint> getAllPoints() {
        return allPoints;
    }


    // the getLocation button listener

    @Override

    // on App creation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_parameters);
        txtIntput = findViewById(R.id.txtInput);
        accessToken = getIntent().getStringExtra("accessToken");
        Toast.makeText(this, " onCreate", Toast.LENGTH_LONG).show();
        presenter = new PresenterImpl(this);
        isRunnig = true;
        isDataHere = false;
        presenter.getAllPoints(this, accessToken);
        allPoints = new ArrayList<LocationPoint>();



    }

    public void OnSubmit(android.view.View view) {
        String rechargeAmount_text = txtIntput.getText().toString();
        //validates recharge aomunt field
        AmountField amountField = new AmountField(this, txtIntput);
        if (isNetworkConnected()) {
            if (amountField.validate()) {
                double dAmount = Double.parseDouble(rechargeAmount_text);
                rechargeAmount = (int) dAmount;
                isDataHere = true;

                if (isRunnig) {
                    Log.v("Message : ", "" + rechargeAmount);
                    loadingDialog = ProgressDialog.show(this, "Loading", "Please wait...", true);
                } else if (!isRunnig) {
                    Log.v("Message"  , "date");

                    Intent thirdActivity = new Intent(this, Map.class);
                    thirdActivity.putExtra("rechargeAmount", rechargeAmount);
                    startActivity(thirdActivity);

                }

                // geting all available recharge points in the background thread


            }
        } else {
            Toast.makeText(this, "Check internet connectivity", Toast.LENGTH_SHORT).show();
        }


    }


    //check Network connectivity
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    @Override
    public void OnGettingLocationPoints(ArrayList<LocationPoint> points) {
        Log.v("Message"  , "points");
        allPoints = points;
        isRunnig = false;
        if (isDataHere) {
            loadingDialog.dismiss();
            Intent thirdActivity = new Intent(this, Map.class);
            thirdActivity.putExtra("rechargeAmount", rechargeAmount);
            startActivity(thirdActivity);
            Log.v("Message : ", "onPost");
        }
    }



    // async task inner class to execute the location process in the background thread
/*
    private class FetchPointsTask extends AsyncTask<URL, Void, Void> {

        @Override
        protected Void doInBackground(URL... urls) {
            Log.v("Message :", "background 2 ");
            // Create URL object
            URL url = createUrl(FETCH_POINTS_API);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
                Log.v("Error", "Http Connection error " + e.getMessage());
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
            extractFeatureFromJson(jsonResponse);

            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            Context context = getApplicationContext();
            loadingDialog.dismiss();
            Intent thirdActivity = new Intent(context, Map.class);
            thirdActivity.putExtra("rechargeAmount", rechargeAmount);
            startActivity(thirdActivity);
            Log.v("Message : ", "onPost");
        }


        private URL createUrl(String stringUrl) {
            Log.v("Message :", "create url ");
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Log.e("Error", "Error with creating URL", exception);
                return null;
            }
            return url;
        }


        private String makeHttpRequest(URL url) throws IOException {
            Log.v("Message :", "http connection ");
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 );
                urlConnection.setConnectTimeout(15000 );
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } catch (IOException e) {
                // TODO: Handle the exception
                Log.v("Error", "Http Connection error " + e.getMessage());
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            Log.v("Message :", "connection done ");
            return jsonResponse;
        }


        private String readFromStream(InputStream inputStream) throws IOException {
            Log.v("Message :", "read stream ");
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }


        private ArrayList<LocationPoint> extractFeatureFromJson(String locationPointJSON) {
            Log.v("Message :", "extreact info ");
            try {
                Log.v("Message :", "start extreact info ");
                JSONObject pointsJSON = new JSONObject(locationPointJSON);
                JSONArray pointsArray = pointsJSON.getJSONArray("Points");
                JSONObject point = null;

                // If there are results in the features array
                int pointsArrayLength = pointsArray.length();

                if (pointsArrayLength > 0) {
                    Log.v("Message :", "extreact now");
                    for (int i = 0; i < pointsArrayLength; i++) {
                        point = pointsArray.getJSONObject(i);
                        String name = point.getString("name");

                        String phone = point.getString("phone");

                        double latitude = Double.parseDouble(point.getString("latitude"));

                        double longitude = Double.parseDouble(point.getString("longitude"));

                        int balance = point.getInt("balance");

                        // Create a new {@link Event} object
                        allPoints.add(new LocationPoint(latitude, longitude, balance, name, phone));

                    }
                }
            } catch (JSONException e) {
                Log.e("Error", "Problem parsing the  JSON results", e);
            }
            return allPoints;
        }
    } */
}












