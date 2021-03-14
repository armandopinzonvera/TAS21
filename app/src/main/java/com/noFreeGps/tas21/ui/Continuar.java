package com.noFreeGps.tas21.ui;

/** This activity takes the list of creating projects names.
 * from the DDBB (tabla_projecto), make a summary card (RecyclerView) for each one,
 * including amount of tracks, Accumulated richness and density;
 * Showing this information on TextViews.
 * In addition if a Project-card is selected by clicking, It's
 * Open a question window asking if the user wants to follow.
 * That project with a new track so ask the Id for this one,
 * enter the information to the DDBB and open VistaTransecto's
 * activity for this new register:
 * So here we can find the following methods:
 * > @Override - onCreate()
 *
 */


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

    ArrayList<Entidad_Ttrack> listTrack;

    private void llenadoTargetas() {

        AdaptadorRecycler adaptadorRecycler = new AdaptadorRecycler(dataNombreProyecto());
        recyclerView.setAdapter(adaptadorRecycler);
    }

    private ArrayList<Entidad_Ttrack> dataNombreProyecto(){

        SQLiteDatabase ddbb = conexionSQLite.getReadableDatabase();
        Entidad_Ttrack entidadTtrack =null;

        Cursor cursor2 = ddbb.rawQuery("SELECT * FROM "+UtilidadesSQLite.TABLA_TRACK+
                " JOIN "+UtilidadesSQLite.TABLA_PROYECTO+
                " ON "+UtilidadesSQLite.FK_ID_PROYECTO_TR+" = "+UtilidadesSQLite.NOMBRE_PROYECTO, null);

        int indexColumnTrack = cursor2.getColumnIndex(UtilidadesSQLite.ID_TRACK);
        int indexColumnProject = cursor2.getColumnIndex(UtilidadesSQLite.FK_ID_PROYECTO_TR);

        while(cursor2.moveToNext()){
            entidadTtrack = new Entidad_Ttrack();

            entidadTtrack.setFk_IdTProyecto (cursor2.getString(indexColumnProject));
            entidadTtrack.setId_track(cursor2.getString(indexColumnTrack));
            listTrack.add(entidadTtrack);
        }
        return listTrack;
    }




}