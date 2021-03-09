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
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Ttrack;

import java.util.ArrayList;

public class Consultar extends AppCompatActivity {


    TextView tv_c_transecto; // cantidad de transectos en el proyecto
    TextView tv_inicio;// fecha de inicio
    TextView tv_fin;// fecha finalizacion
    TextView tv_distancia;// Suma distancias tracks
    TextView tv_tiempo; // suma de tiempos de tracks
    TextView tv_alturaMax; // msnm mayor en el track
    TextView tv_alturaMin; // msnm menor en el track
    TextView tv_riqueza; // cantidad de especies diferentes
    TextView tv_abundancia; // cantidad de ind

    String busquedaProyecto;

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
        tv_inicio = findViewById(R.id.tv_inicio);
        tv_fin = findViewById(R.id.tv_fin);
        tv_distancia = findViewById(R.id.tv_distancia);
        tv_tiempo = findViewById(R.id.tv_tiempo);
        tv_alturaMax = findViewById(R.id.tv_alturaMax);
        tv_alturaMin = findViewById(R.id.tv_alturaMin);
        tv_riqueza = findViewById(R.id.tv_riqueza);
        tv_abundancia = findViewById(R.id.tv_abundancia);

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
             busquedaProyecto = parent.getItemAtPosition(position).toString();
              //  Toast.makeText(Consultar.this, "Seleccion: "+busquedaProyecto, Toast.LENGTH_LONG).show();

                cantidadTransectos();


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
            SQLiteDatabase db = conexionSQLite.getReadableDatabase();
            String[] parametros = {/*et_c_nombreproyecto.getText().toString()*/};
            String[] campos = {UtilidadesSQLite.ID_TRACK};

            try {
                Cursor cursor = db.query(UtilidadesSQLite.TABLA_PROYECTO, campos, UtilidadesSQLite.NOMBRE_PROYECTO + "=?",
                        parametros, null, null, null);
                cursor.moveToFirst();
                tv_c_transecto.setText(cursor.getString(0));

                cursor.close();
            } catch (Exception e) {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                ;
               /* et_c_nombreproyecto.setText("");*/
            }
        }
        ///////////////////////////////////////////////////////////////////

        ArrayList<Entidad_Ttrack> entidadTtrackArrayList;

        public void cantidadTransectos(){

            SQLiteDatabase ddbb =conexionSQLite.getReadableDatabase();
            Entidad_Ttrack entidad_Ttrack =  null;
            entidadTtrackArrayList = new ArrayList<Entidad_Ttrack>();

            // Hace la busqueda en la tabla con el nombre de proyecto seleccionado "String busquedaProyecto"

            String consultaCantidadTranck = "SELECT * FROM "+UtilidadesSQLite.TABLA_TRACK+
                    " JOIN "+UtilidadesSQLite.TABLA_PROYECTO+
                    " ON "+UtilidadesSQLite.FK_ID_PROYECTO_TR+" = "+UtilidadesSQLite.NOMBRE_PROYECTO+
                    " WHERE "+UtilidadesSQLite.NOMBRE_PROYECTO+" = '"+busquedaProyecto+"'; ";

            Cursor cursor = ddbb.rawQuery(consultaCantidadTranck, null);

            int indexColumna = cursor.getColumnIndex(UtilidadesSQLite.ID_TRACK);

            while(cursor.moveToNext()){
                entidad_Ttrack = new Entidad_Ttrack();
                entidad_Ttrack.setId_track(cursor.getString(indexColumna));
                entidadTtrackArrayList.add(entidad_Ttrack);
                int largoArray = entidadTtrackArrayList.size();

              //  Toast.makeText(Consultar.this, Integer.toString(largoArray) , Toast.LENGTH_LONG).show();
                tv_c_transecto.setText(Integer.toString(largoArray));
            }

        }


}