package com.noFreeGps.tas21.SQLite;

public class UtilidadesSQLite  {

    //////////  tabla proyecto  /////////////

    public static final String TABLA_PROYECTO = "tabla_proyecto";
    public static final String NOMBRE_PROYECTO = "nombre_proyecto";
    public static final String FK_TRANSECTO = "fk_transecto";

    public static final String CREAR_TABLA_PROYECTO = "CREATE TABLE "
            +TABLA_PROYECTO+" ("
            +NOMBRE_PROYECTO+" VARCHAR(20) PRIMARY KEY, "
            +FK_TRANSECTO+" VARCHAR(20) )";

    //////////  tabla transecto  /////////////

    public static final String TABLA_TRANSECTO = "tabla_proyecto";
    public static final String ID_TRANSECTO = "id_transecto";
    public static final String FK_TRACK = "fk_track";

    public static final String CREAR_TABLA_TRANSECTO = "CREATE TABLE "
            +TABLA_TRANSECTO+" ("
            +ID_TRANSECTO+" VARCHAR(20) PRIMARY KEY, "
            +FK_TRACK+" VARCHAR(20) )";

    //////////  tabla track  /////////////

    public static final String TABLA_TRACK = "tabla_proyecto";
    public static final String ID_TRACK = "id_transecto";
    public static final String FECHA = "fecha";
    public static final String HORA = "hora";
    public static final String LONGITUD = "longitud";
    public static final String LATITUD = "latitud";
    public static final String ALTURA = "altura";
    public static final String ESPECIE = "especie";
    public static final String DENSIDAD = "densidad";

    public static final String CREAR_TABLA_TRACK = "CREATE TABLE "
            +TABLA_TRACK+" ("
            +ID_TRACK+" VARCHAR(20) PRIMARY KEY, "
            +FECHA+" DATE, "
            +HORA+" TIME, "
            +LONGITUD+" INT, "
            +LATITUD+" INT, "
            +ALTURA+" INT, "
            +ESPECIE+" VARCHAR(20), "
            +DENSIDAD+" INT )";

}
