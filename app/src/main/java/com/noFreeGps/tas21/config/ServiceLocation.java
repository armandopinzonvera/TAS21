package com.noFreeGps.tas21.config;

<<<<<<< HEAD
import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
=======
import android.annotation.SuppressLint;
>>>>>>> f6484fb83ed74509b62c0ba96af59b680c77dd33
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
<<<<<<< HEAD
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
=======
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
>>>>>>> f6484fb83ed74509b62c0ba96af59b680c77dd33

import com.noFreeGps.tas21.R;
import com.noFreeGps.tas21.ui.VistaTransecto;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

<<<<<<< HEAD
public class ServiceLocation extends Service {

    private static final String CHANNEL_ID = "Notificacion de canal de Localizacion";
    private LocationManager locationManager;
    private LocationListener locationListener;
    public String latitudString, longitudString;
    public static final String DATO_LATITUD = "latitud";
    public static final String DATO_LONGITUD = "longitud";
    DecimalFormat decimalFormat;

    private boolean checkTimer;
    public static final String INTENT_RECEIVER = "intent_receiver";

    PendingIntent pendingIntent;
    private Intent intent;
    private int time;
    private Thread thread;



    public ServiceLocation() {
=======
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
>>>>>>> f6484fb83ed74509b62c0ba96af59b680c77dd33
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        vistaTransecto = new VistaTransecto();

<<<<<<< HEAD
        checkTimer = true;  // para solo tener un servicio activo
        intent = new Intent(INTENT_RECEIVER);
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

    private void notificacionServicio() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Foreground Notification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Intent intentResult = new Intent(this, VistaTransecto.class);
        pendingIntent = PendingIntent.getActivity(this, 0, intentResult, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("TITULO")
                .setContentText("CONTENIDO")
                .setSmallIcon(R.drawable.ic_down)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);

    }

    private void updateUI(String latitudString, String longitudString) {
        intent.putExtra(DATO_LATITUD, latitudString);
        intent.putExtra(DATO_LONGITUD,longitudString);
        sendBroadcast(intent);  // para enviar la info
    }

    private void datosUbicacion() {

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                longitudString = decimalFormat.format(location.getLongitude());
                latitudString = decimalFormat.format(location.getLatitude());


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


    @Override
    public void onDestroy() {
        super.onDestroy();
=======
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
>>>>>>> f6484fb83ed74509b62c0ba96af59b680c77dd33

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

<<<<<<< HEAD





=======
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
>>>>>>> f6484fb83ed74509b62c0ba96af59b680c77dd33
}