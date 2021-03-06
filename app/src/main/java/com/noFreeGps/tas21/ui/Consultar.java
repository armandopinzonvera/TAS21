package com.noFreeGps.tas21.ui;
/**
 * This activity has a Spinner whit the list of projects name.
 * in which the user selects what he cares about and it's show the
 * information of the set of tracks as begins and finish date,
 * length, time, height, richness and density.
 * Allowing deletes or edits the project name and id track
 * also it gives the option to download the info in .xlsx
 * So here we can find the following methods:
 *
 *
 */

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.contentcapture.DataShareWriteAdapter;
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
import com.noFreeGps.tas21.SQLite.implementaciones.Dao_Tespecies_Imp;
import com.noFreeGps.tas21.SQLite.implementaciones.Dao_Tproyecto_Imp;
import com.noFreeGps.tas21.SQLite.implementaciones.Dao_Ttrack_Imp;
import com.noFreeGps.tas21.SQLite.interfaces.Dao_Tespecie;
import com.noFreeGps.tas21.SQLite.interfaces.Dao_Tproyecto;
import com.noFreeGps.tas21.SQLite.interfaces.Dao_Ttrack;

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
    //************************************ Spinner
    Spinner spinnerProyecto;
    ArrayAdapter entidadArrayAdapter;
    ArrayList<String> listaProyectos;
    ArrayList<Entidad_Tproyecto> entidadTproyectoArrayList;

    Dao_Ttrack dao_ttrack = new Dao_Ttrack_Imp(this);
    Dao_Tespecie dao_tespecie = new Dao_Tespecies_Imp(this);

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
                                          //////////////////////////////////////
    ///////////////////////////////     //     Fill Spinner Method
                                         ///////////////////////////////////////
    private void spinnerProyecto() {

        Dao_Tproyecto daoTproyecto = new Dao_Tproyecto_Imp(this);

        listaProyectos = new ArrayList<String>();
        for(int i = 0; i<daoTproyecto.llenarSpinnerProyecto().size(); i++){
            listaProyectos.add(daoTproyecto.llenarSpinnerProyecto().get(i).getNombre_proyecto());
        }
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaProyectos);
        spinnerProyecto.setAdapter(adapter);
        spinnerProyecto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             busquedaProyecto = parent.getItemAtPosition(position).toString();
               //int cantidad =  conexionSQLite.cantidadtransectos(busquedaProyecto);
                Entidad_Ttrack cantidadT = dao_ttrack.resultadoconsultarTrack(busquedaProyecto).get(0);
                Entidad_Ttrack inicioT = dao_ttrack.resultadoconsultarTrack(busquedaProyecto).get(1);
                Entidad_Ttrack finT = dao_ttrack.resultadoconsultarTrack(busquedaProyecto).get(2);

                tv_c_transecto.setText(cantidadT.getId_track());
                tv_inicio.setText(inicioT.getFecha());
                tv_fin.setText(finT.getFecha());
                /*tv_distancia
                tv_tiempo*/
               // tv_alturaMax.setText(alturaMaxT.getAltura());
               // tv_alturaMin.setText(alturaMinT.getAltura());

                Entidad_Tespecies riqueza = dao_tespecie.resultadoConsultar(busquedaProyecto).get(0);
              //  Entidad_Tespecies abundancia = dao_tespecie.resultadoConsultar(busquedaProyecto).get(1);
                tv_riqueza.setText(riqueza.getEspecie());
                //tv_abundancia.setText(abundancia.getDensidad());



                //tv_c_transecto.setText(Integer.toString(cantidad));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

        public void buscar(View view) {

        }



}