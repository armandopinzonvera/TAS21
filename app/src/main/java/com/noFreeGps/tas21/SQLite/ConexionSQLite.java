package com.noFreeGps.tas21.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tespecies;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tproyecto;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Ttrack;

import java.util.ArrayList;
import java.util.List;

public class ConexionSQLite extends SQLiteOpenHelper {

    public ConexionSQLite(@Nullable Context context) {
        super(context, UtilidadesSQLite.DDBB_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(UtilidadesSQLite.CREAR_TABLA_TRACK);
        db.execSQL(UtilidadesSQLite.CREAR_TABLA_PROYECTO);
        db.execSQL(UtilidadesSQLite.CREAR_TABLA_ESPECIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+UtilidadesSQLite.TABLA_TRACK);
        db.execSQL("DROP TABLE IF EXISTS "+UtilidadesSQLite.TABLA_PROYECTO);
        db.execSQL("DROP TABLE IF EXISTS "+UtilidadesSQLite.TABLA_ESPECIES);
        onCreate(db);
    }

                                                //////////////////////////////////////
      ///////////////////////////////           //     Add Data Methods
                                                ///////////////////////////////////////

    public boolean addDatoTproyecto(Entidad_Tproyecto entidadTproyecto){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UtilidadesSQLite.NOMBRE_PROYECTO, entidadTproyecto.getNombre_proyecto());

        long insert = db.insert(UtilidadesSQLite.TABLA_PROYECTO, null, contentValues);

        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean addDatoTtrack(Entidad_Ttrack entidadTtrack){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UtilidadesSQLite.ID_TRACK, entidadTtrack.getId_track());
        contentValues.put(UtilidadesSQLite.FECHA, entidadTtrack.getFecha());
        contentValues.put(UtilidadesSQLite.HORA, entidadTtrack.getHora());
        contentValues.put(UtilidadesSQLite.LONGITUD, entidadTtrack.getLongitud());
        contentValues.put(UtilidadesSQLite.LATITUD, entidadTtrack.getLatitud());
        contentValues.put(UtilidadesSQLite.ALTURA, entidadTtrack.getAltura());
        contentValues.put(UtilidadesSQLite.FK_ID_PROYECTO_TR, entidadTtrack.getFk_idTransecto());

        long insert = db.insert(UtilidadesSQLite.TABLA_TRACK, null, contentValues);

        if (insert == -1)
            return false;
        else
            return true;
    }

    public boolean addDatoTespecies(Entidad_Tespecies entidadTespecies){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UtilidadesSQLite.ID_ESPECIES, entidadTespecies.getEspecie());
        contentValues.put(UtilidadesSQLite.ESPECIE, entidadTespecies.getEspecie());
        contentValues.put(UtilidadesSQLite.DENSIDAD, entidadTespecies.getDensidad());
        contentValues.put(UtilidadesSQLite.FK_ID_TRACK, entidadTespecies.getFk_idTrack());
        contentValues.put(UtilidadesSQLite.FK_ID_PROYECTO_SP, entidadTespecies.getfk_IdSProyecto());

        long insert = db.insert(UtilidadesSQLite.TABLA_ESPECIES, null, contentValues);

        if(insert == -1)
            return false;
        else
            return true;
    }
                                                 //////////////////////////////////////
    ///////////////////////////////           //     Fill ListView with Species Method
                                                 ///////////////////////////////////////


       public List<Entidad_Tespecies> getEveryoneEspecie(){
        List<Entidad_Tespecies> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM "+UtilidadesSQLite.TABLA_ESPECIES;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String especie = cursor.getString(1);
                int densidad = cursor.getInt(2);
                String fk_IdTrack= cursor.getString(3);
                String fk_IdSProyecto= cursor.getString(4);

                Entidad_Tespecies entidadTespecies = new Entidad_Tespecies(id, especie, densidad, fk_IdTrack, fk_IdSProyecto);

                entidadTespecies.getEspecie();
                returnList.add(entidadTespecies);


            }while(cursor.moveToNext());
        }else{

        }
        cursor.close();
        sqLiteDatabase.close();

        return returnList;
    }

                                                 //////////////////////////////////////
    ///////////////////////////////           //     Amount of tracks per project Method
                                                ///////////////////////////////////////
    ArrayList<Entidad_Ttrack> entidadTtrackArrayList;

    public int cantidadtransectos(String busquedaProyecto){

        SQLiteDatabase ddbb = this.getReadableDatabase();
        Entidad_Ttrack entidadTtrack = null;
        entidadTtrackArrayList = new ArrayList<Entidad_Ttrack>();

        String consultaCantidadTrack = "SELECT * FROM "+UtilidadesSQLite.TABLA_TRACK+
                " JOIN "+UtilidadesSQLite.TABLA_PROYECTO+
                " ON "+UtilidadesSQLite.FK_ID_PROYECTO_TR+" = "+UtilidadesSQLite.NOMBRE_PROYECTO+
                " WHERE "+UtilidadesSQLite.NOMBRE_PROYECTO+" = '"+busquedaProyecto+"'; ";

        Cursor cursor = ddbb.rawQuery(consultaCantidadTrack, null);

        int indexColumna = cursor.getColumnIndex(UtilidadesSQLite.ID_TRACK);
        int largoArray = 0;
        while(cursor.moveToNext()){
            entidadTtrack = new Entidad_Ttrack();
            entidadTtrack.setId_track(cursor.getString(indexColumna));
            entidadTtrackArrayList.add(entidadTtrack);
            largoArray = entidadTtrackArrayList.size();
        }
        return largoArray;
    }




}
