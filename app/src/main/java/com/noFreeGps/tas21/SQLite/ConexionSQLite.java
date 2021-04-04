package com.noFreeGps.tas21.SQLite;
/**
 *
 */

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



    ArrayList<Entidad_Tespecies> listTrackx = new ArrayList<Entidad_Tespecies>();
    public ArrayList<Entidad_Tespecies> dataNombreProyecto(){

        SQLiteDatabase ddbb = this.getReadableDatabase();
        Entidad_Tespecies entidadTespecies =null;

        String queryEspecies = "SELECT "+UtilidadesSQLite.NOMBRE_PROYECTO+
                                ", COUNT("+UtilidadesSQLite.ID_TRACK+
                                "), COUNT("+UtilidadesSQLite.ESPECIE+
                                "), SUM ("+UtilidadesSQLite.DENSIDAD+
                                ") FROM "+ UtilidadesSQLite.TABLA_ESPECIES+
                                " JOIN "+UtilidadesSQLite.TABLA_TRACK+" ON "+UtilidadesSQLite.ID_TRACK+" = "+UtilidadesSQLite.FK_ID_TRACK+
                                " JOIN "+UtilidadesSQLite.TABLA_PROYECTO+" ON "+UtilidadesSQLite.NOMBRE_PROYECTO+" = "+UtilidadesSQLite.FK_ID_PROYECTO_SP+
                                " GROUP BY "+UtilidadesSQLite.NOMBRE_PROYECTO;


        Cursor cursor = ddbb.rawQuery(queryEspecies, null);

       /* int indexColumnProject = cursor.getColumnIndex(UtilidadesSQLite.FK_ID_PROYECTO_TR);
      */

        while(cursor.moveToNext()){

            entidadTespecies = new Entidad_Tespecies();
            entidadTespecies.setfk_IdSProyecto (cursor.getString(0));
            entidadTespecies.setFk_idTrack(cursor.getString(1));
            entidadTespecies.setEspecie(cursor.getString(2));
            entidadTespecies.setDensidad(cursor.getInt(3));

            listTrackx.add(entidadTespecies);
        }
        cursor.close();
        ddbb.close();
        return listTrackx;

    }




}
