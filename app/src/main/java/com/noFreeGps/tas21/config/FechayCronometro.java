package com.noFreeGps.tas21.config;

import android.os.SystemClock;
import android.widget.Chronometer;

import java.text.SimpleDateFormat;

public class FechayCronometro {

    private long fecha, detener;
    private String fechaString;
    private boolean tiempoCorre;
    Chronometer chronometer;

    public FechayCronometro() {

    }

    public String getFecha(){

        fecha = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
        fechaString = simpleDateFormat.format(fecha);

        return fechaString;
    }

    public Chronometer iniciarCronometro(Chronometer chronometer){
        this.chronometer = chronometer;

        if(!tiempoCorre){
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
           tiempoCorre = true;
        }
        return chronometer;
    }
    public void detenerCronometro(){

        if(tiempoCorre){
            chronometer.stop();
            detener = SystemClock.elapsedRealtime() - chronometer.getBase();
            tiempoCorre = false;
        }
    }

    public void resetCronometro(){
        chronometer.setBase(SystemClock.elapsedRealtime());
        detener= 0;
    }
}
