package com.noFreeGps.tas21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.noFreeGps.tas21.R;
import com.noFreeGps.tas21.SQLite.ConexionSQLite;
import com.noFreeGps.tas21.SQLite.UtilidadesSQLite;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tespecies;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tproyecto;

import java.util.ArrayList;

public class Consultar extends AppCompatActivity {


    TextView tv_c_transecto, tv_c_sumaTransecto;

    ConexionSQLite conexionSQLite;
    // Spinner

    Spinner spinnerProyecto;

    ArrayAdapter entidadArrayAdapter;
    ArrayList<String> listaProyectos;
    ArrayList<Entidad_Tproyecto> entidadTproyectoArrayList;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        conexionSQLite = new ConexionSQLite(this);

        tv_c_transecto = findViewById(R.id.tv_c_transectos);

        spinnerProyecto = (Spinner)findViewById(R.id.spinnerProyecto);



        spinnerProyecto();

    }

    private void spinnerProyecto() {
        SQLiteDatabase ddbb =conexionSQLite.getReadableDatabase();
        Entidad_Tproyecto entidadTproyecto = null;
        entidadTproyectoArrayList = new ArrayList<Entidad_Tproyecto>();

        Cursor cursor = ddbb.rawQuery("SELECT nombre_proyecto FROM "+ UtilidadesSQLite.TABLA_PROYECTO, null);
        while(cursor.moveToNext()){
            entidadTproyecto = new Entidad_Tproyecto();
            entidadTproyecto.setNombre_proyecto(cursor.getString(0));
            entidadTproyectoArrayList.add(entidadTproyecto);
        }
        spinnerlistProyect();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaProyectos);
        spinnerProyecto.setAdapter(adapter);
        spinnerProyecto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void spinnerlistProyect() {
        listaProyectos = new ArrayList<String>();
        for(int i = 0; i<entidadTproyectoArrayList.size(); i++){
            listaProyectos.add(entidadTproyectoArrayList.get(i).getNombre_proyecto());
        }
    }

    public void buscar(View view) {


    }






   /*     public void buscar(View view) {
       SQLiteDatabase db = conexionSQLite.getReadableDatabase();
        String [] parametros = {et_c_nombreproyecto.getText().toString()};
        String[] campos = {UtilidadesSQLite.ID_TRANSECTO};

        try {
            Cursor cursor =  db.query(UtilidadesSQLite.TABLA_PROYECTO, campos, UtilidadesSQLite.NOMBRE_PROYECTO+"=?",
                    parametros, null, null, null );
            cursor.moveToFirst();
            tv_c_transecto.setText(cursor.getString(0));

            cursor.close();
        } catch (Exception e) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();;
            et_c_nombreproyecto.setText("");
        }*/

}