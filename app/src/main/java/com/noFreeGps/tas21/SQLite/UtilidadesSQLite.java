package com.noFreeGps.tas21.SQLite;
//************************************
//********************* This class has constants that define BBDD's name,
//********************* tables, table's fields. furthermore the creation
//********************* sentence for each table.
//*********************
//************************************

public class UtilidadesSQLite {

    //************************************  Nombre BBDD

    public static final String DDBB_NAME="DDBB_tas21";

    //************************************ tabla especies

    public static final String TABLA_ESPECIES = "tabla_especies";
    public static final String ID_ESPECIES ="id_especie";
    public static final String ESPECIE = "especie";
    public static final String DENSIDAD = "densidad";
    public static final String FK_ID_TRACK = "fk_IdTrack";
    public static final String FK_ID_PROYECTO_SP = "fk_IdSProyecto";

    //************************************ Constantes tabla track

    public static final String TABLA_TRACK = "tabla_track";
    public static final String ID_TRACK = "id_track";
    public static final String FECHA = "fecha";
    public static final String HORA = "hora";
    public static final String LONGITUD = "longitud";
    public static final String LATITUD = "latitud";
    public static final String ALTURA = "altura";
    public static final String FK_ID_PROYECTO_TR = "fk_IdTProyecto";

    //************************************ Constantes tabla proyecto

    public static final String TABLA_PROYECTO = "tabla_proyecto";
    public static final String NOMBRE_PROYECTO = "nombre_proyecto";


                                                 //////////////////////////////////////
    ///////////////////////////////           //     Creation tables sentences
                                                 ///////////////////////////////////////

 //************************************  Create species Table

   public static final String CREAR_TABLA_ESPECIES = "CREATE TABLE "
            +TABLA_ESPECIES+" ("
            +ID_ESPECIES+" INTEGER NOT NULL, "
            +ESPECIE+" TEXT, "
            +DENSIDAD+" INTEGER, "
            +FK_ID_TRACK+ " TEXT NOT NULL, "
            +FK_ID_PROYECTO_SP+ " TEXT NOT NULL, "


            +" FOREIGN KEY("+FK_ID_TRACK+") REFERENCES "+TABLA_TRACK+"( "+ID_TRACK+"), "
            +" FOREIGN KEY("+FK_ID_PROYECTO_SP+") REFERENCES "+TABLA_PROYECTO+"( "+NOMBRE_PROYECTO+") );";

//************************************  Create Track Table

    public static final String CREAR_TABLA_TRACK = "CREATE TABLE "
            +TABLA_TRACK+" ("
            //+ID_TRACK+" TEXT PRIMARY KEY NOT NULL, "
            +ID_TRACK+" TEXT, "
            +FECHA+" TEXT, "
            +HORA+" TEXT, "
            +LONGITUD+" TEXT, "
            +LATITUD+" TEXT, "
            +ALTURA+"  TEXT, "
            + FK_ID_PROYECTO_TR + " TEXT, "
            +" FOREIGN KEY("+ FK_ID_PROYECTO_TR +") REFERENCES "+TABLA_PROYECTO+"( "+NOMBRE_PROYECTO+") );";


    //************************************  Create project table

    public static final String CREAR_TABLA_PROYECTO = "CREATE TABLE "
            +TABLA_PROYECTO+" ("
            +NOMBRE_PROYECTO+" TEXT PRIMARY KEY NOT NULL);";


                                                //////////////////////////////////////
    ///////////////////////////////           //     Query sentences
                                                ///////////////////////////////////////

    /*String consultaCantidadTrack = "SELECT * FROM "+TABLA_TRACK+
            " JOIN "+TABLA_PROYECTO+
            " ON "+FK_ID_PROYECTO_TR+" = "+NOMBRE_PROYECTO+
            " WHERE "+NOMBRE_PROYECTO+" = '"+busquedaProyecto+"'; ";*/









}
