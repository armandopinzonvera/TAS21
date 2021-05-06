package com.noFreeGps.tas21.config;


import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;

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

import android.widget.Toast;


import com.noFreeGps.tas21.R;
import com.noFreeGps.tas21.ui.VistaTransecto;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;


public class ServiceLocation extends Service {

    private static final String CHANNEL_ID = "Notificacion de canal de Localizacion";
    public static final String DATO_LONGITUD = "longitud";
    public static final String DATO_LATITUD = "latitud";
    public static final String DATO_ALTURA = "altura";
    DecimalFormat decimalFormat;
    private LocationManager locationManager;
    LocationListener locationListener;

    private String longitudString, latitudString, msnmString;
    private boolean checkLocation;
    private boolean isGpsActivo = false ;
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
        time = 200; // tiempo al que inicia

        decimalFormat = new DecimalFormat("#.#####");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        notificacionServicio();
        datosUbicacion();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if(checkLocation == true){
                    for (int i = 0; i < 36000; i++) {
                        try {
                            Thread.sleep(2000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (latitudString == null) {
                            updateUInullData();
                        } else {
                            try {
                                TimeUnit.SECONDS.sleep(5);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            time = time - 1;
                            updateUI(latitudString, longitudString);
                        }
                        // para continuar con la ubicacion luego que se hizo la verificacion de inicio
                        while (isGpsActivo) {
                            try {
                                TimeUnit.SECONDS.sleep(5);
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
            thread =new

            Thread(runnable);
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
                .setContentTitle(getString(R.string.notificacion_servicio_titulo))
                .setContentText(getString(R.string.notificacion_servicio_contenido))
                .setSmallIcon(R.drawable.ic_gps_fixed_black_24dp)
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
                msnmString = String.valueOf(location.getAltitude());

                Toast.makeText(ServiceLocation.this, "Lat: "+isGpsActivo , Toast.LENGTH_SHORT).show();
            }
        };

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, locationListener);
            isGpsActivo = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
/*************************************************************/
/*************************************************************/
    private void updateUI(String latitudString, String longitudString) {

        intentBroadcast.putExtra(DATO_LATITUD, latitudString);
        intentBroadcast.putExtra(DATO_LONGITUD, longitudString);
        intentBroadcast.putExtra(DATO_ALTURA, msnmString);

        sendBroadcast(intentBroadcast);  // para enviar la info
    }

   // Para usar en cuando aun no se obtienen los datos de ubicacion
    private void updateUInullData() {
        intentBroadcast.putExtra(DATO_LATITUD, getString(R.string.buscando));
        intentBroadcast.putExtra(DATO_LONGITUD,  getString(R.string.buscando));
        sendBroadcast(intentBroadcast);  // para enviar la info
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