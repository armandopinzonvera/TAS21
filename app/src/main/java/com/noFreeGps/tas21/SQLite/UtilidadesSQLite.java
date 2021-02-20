package com.noFreeGps.tas21.SQLite;

import java.util.Date;

public class UtilidadesSQLite {

    /////////  Tabla Proyecto //////////////

    public static final String TABLA_PROYECTO = "Tabla_Proyecto";
    public static final String NOMBRE_PROYECTO = "nombre_proyecto";
    public static final String fkTRANSECTO = "fKTransecto";

    public static final String CREAR_TABLA_PROYECTO = "CREATE TABLE "
            +TABLA_PROYECTO+" ("+NOMBRE_PROYECTO+" VARCHAR(20) PRIMARY KEY, "
            +fkTRANSECTO+" VARCHAR(20) )";

    /////////  Tabla transecto //////////////

    public static final String TABLA_TRANSECTO = "Tabla_Transecto";
    public static final String ID_TRANSECTO = "id_transecto";
    public static final String fkTRACK = "fKTrack";

    public static final String CREAR_TABLA_TRANSECTO = "CREATE TABLE "
            +TABLA_TRANSECTO+" ("+ID_TRANSECTO+" VARCHAR(20) PRIMARY KEY, "
            +fkTRACK+" VARCHAR(20) )";

    /////////  Tabla track //////////////

    public static final String TABLA_TRACK = "Tabla_Track";
    public static final String ID_TRACK = "id_track";
    public static final String FECHA = "fecha";
    public static final String HORA = "hora";
    public static final String LONGITUD = "longitud";
    public static final String LATITUD = "latitud";
    public static final String ALTURA = "altura";
    public static final String ESPECIE = "especie";
    public static final String DENSIDAD = "densidad";

    public static final String CREAR_TABLA_TRACK = "CREATE TABLE "
            +TABLA_TRACK+" ("+ID_TRACK+" VARCHAR(20) PRIMARY KEY, "
            +FECHA+" DATE, "
            +HORA+" TIME, "
            +LONGITUD+" INT(11), "
            +LATITUD+" INT(11), "
            +ALTURA+" INT(11), "
            +ESPECIE+" VARCHAR(20), "
            +DENSIDAD+" INT(11) )";
}


