package com.noFreeGps.tas21.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
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

import com.noFreeGps.tas21.MainActivity;
import com.noFreeGps.tas21.R;
import com.noFreeGps.tas21.SQLite.ConexionSQLite;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tespecies;
import com.noFreeGps.tas21.SQLite.UtilidadesSQLite;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tproyecto;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Ttrack;
import com.noFreeGps.tas21.SQLite.implementaciones.Dao_Tespecies_Imp;
import com.noFreeGps.tas21.SQLite.interfaces.Dao_Tespecie;
import com.noFreeGps.tas21.config.ValidarEditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class
VistaTransecto extends AppCompatActivity {

    TextView tv_lat, tv_long, tv_nombreProyecto, tv_idTransecto;
    EditText et_especie, et_cantidad;

    Fragment fragment_mapa;

    ConexionSQLite conexionSQLite;

    Spinner spinner_especies;
    ArrayAdapter entidadArrayAdapter;

    String data1;
    String data2;

    String campo1;
    String campo2;

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
        tv_nombreProyecto.setText("Proyecto: "+ data1);
        data2 = getIntent().getStringExtra("extra_2");
        tv_idTransecto.setText("transecto: " + data2);
        // Fragment
        fragment_mapa = new MapsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.marco_fragment, fragment_mapa).commit();

        conexionSQLite = new ConexionSQLite(this);

        spinnersqlite();

    }

       ArrayList<String> listaEspecies;
       ArrayList<Entidad_Tespecies> entidadesEspecies;

    private void spinnerlist() {
        listaEspecies = new ArrayList<String>();
        listaEspecies.add(" ");

        for(int i = 0; i<entidadesEspecies.size(); i++){
           listaEspecies.add(entidadesEspecies.get(i).getEspecie());
        }
    }
    public void spinnersqlite(){
        // SQLite
        SQLiteDatabase ddbb =conexionSQLite.getReadableDatabase();
        Entidad_Tespecies entidad_ttrack= null;
        entidadesEspecies = new ArrayList<Entidad_Tespecies>();

        Cursor cursor = ddbb.rawQuery("SELECT DISTINCT especie FROM "+ UtilidadesSQLite.TABLA_ESPECIES, null);
        while(cursor.moveToNext()){
            entidad_ttrack = new Entidad_Tespecies();
            entidad_ttrack.setEspecie(cursor.getString(0));

            entidadesEspecies.add(entidad_ttrack);
        }
        spinnerlist();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaEspecies);
        spinner_especies.setAdapter( adapter);

        spinner_especies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                et_especie.setText(parent.getItemAtPosition(position).toString());
                Toast.makeText(VistaTransecto.this,
                        "Seleccion: "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }



    //  Funcionalidad Botones

    public void terminar(View view) {


        Intent intent = new Intent(this, MainActivity.class);
        Toast.makeText(getApplicationContext(), "BBDD cerrada", Toast.LENGTH_LONG).show();
        startActivity(intent);

    }
    public void enviar(View view) {

        campo1 = et_especie.getText().toString().trim();
        campo2 = et_cantidad.getText().toString().trim();

        ValidarEditText validarEditText = new ValidarEditText(this);
        if(validarEditText.compararEditText(campo1, campo2)){
            enviarInformacion();
        }

    }


    private void enviarInformacion() {
        Entidad_Tespecies entidadTespecies;
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


}