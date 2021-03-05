package com.noFreeGps.tas21.config;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServiceLocation extends Service {
    public ServiceLocation() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}