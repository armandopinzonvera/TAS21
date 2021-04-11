package com.noFreeGps.tas21.config;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class ServiceLocation extends Service implements LocationListener {

    private LocationManager locationManager;

    public static final String START_LOCATION_SERVICE = "Iniciar localizacion";
    public static final String FINAL_LOCATION_SERVICE = "Finalizar localizacion";

    public ServiceLocation() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Toast toast = Toast.makeText(getApplicationContext(), "SERVICE LOCATION", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();


        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();





    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}