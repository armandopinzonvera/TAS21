package com.noFreeGps.tas21.config;

/**
 * Esta clase crea una ventana donde se notifica el inicio del servicio
 * contiene un unico metodo >> Notificar servicio, el cual retorna un objeto
 * de la clase Notification.
 * debido a que para implementarlo se utiliza el metodo "startForeground"
 * y este metodo solo se puede aplicar en clases >Service<
 * entonces para usarlo se debe hacer como el sgundo parametro de este metodo,
 *
 * Se pueden cambiar las variables String: titulo y texto, para personalizar el mensaje
 * estas vienen de: context.getString(R.string.notificacion_servicio_titulo)
 *
 * en una clase que extienda service, Ejemp:
 *   * * ** * * * **
 *          NotificacionServicio notificacionServicio = new NotificacionServicio(this);
 *          startForeground(1, notificacionServicio.notificarServicio());
 *
 * * * * * * * * * * * * * * *
 *
 */




import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import com.noFreeGps.tas21.R;
import com.noFreeGps.tas21.ui.VistaTransecto;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import static android.app.Service.*;

class NotificacionServicio  {

    private static final String CHANNEL_ID = "Location channel notification";
    String titulo = "";
    String texto = "";

    Context context;

    public NotificacionServicio(Context context) {
        this.context = context;
    }

    public Notification notificarServicio(){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Location Service", NotificationManager.IMPORTANCE_HIGH);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);

    }
    Intent resultIntent = new Intent(context, VistaTransecto.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(context.getString(R.string.notificacion_servicio_titulo))
            .setContentText(context.getString(R.string.notificacion_servicio_contenido))
            .setSmallIcon(R.drawable.ic_gps_fixed_black_24dp)
            .setContentIntent(pendingIntent)
            .build();


    return notification;
    }

}
