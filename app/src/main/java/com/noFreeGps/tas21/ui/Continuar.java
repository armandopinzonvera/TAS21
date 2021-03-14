package com.noFreeGps.tas21.ui;
/***************************
* En este activity se presentan targetas (recyclerView)
 * con informacion general de cada proyecto: nombre, cantidad de
 * transectos, riqueza acumulada y densidad acumulada.
 * con informacion tomada de la BBDD
 * y contienen un listener que permiter continuar un proyecto
 * o descargar la informacion
*
****************************/

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.noFreeGps.tas21.SQLite.entidades.Entidad_Ttrack;
import com.noFreeGps.tas21.config.AdaptadorRecycler;
import com.noFreeGps.tas21.R;
import com.noFreeGps.tas21.SQLite.ConexionSQLite;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tproyecto;
import com.noFreeGps.tas21.SQLite.UtilidadesSQLite;

import java.util.ArrayList;

public class Continuar extends AppCompatActivity {


    TextView tv_c_transecto, tv_c_sumaTransecto;
    ConexionSQLite conexionSQLite;
    //************************************  RecyclerView

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuar);

        conexionSQLite = new ConexionSQLite(getApplicationContext() );


        tv_c_transecto = findViewById(R.id.tv_c_transectos);
       //************************************  RecyclerView
        listTrack = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        llenadoTargetas();
    }
                                               //////////////////////////////////////
    ///////////////////////////////           //     Fill RecyclerView Method
                                              ///////////////////////////////////////

    /**** OJOO :   LA INFORMACION ES MONTADA EN EL ARRAY, EMPERO ESE ARRAY
     * RETORNA EL NOMBRE DEL PROYECTO Y EL ID DEL TRACK, QUEDA PENDIENTE
     * CONVERTIR EL VALOR DEL TRACK A LA CANTIDAD
     * ****/


    ArrayList<Entidad_Ttrack> listTrack;

    private void llenadoTargetas() {
        SQLiteDatabase ddbb = conexionSQLite.getReadableDatabase();

        Entidad_Ttrack entidadTtrack =null;

        Cursor cursor2 = ddbb.rawQuery("SELECT * FROM "+UtilidadesSQLite.TABLA_TRACK+
                " JOIN "+UtilidadesSQLite.TABLA_PROYECTO+
                " ON "+UtilidadesSQLite.FK_ID_PROYECTO_TR+" = "+UtilidadesSQLite.NOMBRE_PROYECTO, null);

   /*     Cursor cursor2 = ddbb.rawQuery("SELECT * FROM "+UtilidadesSQLite.TABLA_TRACK+
                " JOIN "+UtilidadesSQLite.TABLA_PROYECTO+
                " ON "+UtilidadesSQLite.FK_ID_PROYECTO_TR+" = "+UtilidadesSQLite.NOMBRE_PROYECTO+
                " WHERE "+UtilidadesSQLite.NOMBRE_PROYECTO+" = '"+UtilidadesSQLite.FK_ID_PROYECTO_TR+"'", null);*/

        int indexColumnTrack = cursor2.getColumnIndex(UtilidadesSQLite.ID_TRACK);
        int indexColumnProject = cursor2.getColumnIndex(UtilidadesSQLite.FK_ID_PROYECTO_TR);

        /******/

        while(cursor2.moveToNext()){
            entidadTtrack = new Entidad_Ttrack();

            entidadTtrack.setFk_IdTProyecto (cursor2.getString(indexColumnProject));
            entidadTtrack.setId_track(cursor2.getString(indexColumnTrack));


            listTrack.add(entidadTtrack);

        }
        AdaptadorRecycler adaptadorRecycler = new AdaptadorRecycler(listTrack);
        recyclerView.setAdapter(adaptadorRecycler);






    }



}