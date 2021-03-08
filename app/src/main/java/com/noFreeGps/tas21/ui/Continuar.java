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
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.noFreeGps.tas21.config.AdaptadorRecycler;
import com.noFreeGps.tas21.R;
import com.noFreeGps.tas21.SQLite.ConexionSQLite;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tproyecto;
import com.noFreeGps.tas21.SQLite.UtilidadesSQLite;

import java.util.ArrayList;

public class Continuar extends AppCompatActivity {


    TextView tv_c_transecto, tv_c_sumaTransecto;
    ConexionSQLite conexionSQLite;
    /*****  RecyclerView ******/
    ArrayList<Entidad_Tproyecto> listTproyecto;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuar);

        conexionSQLite = new ConexionSQLite(getApplicationContext() );


        tv_c_transecto = findViewById(R.id.tv_c_transectos);
        /*****  RecyclerView ******/
        listTproyecto = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        llenadoTargetas();
    }
    /*************************************
            llenado RecyclerView
    ***************************************/

    private void llenadoTargetas() {
        SQLiteDatabase ddbb = conexionSQLite.getReadableDatabase();

        Entidad_Tproyecto entidadTproyecto = null;
        Cursor cursor = ddbb.rawQuery("SELECT * FROM "+ UtilidadesSQLite.TABLA_PROYECTO, null);

        while(cursor.moveToNext()){
            entidadTproyecto = new Entidad_Tproyecto();
            entidadTproyecto.setNombre_proyecto(cursor.getString(0));
            /*****  para incluir mas resultados*/
            //   entidad_ttrack.setDensidad(cursor.getInt(1));

            listTproyecto.add(entidadTproyecto);
        }
        AdaptadorRecycler adaptadorRecycler = new AdaptadorRecycler(listTproyecto);
        recyclerView.setAdapter(adaptadorRecycler);

/*
      listDatos = new ArrayList<String>();

        for(int i = 0; i < 20; i++){
            listDatos.add("dato: "+i+" ");
        }
        AdaptadorRecycler adaptadorRecycler = n0ew AdaptadorRecycler(listDatos);
        recyclerView.setAdapter(adaptadorRecycler);*/




    }


    /////////////////////////////////////////////////////
    ///////////////// funcionalidad botones ///////////////
   ////////////// ///////////////////////////////

   /* public void buscar(View view) {
        SQLiteDatabase db = conexionSQLite.getReadableDatabase();
        String [] parametros = {et_c_nombreproyecto.getText().toString()};
        String[] campos = {UtilidadesSQLite.FK_TRANSECTO};

        try {
            Cursor cursor =  db.query(UtilidadesSQLite.TABLA_PROYECTO, campos, UtilidadesSQLite.NOMBRE_PROYECTO+"=?",
                    parametros, null, null, null );
            cursor.moveToFirst();
            tv_c_transecto.setText(cursor.getString(0));

            cursor.close();
        } catch (Exception e) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();;
            et_c_nombreproyecto.setText("");
        }

    }*/
    public void continuar(View view) {

    }

    public void limpiar(View view) {
        tv_c_transecto.setText("");
        tv_c_sumaTransecto.setText("");

    }

}