package com.noFreeGps.tas21.config;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import android.widget.Toast;


import com.noFreeGps.tas21.R;
import com.noFreeGps.tas21.ui.MapsFragment;
import com.noFreeGps.tas21.ui.VistaTransecto;

import java.text.DecimalFormat;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class ServiceLocation extends Service {

    public static final String DATO_LONGITUD = "longitud";
    public static final String DATO_LATITUD = "latitud";
    public static final String DATO_ALTURA = "altura";

    DatosUbicacion datosUbicacion;
    String[] datosLocationStringArray;

    Intent intentLocationData;

    private String longitudString, latitudString, msnmString;
    private double longitudDouble, latitudDouble;

    private boolean checkLocation;
    private boolean isGpsActivo = false;
    public static final String INTENT_RECEIVER = "intent_receiver";

    private Intent intentBroadcast;

    private int time;
    private Thread thread;

    public ServiceLocation() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        checkLocation = true;  // para solo tener un servicio activo
        intentBroadcast = new Intent(INTENT_RECEIVER);
        intentLocationData = new Intent(getApplicationContext(), MapsFragment.class);

        time = 200; // tiempo al que inicia

        datosUbicacion = new DatosUbicacion(this);
        datosLocationStringArray = new String[3];
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        NotificacionServicio notificacionServicio = new NotificacionServicio(this);
        startForeground(1, notificacionServicio.notificarServicio());

       datosLocationStringArray =  datosUbicacion.bestProvider();
       longitudString = datosLocationStringArray[0];
       latitudString = datosLocationStringArray[1];
       msnmString = datosLocationStringArray[2];

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if (checkLocation == true) {

                    while (checkLocation == true) {
                        try {
                            Thread.sleep(2000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (latitudString == null) {
                            updateUInullData();
                        } else {

                            try {
                                TimeUnit.SECONDS.sleep(2);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            time = time - 1;
                            updateUI(latitudString, longitudString);

                        }
                        // para continuar con la ubicacion luego que se hizo la verificacion de inicio
                        while (isGpsActivo) {
                            try {
                                TimeUnit.SECONDS.sleep(2);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            time = time - 1;
                            updateUI(latitudString, longitudString);
                        }
                    }
                    //stopSelf();
                }
            } //cierre primer if
        };
        thread = new

                Thread(runnable);
        thread.start();

        return Service.START_STICKY;
    }



/********************************************/
    /********************************************/


    private void updateUI(String latitudString, String longitudString) {

        intentBroadcast.putExtra(DATO_LATITUD, latitudString);
        intentBroadcast.putExtra(DATO_LONGITUD, longitudString);
        intentBroadcast.putExtra(DATO_ALTURA, msnmString);

        sendBroadcast(intentBroadcast);  // para enviar la info

        intentLocationData.putExtra("latitud_map", latitudDouble);
        intentLocationData.putExtra("longitud_map", longitudDouble);

    }

    // Para usar en cuando aun no se obtienen los datos de ubicacion
    private void updateUInullData() {

        intentBroadcast.putExtra(DATO_LATITUD, "buscando");
        intentBroadcast.putExtra(DATO_LONGITUD, "buscando");
        intentBroadcast.putExtra(DATO_ALTURA, msnmString);

        sendBroadcast(intentBroadcast);  // para enviar la info

        intentLocationData.putExtra("latitud_map", latitudDouble);
        intentLocationData.putExtra("longitud_map", longitudDouble);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        checkLocation = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}