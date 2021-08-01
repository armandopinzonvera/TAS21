  package com.noFreeGps.tas21.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
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
import com.noFreeGps.tas21.config.FechayCronometro;
import com.noFreeGps.tas21.config.ServiceLocation;
import com.noFreeGps.tas21.config.ValidarEditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.noFreeGps.tas21.config.ServiceLocation.DATO_ALTURA;
import static com.noFreeGps.tas21.config.ServiceLocation.DATO_LATITUD;
import static com.noFreeGps.tas21.config.ServiceLocation.DATO_LONGITUD;


public class VistaTransecto extends AppCompatActivity {
    TextView tv_lat, tv_long, tv_nombreProyecto, tv_idTransecto, tv_msnm;
    EditText et_especie, et_cantidad;
    Chronometer tv_chronometer;
    Fragment fragment_mapa;
    ConexionSQLite conexionSQLite;
    FechayCronometro fechayCronometro;
    Spinner spinner_especies;
    private static final String TAG = "VistaTransecto";
    String nombreProyectoString, idTransectoString, editTextunoValidarString, editTextdosValidarString, msnmString;
    String latitudString, longitudString;
    Bundle bundleLocationData;


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
        tv_msnm = findViewById(R.id.tv_msnm);
        tv_chronometer = findViewById(R.id.tv_chronometer);
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

        fragment_mapa = new MapsFragment(); /** **/



        //class
        conexionSQLite = new ConexionSQLite(this);
        fechayCronometro = new FechayCronometro();
        //methods
        spinnersqlite();
        llenarWigets();
    }


    ArrayList<String> listaEspecies;

    public void spinnersqlite() {

        Dao_Tespecie daoTespecie = new Dao_Tespecies_Imp(this);
        listaEspecies = new ArrayList<String>();
        //listaEspecies.add(" ");
        for (int i = 0; i < daoTespecie.datosParaSpinner().size(); i++) {
            listaEspecies.add(daoTespecie.datosParaSpinner().get(i).getEspecie());
        }
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaEspecies);
        spinner_especies.setAdapter(adapter);

        spinner_especies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                et_especie.setText(parent.getItemAtPosition(position).toString());

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
        Entidad_Ttrack entidadTtrack;
        Entidad_Tproyecto entidadTproyecto = null;
        if(et_especie.getText().toString().trim() != "null"){
        try {
            entidadTespecies = new Entidad_Tespecies(1, et_especie.getText().toString().trim(), Integer.parseInt(et_cantidad.getText().toString()), idTransectoString, nombreProyectoString);

        } catch (NumberFormatException e) {
            entidadTespecies = new Entidad_Tespecies(-1, "error", 0, "error", "error");
        }
        } // cierre if - not null
        try {
            entidadTtrack = new Entidad_Ttrack(idTransectoString, "fecha", "hora", tv_long.getText().toString(), tv_lat.getText().toString(), tv_msnm.getText().toString(), nombreProyectoString);
            Toast.makeText(this, entidadTtrack.toString(), Toast.LENGTH_LONG).show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
             entidadTtrack = new Entidad_Ttrack(" ", " ", " ", "0.0f", "0.0f", " ", " ");
             Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
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

        latitudString = tv_lat.getText().toString();
        longitudString = tv_long.getText().toString();
        sendLocationToMap();
    }

    //  This method send the Data To the Fragment

    public void sendLocationToMap(){


        MapsFragment mapsFragment = new MapsFragment();
        /******************************/

        fragment_mapa.setArguments(bundleLocationData);
        /******************************/

        mapsFragment.getlocationData();
    }


    private void llenarWigets() {

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                tv_lat.setText(intent.getStringExtra(DATO_LATITUD));
                
                tv_long.setText(intent.getStringExtra(DATO_LONGITUD));
                msnmString = intent.getStringExtra(DATO_ALTURA);
                tv_msnm.setText(msnmString);

                System.out.println("XXX11: "+ tv_lat.getText().toString().isEmpty());
            }
        };
        tv_chronometer = fechayCronometro.iniciarCronometro(tv_chronometer);
 /**     // // / // / /      ***/

    // OJO: LO traje de sendLocationToMap(), para establecer el bundle antes del fragment
        Bundle bundleLocationData  = new Bundle();
        bundleLocationData.putString("latitudKey", latitudString);
        bundleLocationData.putString("longitudKey", longitudString);

        System.out.println(TAG +":  " +  latitudString + ", "+ latitudString);

  /***     / / // / / // /      ***/

        getSupportFragmentManager().beginTransaction().replace(R.id.marco_fragment, fragment_mapa).commit();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intentService = new Intent(this, ServiceLocation.class);
        stopService(intentService);
    }
}