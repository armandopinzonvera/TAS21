package com.noFreeGps.tas21.SQLite;

public class UtilidadesSQLite  {


    public static final String DDBB_NAME="DDBB_tas";
    //////////  tabla proyecto  /////////////

    public static final String TABLA_PROYECTO = "tabla_proyecto";
    public static final String NOMBRE_PROYECTO = "nombre_proyecto";
    public static final String FK_TRANSECTO = "fk_transecto";

    public static final String CREAR_TABLA_PROYECTO = "CREATE TABLE "
            +TABLA_PROYECTO+" ("
            +NOMBRE_PROYECTO+" TEXT, "
            +FK_TRANSECTO+" TEXT )";

    //////////  tabla transecto  /////////////

    public static final String TABLA_TRANSECTO = "tabla_transecto";
    public static final String ID_TRANSECTO = "id_transecto";
    public static final String FK_TRACK = "fk_track";

    public static final String CREAR_TABLA_TRANSECTO = "CREATE TABLE "
            +TABLA_TRANSECTO+" ("
            +ID_TRANSECTO+" TEXT PRIMARY KEY, "
            +FK_TRACK+" TEXT )";

    //////////  tabla track  /////////////

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
            +ID_TRACK+" TEXT PRIMARY KEY, "
            +FECHA+" DATE, "
            +HORA+" TIME, "
            +LONGITUD+" INTEGER, "
            +LATITUD+" INTEGER, "
            +ALTURA+"  INTEGER, "
            +ESPECIE+" TEXT, "
            +DENSIDAD+" INT )";

}
