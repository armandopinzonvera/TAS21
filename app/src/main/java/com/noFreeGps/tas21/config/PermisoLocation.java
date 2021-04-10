package com.noFreeGps.tas21.config;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermisoLocation {


    public int LOCATION_REQUEST_CODE;
    private final Context context;

    public PermisoLocation(Context context) {
        this.context = context;
    }



    public boolean verificacionInicialPermiso(){

        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }

    }
    public void solicitarPermisoLocation(int requestCode){

        this.LOCATION_REQUEST_CODE = requestCode;

        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.ACCESS_FINE_LOCATION)){

                Log.d("", "        YOU SHOULD SHOW A ALERT DIALOG      ");
                ActivityCompat.requestPermissions((Activity)context, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            }else{
                ActivityCompat.requestPermissions((Activity)context, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            }
        }
    }

    public boolean verificacionFinalPermisoLocation( int requestCode, int[] grantResult){
        if(requestCode == LOCATION_REQUEST_CODE){
            if(grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_DENIED){
                return true;
            }
        }else{
            return false;
        }

        return false;
    }



}