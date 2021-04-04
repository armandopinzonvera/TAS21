package com.noFreeGps.tas21.config;

import android.content.Context;
import android.widget.Toast;

public class ValidarEditText {

    private final Context context;
    public ValidarEditText(Context context) {
        this.context = context;
    }

    public boolean compararEditText(String campo1validar, String campo2validar) {


        if (campo1validar.isEmpty() || campo2validar.isEmpty()) {
            Toast.makeText(context, "No pueden estar vacios", Toast.LENGTH_LONG).show();
            return false;

        } else if (campo1validar.equals(campo2validar)) {
            Toast.makeText(context, "No pueden ser iguales", Toast.LENGTH_LONG).show();
            return false;

        } else if (campo1validar.length() > 20 || campo2validar.length() > 20) {
            Toast.makeText(context, "no pueden ser tan grandes", Toast.LENGTH_LONG).show();
            return false;

        }
        return true;
    }






}





