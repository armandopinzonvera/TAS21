package com.noFreeGps.tas21.SQLite;

import android.widget.EditText;

public class UtilidadesSQLite {

    /*********** Nombre BBDD  ************/

    public static final String DDBB_NAME="DDBB_tas";

    /*********** Constantes tabla proyecto  ************/

    public static final String TABLA_PROYECTO = "tabla_proyecto";
    public static final String NOMBRE_PROYECTO = "nombre_proyecto";
    public static final String FK_TRANSECTO = "fk_transecto";

    /********* Constantes tabla transecto  ************/

    public static final String TABLA_TRANSECTO = "tabla_transecto";
    public static final String ID_TRANSECTO = "id_transecto";
    public static final String FK_TRACK = "fk_track";

    /********* Constantes tabla track  ************/

    public static final String TABLA_TRACK = "tabla_track";
    public static final String ID_TRACK = "id_track";
    public static final String FECHA = "fecha";
    public static final String HORA = "hora";
    public static final String LONGITUD = "longitud";
    public static final String LATITUD = "latitud";
    public static final String ALTURA = "altura";
    public static final String ESPECIE = "especie";
    public static final String DENSIDAD = "densidad";

    /************************************************************************************************************/
    /*********  crear tabla track ************/

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

    /*********  crear tabla transecto  ************/

    public static final String CREAR_TABLA_TRANSECTO = "CREATE TABLE "
            +TABLA_TRANSECTO+" ("
            +ID_TRANSECTO+" TEXT PRIMARY KEY, "
            +FK_TRACK+" INTEGER, FOREING KEY("+FK_TRACK+") REFERENCES "+TABLA_TRACK+"( "+ID_TRACK+") )";

    /*********  crear tabla proyecto  ************/

    public static final String CREAR_TABLA_PROYECTO = "CREATE TABLE "
            +TABLA_PROYECTO+" ("
            +NOMBRE_PROYECTO+" TEXT, "
            +FK_TRANSECTO+" TEXT, FOREIGN KEY("+FK_TRANSECTO+") REFERENCES "+TABLA_TRANSECTO+"( "+ID_TRANSECTO+") )";


    /************************************************************************************************************/
    /**********  Activity Iniciar  ************/

    public static String insertarProyecto(EditText nombreProyecto, EditText idTransecto){
        String nombreProyecto1 = nombreProyecto.getText().toString().trim();
        String idTransecto1 = idTransecto.getText().toString().trim();

        return "INSERT INTO "+TABLA_PROYECTO +" ( " +NOMBRE_PROYECTO+", "+FK_TRANSECTO+") "+" VALUES ('"+nombreProyecto1+"', '"+idTransecto1+"')";
    }

    public static String insertarTransecto(EditText etIdTransecto){
        String idTransecto1 = etIdTransecto.getText().toString().trim();

        return "INSERT INTO " +TABLA_TRANSECTO+ " ( " + ID_TRANSECTO + ", " + FK_TRACK + ") "+" VALUES ('" + idTransecto1 + "', '" + 0 + "')";
    }

    /**********  Activity VistaTransecto  ************/

    public static String insertarEspecieyDensidad(EditText etespecie, EditText etcantidad) {
        String etespecie1 = etespecie.getText().toString().trim();
        String etcantidad1 = etcantidad.getText().toString().trim();

        return "INSERT INTO "+TABLA_TRACK+" ( " +ESPECIE+", "+DENSIDAD+") "+" VALUES ('"+etespecie1+"', '"+etcantidad1+"')";
    }

}
