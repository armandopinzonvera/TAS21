package com.noFreeGps.tas21.config;


import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.annotation.SuppressLint;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Build;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.noFreeGps.tas21.R;
import com.noFreeGps.tas21.ui.VistaTransecto;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;


public class ServiceLocation extends Service {

    private static final String CHANNEL_ID = "Notificacion de canal de Localizacion";
    public static final String DATO_LONGITUD = "longitud";
    public static final String DATO_LATITUD = "latitud";
    DecimalFormat decimalFormat;
    private LocationManager locationManager;
    LocationListener locationListener;

    private String longitudString, latitudString;
    private boolean checkTimer;
    public static final String INTENT_RECEIVER = "intent_receiver";

    PendingIntent pendingIntent;
    private Intent intent;
    private int time;
    private Thread thread;

    public ServiceLocation() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        checkTimer = true;  // para solo tener un servicio activo
        intent = new Intent(INTENT_RECEIVER);
        time = 200; // tiempo al que inicia

        decimalFormat = new DecimalFormat("#.#####");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        notificacionServicio();   /*************************/
        datosUbicacion(); /*************************/
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (time > 0) {
                    if (checkTimer == true) {
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        time = time - 1;
                        updateUI(latitudString, longitudString);
                    } else {
                        break;
                    }
                }
                // showNotification();
                stopSelf();
            }
        };
        thread = new Thread(runnable);
        thread.start();

        return Service.START_STICKY;
    }
/*************************************************************/
    /*************************************************************/

    private void notificacionServicio() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Location Service", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }
        Intent resultIntent = new Intent(this, VistaTransecto.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("TITULO")
                .setContentText("Contenido")
                .setSmallIcon(R.drawable.ic_down)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
    }

/*************************************************************/
    /*************************************************************/

    private void datosUbicacion() {

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                longitudString = decimalFormat.format(location.getLongitude());
                latitudString = decimalFormat.format(location.getLatitude());

                //Toast.makeText(ServiceLocation.this, "Lat: "+latitudString , Toast.LENGTH_SHORT).show();
            }
        };

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, locationListener);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
/*************************************************************/
    /*************************************************************/
    private void updateUI(String latitudString, String longitudString) {
        intent.putExtra(DATO_LATITUD, latitudString);
        intent.putExtra(DATO_LONGITUD, longitudString);
        sendBroadcast(intent);  // para enviar la info

      /*
      intent.putExtra(DATO_TIEMPO, String.valueOf(time));
      sendBroadcast(intent);  // para enviar la info
        */
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        checkTimer = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}