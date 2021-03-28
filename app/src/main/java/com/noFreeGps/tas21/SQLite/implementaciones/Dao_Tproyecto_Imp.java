package com.noFreeGps.tas21.SQLite.implementaciones;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Gravity;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.noFreeGps.tas21.SQLite.ConexionSQLite;
import com.noFreeGps.tas21.SQLite.UtilidadesSQLite;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tproyecto;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Ttrack;
import com.noFreeGps.tas21.SQLite.interfaces.Dao_Tproyecto;
import com.noFreeGps.tas21.ui.Iniciar;

import java.util.ArrayList;

public class Dao_Tproyecto_Imp implements Dao_Tproyecto {


    ArrayList<Entidad_Tproyecto> arrayNombreProyecto;

    private final Context context;
    public Dao_Tproyecto_Imp(Context context) {
        this.context = context;
    }

 //************************************   Verficar Proyecto no Exista en la BBDD
    @Override
    public String verificarExiteProyecto( String nombreProyecto) {
        ConexionSQLite conexion = new ConexionSQLite(context);
        SQLiteDatabase db = conexion.getReadableDatabase();
        arrayNombreProyecto = new ArrayList<Entidad_Tproyecto>();
        Entidad_Tproyecto entidadTproyecto = null;

        String queryProyecto = "SELECT "+ UtilidadesSQLite.NOMBRE_PROYECTO+
                " FROM "+UtilidadesSQLite.TABLA_PROYECTO+
                " WHERE "+UtilidadesSQLite.NOMBRE_PROYECTO+
                " = '"+nombreProyecto+"';";

        Cursor cursor = db.rawQuery(queryProyecto, null);
        int columIndex = cursor.getColumnIndex(UtilidadesSQLite.NOMBRE_PROYECTO);

        if(cursor.moveToNext()){
            Toast toast = Toast.makeText(context, "   EXISTE   ", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0,0);
            toast.show();
            return "existe";
        }
        return "";
    }
    //************************************   Crear Proyecto con Nombre y track

    public void iniciarProyecto( String nombreProyecto, String idTrack){

        Entidad_Tproyecto entidadTproyecto;
        Entidad_Ttrack entidadTtrack;

        try {
            entidadTtrack = new Entidad_Ttrack(idTrack,"fecha", "hora", 1.111f, 2.222f,2222, nombreProyecto);
            entidadTproyecto = new Entidad_Tproyecto(nombreProyecto);

        } catch (Exception e) {
            entidadTproyecto = new Entidad_Tproyecto("error");
            entidadTtrack = new Entidad_Ttrack("error","error", "error", 1.111f, 2.222f,1, " error");
        }
        ConexionSQLite conexionSQLite = new ConexionSQLite(context);

        boolean success1 = conexionSQLite.addDatoTproyecto(entidadTproyecto);
        boolean success3 = conexionSQLite.addDatoTtrack(entidadTtrack);

        Toast.makeText(context, "Exito: "+success1+ ", "+success3, Toast.LENGTH_SHORT).show();

    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    }

