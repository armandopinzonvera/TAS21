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
 * > llenadoTargetas() : it receives de information from method's
 *                       as ArrayList<Entidad_Ttrack> and put it on the RecyclerView
 */
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tespecies;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Ttrack;
import com.noFreeGps.tas21.SQLite.implementaciones.Dao_Tespecies_Imp;
import com.noFreeGps.tas21.SQLite.interfaces.Dao_Tespecie;
import com.noFreeGps.tas21.config.AdaptadorRecycler;
import com.noFreeGps.tas21.R;
import com.noFreeGps.tas21.SQLite.ConexionSQLite;
import com.noFreeGps.tas21.SQLite.UtilidadesSQLite;

import java.util.ArrayList;

public class Continuar extends AppCompatActivity {

    TextView tv_c_transecto, tv_c_sumaTransecto;
    ConexionSQLite conexionSQLite;
    ArrayList<Entidad_Ttrack> listTrack;
    Dao_Tespecie daoTespecie = new Dao_Tespecies_Imp(this);
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
    ///////////////////////////////   //     Fill RecyclerView Method
                                         ///////////////////////////////////////
    private void llenadoTargetas() {

        AdaptadorRecycler adaptadorRecycler = new AdaptadorRecycler(daoTespecie.datosParaReciclerView());
        adaptadorRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage(view);
            }
        });
        recyclerView.setAdapter(adaptadorRecycler);
    }
    public void showMessage(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Continuar proyecto: "+daoTespecie.datosParaReciclerView().get(recyclerView.getChildAdapterPosition(view)).getfk_IdSProyecto());
        builder.setMessage("nombre del nuevo transecto");

        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Continuar.this, ContinuarIniciar.class);
                intent.putExtra("extra_proyecto", daoTespecie.datosParaReciclerView().get(recyclerView.getChildAdapterPosition(view)).getfk_IdSProyecto());

                startActivity(intent);
            }
        })
        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });




        builder.show();
    }





}