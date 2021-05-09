package com.noFreeGps.tas21.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.noFreeGps.tas21.MainActivity;
import com.noFreeGps.tas21.R;
import com.noFreeGps.tas21.SQLite.ConexionSQLite;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tespecies;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tproyecto;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Ttrack;
import com.noFreeGps.tas21.SQLite.implementaciones.Dao_Tespecies_Imp;
import com.noFreeGps.tas21.SQLite.implementaciones.Dao_Ttrack_Imp;
import com.noFreeGps.tas21.SQLite.interfaces.Dao_Tespecie;
import com.noFreeGps.tas21.SQLite.interfaces.Dao_Ttrack;
import com.noFreeGps.tas21.config.ServiceLocation;
import com.noFreeGps.tas21.config.ValidarEditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.noFreeGps.tas21.config.ServiceLocation.DATO_ALTURA;
import static com.noFreeGps.tas21.config.ServiceLocation.DATO_LATITUD;
import static com.noFreeGps.tas21.config.ServiceLocation.DATO_LONGITUD;


public class VistaTransecto extends AppCompatActivity {
    TextView tv_lat, tv_long, tv_nombreProyecto, tv_idTransecto;
    EditText et_especie, et_cantidad;
    Fragment fragment_mapa;
    ConexionSQLite conexionSQLite;
    Spinner spinner_especies;
    ArrayAdapter entidadArrayAdapter;

    String nombreProyectoString, idTransectoString, editTextunoValidarString, editTextdosValidarString, msnmString;


    private BroadcastReceiver broadcastReceiver;


    @Override
    protected void onStart() {
        super.onStart();

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_transecto);

        tv_lat = findViewById(R.id.tv_lat);
        tv_long = findViewById(R.id.tv_lon);
        et_especie = findViewById(R.id.et_especie);
        et_cantidad = findViewById(R.id.et_cantidad);
        tv_idTransecto = findViewById(R.id.tv_idTransecto);
        tv_nombreProyecto = findViewById(R.id.tv_nombreProyecto);
        spinner_especies = (Spinner) findViewById(R.id.spinner_especies);
        // Extra information
        nombreProyectoString = getIntent().getStringExtra("extra_1");
        tv_nombreProyecto.setText("Proyecto: " + nombreProyectoString);
        idTransectoString = getIntent().getStringExtra("extra_2");
        tv_idTransecto.setText("transecto: " + idTransectoString);
        // Fragment
        fragment_mapa = new MapsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.marco_fragment, fragment_mapa).commit();
        //class
        conexionSQLite = new ConexionSQLite(this);
        //methods
        spinnersqlite();
        llenarWigets();
    }


    ArrayList<String> listaEspecies;

    public void spinnersqlite() {

        Dao_Tespecie daoTespecie = new Dao_Tespecies_Imp(this);
        listaEspecies = new ArrayList<String>();
        listaEspecies.add(" ");
        for (int i = 0; i < daoTespecie.datosParaSpinner().size(); i++) {
            listaEspecies.add(daoTespecie.datosParaSpinner().get(i).getEspecie());
        }

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaEspecies);
        spinner_especies.setAdapter(adapter);

        spinner_especies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                et_especie.setText(parent.getItemAtPosition(position).toString());
               /* Toast.makeText(VistaTransecto.this,
                        "Seleccion: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void terminar(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        Toast.makeText(getApplicationContext(), "BBDD cerrada", Toast.LENGTH_LONG).show();
        startActivity(intent);

    }

    public void enviar(View view) {

        editTextunoValidarString = et_especie.getText().toString().trim();
        editTextdosValidarString = et_cantidad.getText().toString().trim();

        ValidarEditText validarEditText = new ValidarEditText(this);
        if (validarEditText.compararEditText(editTextunoValidarString, editTextdosValidarString)) {
            enviarInformacion();
        }
    }

    private void enviarInformacion() {
        Entidad_Tespecies entidadTespecies = null;
        Entidad_Ttrack entidadTtrack = null;
        Entidad_Tproyecto entidadTproyecto = null;
        try {
            entidadTespecies = new Entidad_Tespecies(1, et_especie.getText().toString().trim(), Integer.parseInt(et_cantidad.getText().toString()), idTransectoString, nombreProyectoString);
            Toast.makeText(this, entidadTespecies.toString(), Toast.LENGTH_LONG).show();
        } catch (NumberFormatException e) {
            entidadTespecies = new Entidad_Tespecies(-1, "error", 0, "error", "error");
        }

        try {
            entidadTtrack = new Entidad_Ttrack(idTransectoString, "fecha", "hora", Float.parseFloat(tv_long.getText().toString()), Float.parseFloat(tv_lat.getText().toString()), Integer.parseInt(msnmString), nombreProyectoString);
        } catch (NumberFormatException e) {
             entidadTtrack = new Entidad_Ttrack(" ", " ", " ", 0.0f, 0.0f, 1, " ");
        }

        Dao_Tespecie daoTespecie = new Dao_Tespecies_Imp(this);
        Dao_Ttrack daoTtrack = new Dao_Ttrack_Imp(this);

        daoTespecie.addDatoEspecie(entidadTespecies);
        daoTtrack.addDatoTtrack(entidadTtrack);

        listaEspecies.add(et_especie.getText().toString().trim());
        Set<String> hashSet = new HashSet<String>(listaEspecies);
        listaEspecies.clear();
        listaEspecies.addAll(hashSet);

        et_especie.setText("");
        et_cantidad.setText("");
    }

    private void llenarWigets() {

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                tv_lat.setText(intent.getStringExtra(DATO_LATITUD));
                tv_long.setText(intent.getStringExtra(DATO_LONGITUD));
                msnmString = intent.getStringExtra(DATO_ALTURA);
            }
        };
    }



    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter(ServiceLocation.INTENT_RECEIVER));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        
    }
}