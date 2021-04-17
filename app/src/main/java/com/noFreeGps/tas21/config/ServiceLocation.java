package com.noFreeGps.tas21.config;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.noFreeGps.tas21.ui.VistaTransecto;

import java.util.concurrent.TimeUnit;

public class ServiceLocation extends Service  {

    public static final String START_LOCATION_SERVICE = "Iniciar Servio de localizacion";
    private LocationListener locationListener;
    private LocationManager locationManager;
    VistaTransecto vistaTransecto;
    private Thread thread;

    public static final String DATO_LATITUD = "datoLatitud";
    public static final String DATO_LONGITUD = "datoLongitud";
    private final String TAG = " XXXXXXXXXX ";
    public static final String DATO_ALTURA = "datoAltura";
    private Intent intentLocationData;
    String longitudString, latitudString, altura;
    double longituDouble;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        vistaTransecto = new VistaTransecto();

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {

                longitudString = String.valueOf(location.getLongitude());
                latitudString = String.valueOf(location.getLatitude());



                Toast toast = Toast.makeText(getApplicationContext(), "latitud: "+longitudString+ "Longitud: "+latitudString, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();





            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

                Intent intentProviderDisabled = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                intentProviderDisabled.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentProviderDisabled);
            }
        };
        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 2, locationListener);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

/*        Runnable runnable = new Runnable() {
            @Override
            public void run() {


                while(longituDouble !=0){

                    try {
                        TimeUnit.SECONDS.sleep(1);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        thread = new Thread(runnable);
        thread.start();*/
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(locationManager != null){
            locationManager.removeUpdates(locationListener);
        }
    }
}