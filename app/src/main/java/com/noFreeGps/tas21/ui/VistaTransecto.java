package com.noFreeGps.tas21.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.noFreeGps.tas21.MainActivity;
import com.noFreeGps.tas21.R;
import com.noFreeGps.tas21.SQLite.ConexionSQLite;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tespecies;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tproyecto;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Ttrack;
import com.noFreeGps.tas21.SQLite.implementaciones.Dao_Tespecies_Imp;
import com.noFreeGps.tas21.SQLite.interfaces.Dao_Tespecie;
import com.noFreeGps.tas21.config.PermisoLocation;
import com.noFreeGps.tas21.config.ServiceLocation;
import com.noFreeGps.tas21.config.ValidarEditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.noFreeGps.tas21.config.ServiceLocation.DATO_LATITUD;
import static com.noFreeGps.tas21.config.ServiceLocation.DATO_LONGITUD;


public class
VistaTransecto extends AppCompatActivity {

    TextView tv_lat, tv_long, tv_nombreProyecto, tv_idTransecto;
    EditText et_especie, et_cantidad;
    Fragment fragment_mapa;
    ConexionSQLite conexionSQLite;
    Spinner spinner_especies;

    String data1;
    String data2;
    String campo1;
    String campo2;

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
        data1 = getIntent().getStringExtra("extra_1");
        tv_nombreProyecto.setText("Proyecto: " + data1);
        data2 = getIntent().getStringExtra("extra_2");
        tv_idTransecto.setText("transecto: " + data2);

      /*  tv_long.setText(getIntent().getStringExtra("dato_longitud"));
        tv_lat.setText(getIntent().getStringExtra("dato_latitud"));*/
        // Fragment
        fragment_mapa = new MapsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.marco_fragment, fragment_mapa).commit();

        conexionSQLite = new ConexionSQLite(this);

        spinnersqlite();

    }

    ArrayList<String> listaEspecies;
    public void spinnersqlite() {

        Dao_Tespecie daoTespecie = new Dao_Tespecies_Imp(this);
        listaEspecies = new ArrayList<String>();
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

        campo1 = et_especie.getText().toString().trim();
        campo2 = et_cantidad.getText().toString().trim();

        ValidarEditText validarEditText = new ValidarEditText(this);
        if (validarEditText.compararEditText(campo1, campo2)) {
            enviarInformacion();
        }
    }

    private void enviarInformacion() {
        Entidad_Tespecies entidadTespecies = null;
        Entidad_Ttrack entidadTtrack = null;
        Entidad_Tproyecto entidadTproyecto = null;
        try {
            entidadTespecies = new Entidad_Tespecies(1, et_especie.getText().toString().trim(), Integer.parseInt(et_cantidad.getText().toString()), data2, data1);
            Toast.makeText(this, entidadTespecies.toString(), Toast.LENGTH_LONG).show();
        } catch (NumberFormatException e) {
            entidadTespecies = new Entidad_Tespecies(-1, "error", 0, "error", "error");
        }
        Dao_Tespecie daoTespecie = new Dao_Tespecies_Imp(this);
        daoTespecie.addDatoEspecie(entidadTespecies);


        listaEspecies.add(et_especie.getText().toString().trim());
        Set<String> hashSet = new HashSet<String>(listaEspecies);
        listaEspecies.clear();
        listaEspecies.addAll(hashSet);

        et_especie.setText("");
        et_cantidad.setText("");
    }


    public void llenarWigets( String latitud, String longitud) {
        tv_lat.setText(latitud);
        tv_long.setText(longitud);

       broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                /*String latitudString = String.valueOf(intent.getStringExtra("dato_latitud"));
                String longitudString = String.valueOf(intent.getStringExtra("dato_longitud"));*/

            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter("longitud_update"));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(broadcastReceiver == null){
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    String latitudString = String.valueOf(intent.getStringExtra("dato_latitud"));
                    String longitudString = String.valueOf(intent.getStringExtra("dato_longitud"));
                    //String alturaString = String.valueOf(intent.getStringExtra("dato_altura"));

                    tv_long.setText(longitudString);
                    tv_lat.setText(latitudString);
                }
            };
        }
        registerReceiver(broadcastReceiver, new IntentFilter("longitud_update"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(broadcastReceiver != null){
            unregisterReceiver(broadcastReceiver);
        }
        Intent intentService = new Intent(getApplicationContext(), ServiceLocation.class);
        stopService(intentService);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       Iniciar iniciar = new Iniciar();
       stopService(iniciar.intentService);

    }
}