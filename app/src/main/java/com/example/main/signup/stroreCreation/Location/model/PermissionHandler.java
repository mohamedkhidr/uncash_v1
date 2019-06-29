package com.example.main.signup.stroreCreation.Location.model;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

public class PermissionHandler {



    private static boolean isGranted = false;
    private static final int PERMISSION_REQUEST_CODE = 10;
    private static Context context;
    private static Activity activity;
    private static String permission;
    private static boolean isCompleted = false;
    private static Model model;


    /*
    @return true if the permission is granted
     */
    public static boolean isPermissionGranted() {
        return isGranted;
    }

    /*

        @param permissonContext the context of the activity
        @param permissionActivity the activity to take permission at
        @param accessPermission the permission you want to request
         request permission and check if it's granted or not
         */

    public static void requestPermission(Model iModel, Context permissionContext, Activity permissionActivity, String accessPermission) {
        context = permissionContext;
        activity = permissionActivity;
        permission = accessPermission;
        model = iModel;

        Toast.makeText(context, "Permission requested ", Toast.LENGTH_LONG).show();
        if (ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            isGranted = true;
            model.onPermissionRequestCompleted(true);

        } else {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, PERMISSION_REQUEST_CODE);
        }

    }


    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        Toast.makeText(context, "Permission result handling", Toast.LENGTH_LONG).show();
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    isGranted = true;
                    model.onPermissionRequestCompleted(true);
                    Toast.makeText(context, "permission granted", Toast.LENGTH_LONG).show();


                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                        new AlertDialog.Builder(context).
                                setTitle("Location permission needed").
                                setMessage("You need to grant location permission to create a store" +
                                        "  feature. Retry and grant it !").
                                setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestPermission(model,context, activity, permission);
                                    }
                                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                model.onPermissionRequestCompleted(false);
                                Toast.makeText(context, "Permission Denied", Toast.LENGTH_LONG).show();
                            }
                        }).create().show();
                    } else {
                        new AlertDialog.Builder(context).
                                setTitle("Location permission needed").
                                setMessage("You need to grant location permission to create a store" +
                                        "  feature. go to setting to grant it").
                                setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //open setting
                                        model.onPermissionRequestCompleted(false);
                                        Toast.makeText(context, " open setting ", Toast.LENGTH_LONG).show();
                                    }
                                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                model.onPermissionRequestCompleted(false);
                                Toast.makeText(context, "Permission Denied", Toast.LENGTH_LONG).show();
                            }
                        }).create().show();
                    }
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }

            }
            break;

            // other 'case' lines to check for other
            // permissions this app might request.
        }


    }
    /*
     * @return true if the permission is granted
     */
    public static boolean isPermissionRequestCompleted () {
        return isCompleted;
    }
}



