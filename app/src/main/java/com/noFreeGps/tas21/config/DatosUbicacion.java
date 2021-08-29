package com.noFreeGps.tas21.config;
/**
*  Esta clase Crea los datos de Location (longitud, latitud, altura)
*  utiliza dos metodos, uno que se base en el mejor proveedor: "getBestProvider()"
*  y el otro directamente para GPS
*  ambos metodos retornan los valores como Arrays de String, ya con el formato
 *  para los decimales
 *
*
* */
import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import java.text.DecimalFormat;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

class DatosUbicacion {


    Context context;
    DecimalFormat decimalFormat1, decimalFormat2;
    String[] datosLocationStringArray;
    boolean isGpsActivo, isNetworkActivo;
    String longitudString, latitudString, msnmString;
    String provedorGPSString;
    LocationManager locationManager;
    LocationListener locationListener;
    Criteria criteria;
    String bestProviderString;
    Location location;

    public DatosUbicacion(Context context) {
        this.context = context;

        locationManager = (LocationManager) context.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        decimalFormat1 = new DecimalFormat("#.####");
        decimalFormat2 = new DecimalFormat("#.#");
        datosLocationStringArray = new String[3];

    }

    public void verificarProvedor() {

        isGpsActivo = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
        isNetworkActivo = locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER);

    }


    public String[] bestProvider() {

        longitudString = " ";
        latitudString = " ";
        msnmString = " ";

        bestProviderString = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {
            }

        };

        locationManager.requestLocationUpdates(bestProviderString, 5000, 5, locationListener);
        location = locationManager.getLastKnownLocation(bestProviderString);

        longitudString = decimalFormat1.format(location.getLongitude());
        latitudString = decimalFormat1.format(location.getLatitude());
        msnmString = decimalFormat2.format(location.getAltitude());

        String[] datosLocationStringArray = {longitudString, latitudString, msnmString};

        return datosLocationStringArray;

    }

    public String[] gpsProvider() {

        provedorGPSString = locationManager.GPS_PROVIDER;
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {

                longitudString = decimalFormat1.format(location.getLongitude());
                latitudString = decimalFormat1.format(location.getLatitude());
                msnmString = decimalFormat2.format(location.getAltitude());

            }
        };

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 5000, 5, locationListener);

         String[] datosLocationStringArray = {longitudString, latitudString, msnmString};

         return datosLocationStringArray;
    }



}
