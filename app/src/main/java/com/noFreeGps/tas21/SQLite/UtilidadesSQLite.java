package com.noFreeGps.tas21.SQLite;

import android.widget.EditText;

public class UtilidadesSQLite {
    public static final String DDBB_NAME="DDBB_tas";


    /*********  tabla transecto  ************/

    public static final String TABLA_TRANSECTO = "tabla_transecto";
    public static final String ID_TRANSECTO = "id_transecto";
    public static final String FK_TRACK = "fk_track";

    public static final String CREAR_TABLA_TRANSECTO = "CREATE TABLE "
            +TABLA_TRANSECTO+" ("
            +ID_TRANSECTO+" TEXT PRIMARY KEY, "
            +FK_TRACK+" INTEGER )";


    /***********  tabla proyecto  ************/

    public static final String TABLA_PROYECTO = "tabla_proyecto";
    public static final String NOMBRE_PROYECTO = "nombre_proyecto";
    public static final String FK_TRANSECTO = "fk_transecto";


    public static final String CREAR_TABLA_PROYECTO = "CREATE TABLE "
            +TABLA_PROYECTO+" ("
            +NOMBRE_PROYECTO+" TEXT, "
            +FK_TRANSECTO+" TEXT, FOREIGN KEY("+FK_TRANSECTO+") REFERENCES "+TABLA_TRANSECTO+"( "+ID_TRANSECTO+") )";


    /*********  tabla track  ************/

    public static final String TABLA_TRACK = "tabla_track";
    public static final String ID_TRACK = "id_track";
    public static final String FECHA = "fecha";
    public static final String HORA = "hora";
    public static final String LONGITUD = "longitud";
    public static final String LATITUD = "latitud";
    public static final String ALTURA = "altura";
    public static final String ESPECIE = "especie";
    public static final String DENSIDAD = "densidad";

    public static final String CREAR_TABLA_TRACK = "CREATE TABLE "
            +TABLA_TRACK+" ("
            +ID_TRACK+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +FECHA+" DATE, "
            +HORA+" TIME, "
            +LONGITUD+" INTEGER, "
            +LATITUD+" INTEGER, "
            +ALTURA+"  INTEGER, "
            +ESPECIE+" TEXT, "
            +DENSIDAD+" INTEGER )";

    /**********  Activity Iniciar  ************/

    public static String crearProyecto(EditText nombreProyecto, EditText idTransecto){
        String nombreProyecto1 = nombreProyecto.getText().toString().trim();
        String idTransecto1 = idTransecto.getText().toString().trim();

        return "INSERT INTO "+TABLA_PROYECTO +" ( " +NOMBRE_PROYECTO+", "+FK_TRANSECTO+") "+" VALUES ('"+nombreProyecto1+"', '"+idTransecto1+"')";
    }


    public static String ingresarTransecto(EditText etIdTransecto){
        String idTransecto1 = etIdTransecto.getText().toString().trim();

        return "INSERT INTO " +TABLA_TRANSECTO+ " ( " + ID_TRANSECTO + ", " + FK_TRACK + ") "+" VALUES ('" + idTransecto1 + "', '" + 0 + "')";

    }

}
