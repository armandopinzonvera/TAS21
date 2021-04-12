package com.noFreeGps.tas21.config;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
import androidx.core.app.NotificationCompat;

import com.noFreeGps.tas21.ui.VistaTransecto;

import java.util.concurrent.TimeUnit;

public class ServiceLocation extends Service implements LocationListener {

    private LocationManager locationManager;

    public static final String START_LOCATION_SERVICE = "Iniciar localizacion";
    public static final String FINAL_LOCATION_SERVICE = "Finalizar localizacion";

    private boolean checkTimer;
    public static final String INTENT_RECEIVER = "intent_receiver";
    public static final String DATO_TIEMPO = "tiempo";
    private final int COUNT_NOTIFICATION_ID = 111;
    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;
    private Intent intent;
    private  int time;
    private Thread thread;

    public ServiceLocation() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        checkTimer = true;  // para solo tener un servicio activo
        intent = new Intent(INTENT_RECEIVER);
        time = 20; // tiempo al que inicia




    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // lo que se coloca adentro es lo que se ejecuta en el hilo secundario
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                while(time > 0){
                    if(checkTimer == true){

                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } time = time - 1;
                            updateUI(time);
                    }else{
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
        private void  updateUI(int time){
            intent.putExtra(DATO_TIEMPO, String.valueOf(time));
            sendBroadcast(intent);  // para enviar la info
        }

        private void showNotification(){

          /*  Intent notificationIntent = new Intent(this, VistaTransecto.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            notificationBuilder = new NotificationCompat.Builder(ServiceLocation.this)

            .setContentText("x x x x x ").setContentTitle("AJA").setContentIntent(pendingIntent);
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(
                    COUNT_NOTIFICATION_ID, notificationBuilder.build()
            );*/

            Toast toast = Toast.makeText(getApplicationContext(), "SERVICE LOCATION", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();


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
                                            //////////////////////////////////////
    ///////////////////////////////     //     Location Listener
                                            ////////////////////////////////////////

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