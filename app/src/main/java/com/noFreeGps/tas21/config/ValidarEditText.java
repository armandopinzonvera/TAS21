package com.noFreeGps.tas21.config;

import android.content.Context;
import android.widget.Toast;

public class ValidarEditText {

    private final Context context;
    public ValidarEditText(Context context) {
        this.context = context;
    }

    public boolean compararEditText(String nombreProyecto, String idTrack) {


        if (nombreProyecto.isEmpty() || idTrack.isEmpty()) {
            Toast.makeText(context, "No pueden estar vacios", Toast.LENGTH_LONG).show();
            return false;

        } else if (nombreProyecto.equals(idTrack)) {
            Toast.makeText(context, "No pueden ser iguales", Toast.LENGTH_LONG).show();
            return false;

        } else if (nombreProyecto.length() > 20 || idTrack.length() > 20) {
            Toast.makeText(context, "no pueden ser tan grandes", Toast.LENGTH_LONG).show();
            return false;

        }
        return true;
    }




}





