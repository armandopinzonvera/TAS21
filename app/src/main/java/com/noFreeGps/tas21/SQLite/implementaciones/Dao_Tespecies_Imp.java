package com.noFreeGps.tas21.SQLite.implementaciones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.noFreeGps.tas21.SQLite.ConexionSQLite;
import com.noFreeGps.tas21.SQLite.UtilidadesSQLite;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tespecies;
import com.noFreeGps.tas21.SQLite.interfaces.Dao_Tespecie;

import java.util.ArrayList;

public class Dao_Tespecies_Imp implements Dao_Tespecie {


    private final Context context;

    public Dao_Tespecies_Imp(Context context) {

        this.context = context;
    }

    public boolean addDatoEspecie(Entidad_Tespecies entidadTespecie){

        ConexionSQLite conexion = new ConexionSQLite(context);
        SQLiteDatabase ddbb = conexion.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(UtilidadesSQLite.ESPECIE, entidadTespecie.getEspecie());
        contentValues.put(UtilidadesSQLite.DENSIDAD, entidadTespecie.getDensidad());
        contentValues.put(UtilidadesSQLite.FK_ID_TRACK, entidadTespecie.getFk_idTrack());
        contentValues.put(UtilidadesSQLite.FK_ID_PROYECTO_SP, entidadTespecie.getfk_IdSProyecto());

        long insert = ddbb.insert(UtilidadesSQLite.TABLA_ESPECIES, null, contentValues);

        if(insert == -1) return false;
        else

        return true;
    }

    ArrayList<Entidad_Tespecies> listDataProjectEspecies = new ArrayList<Entidad_Tespecies>();

    public ArrayList<Entidad_Tespecies> resultadoConsultar(String nombreProyecto){

        ConexionSQLite conexionSQLite = new ConexionSQLite(context);
        SQLiteDatabase ddbb = conexionSQLite.getReadableDatabase();
        Entidad_Tespecies entidadTespecies = null;

        String sentenciaConsulta = "SELECT "+ UtilidadesSQLite.NOMBRE_PROYECTO+
                ", COUNT("+UtilidadesSQLite.ESPECIE+
                "), SUM ("+UtilidadesSQLite.DENSIDAD+
                ") FROM "+ UtilidadesSQLite.TABLA_ESPECIES+
                " JOIN "+UtilidadesSQLite.TABLA_PROYECTO+" ON "+UtilidadesSQLite.NOMBRE_PROYECTO+" = "+UtilidadesSQLite.FK_ID_PROYECTO_SP+
                " WHERE "+UtilidadesSQLite.NOMBRE_PROYECTO+" = '"+nombreProyecto+"';";

        Cursor cursor = ddbb.rawQuery(sentenciaConsulta, null);
        while(cursor.moveToNext()){
            entidadTespecies = new Entidad_Tespecies();
            entidadTespecies.setEspecie(cursor.getString(1));
            entidadTespecies.setDensidad(cursor.getInt(2));
        }
        listDataProjectEspecies.add(entidadTespecies);
        cursor.close();
        ddbb.close();
        return listDataProjectEspecies;
    }





}
