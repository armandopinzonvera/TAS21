package com.noFreeGps.tas21.SQLite.implementaciones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Gravity;
import android.widget.Toast;

import com.noFreeGps.tas21.SQLite.ConexionSQLite;
import com.noFreeGps.tas21.SQLite.UtilidadesSQLite;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tproyecto;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Ttrack;
import com.noFreeGps.tas21.SQLite.interfaces.Dao_Ttrack;

import java.util.ArrayList;

public class Dao_Ttrack_Imp implements Dao_Ttrack {


    Context context;

    public Dao_Ttrack_Imp(Context context) {
        this.context = context;
    }

    @Override
    public boolean addDatoTtrack(Entidad_Ttrack entidadTtrack) {
        ConexionSQLite conexionSQLite = new ConexionSQLite(context);
        SQLiteDatabase db = conexionSQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UtilidadesSQLite.ID_TRACK, entidadTtrack.getId_track());
        contentValues.put(UtilidadesSQLite.FECHA, entidadTtrack.getFecha());
        contentValues.put(UtilidadesSQLite.HORA, entidadTtrack.getHora());
        contentValues.put(UtilidadesSQLite.LONGITUD, entidadTtrack.getLongitud());
        contentValues.put(UtilidadesSQLite.LATITUD, entidadTtrack.getLatitud());
        contentValues.put(UtilidadesSQLite.ALTURA, entidadTtrack.getAltura());
        contentValues.put(UtilidadesSQLite.FK_ID_PROYECTO_TR, entidadTtrack.getFk_IdTProyecto());

        long insert = db.insert(UtilidadesSQLite.TABLA_TRACK, null, contentValues);

        if (insert == -1)
            return false;
        else
            return true;
    }

    //************************************   Verficar Proyecto no Exista en la BBDD
    ArrayList<Entidad_Ttrack> arrayIdTrack;

    public String verificarNuevoTrack(String idTrack) {

        ConexionSQLite conexion = new ConexionSQLite(context);
        SQLiteDatabase db = conexion.getReadableDatabase();
        arrayIdTrack = new ArrayList<Entidad_Ttrack>();

        Entidad_Ttrack entidadTproyecto = null;

        String queryProyecto = "SELECT "+ UtilidadesSQLite.ID_TRACK+
                " FROM "+UtilidadesSQLite.TABLA_TRACK+
                " WHERE "+UtilidadesSQLite.ID_TRACK+
                " = '"+idTrack+"';";

        Cursor cursor = db.rawQuery(queryProyecto, null);
        int columIndex = cursor.getColumnIndex(UtilidadesSQLite.ID_TRACK);

        if(cursor.moveToNext()){
            Toast toast = Toast.makeText(context, "   EXISTE   ", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0,0);
            toast.show();
            return "existe";
        }
        return "";

    }

    public void iniciarTrack(String nombreProyecto, String idTrack){
        ConexionSQLite conexionSQLite = new ConexionSQLite(context);
      //  Entidad_Tproyecto entidadTproyecto;
        Entidad_Ttrack entidadTtrack;

        try {
            entidadTtrack = new Entidad_Ttrack(idTrack,"fecha", "hora", "1.111f", "2.222f", "Altura", nombreProyecto);

        } catch (Exception e) {
            entidadTtrack = new Entidad_Ttrack("error","error", "error", "1.111f", "2.222f","altura", " error");
        }
        boolean success3 = this.addDatoTtrack(entidadTtrack);
        Toast.makeText(context, "Exito:   "+success3, Toast.LENGTH_SHORT).show();
    }
    //************************************   Llenar informacion del track en consultar.java

   ArrayList<Entidad_Ttrack> listaProjectTrack = new ArrayList<>();
    public ArrayList<Entidad_Ttrack> resultadoconsultarTrack(String nombreProyecto){
        ConexionSQLite conexionSQLite = new ConexionSQLite(context);
        SQLiteDatabase ddbb = conexionSQLite.getReadableDatabase();
        Entidad_Ttrack entidadTtrack = null;

        String sentenciaConsulta = "SELECT "+ UtilidadesSQLite.NOMBRE_PROYECTO+
                ", COUNT("+UtilidadesSQLite.ID_TRACK+
                "), MIN("+UtilidadesSQLite.FECHA+
                "), MAX("+UtilidadesSQLite.FECHA+
                "), MIN("+UtilidadesSQLite.ALTURA+
                "), MAX("+UtilidadesSQLite.ALTURA+
                ") FROM "+ UtilidadesSQLite.TABLA_TRACK+
                " JOIN "+UtilidadesSQLite.TABLA_PROYECTO+" ON "+UtilidadesSQLite.NOMBRE_PROYECTO+" = "+UtilidadesSQLite.FK_ID_PROYECTO_TR+
                " WHERE "+UtilidadesSQLite.NOMBRE_PROYECTO+" = '"+nombreProyecto+"';";

        Cursor cursor = ddbb.rawQuery(sentenciaConsulta, null);
        while(cursor.moveToNext()){
            entidadTtrack = new Entidad_Ttrack();
            entidadTtrack.setId_track(cursor.getString(0));
            entidadTtrack.setFecha(cursor.getString(1));
            entidadTtrack.setFecha(cursor.getString(2));
            entidadTtrack.setAltura(cursor.getString(3));
            entidadTtrack.setAltura(cursor.getString(4));

        }
        listaProjectTrack.add(entidadTtrack);

        for(int i = 0 ; i < listaProjectTrack.size(); i++){
            System.out.println("                xxxxxxxxxxxxxxxxxx                  ");
            System.out.println("                       --                           ");
            System.out.println(listaProjectTrack.get(i).getId_track());
            System.out.println(listaProjectTrack.get(i).getFecha());
            System.out.println(listaProjectTrack.get(i).getAltura());
            System.out.println("                       --                           ");
            System.out.println("                xxxxxxxxxxxxxxxxxx                  ");
        }


        cursor.close();
        ddbb.close();
        return listaProjectTrack;
    }



}
