package com.noFreeGps.tas21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
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

import com.noFreeGps.tas21.SQLite.ConexionSQLite;
import com.noFreeGps.tas21.SQLite.Entidad_Ttrack;
import com.noFreeGps.tas21.SQLite.UtilidadesSQLite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class
VistaTransecto extends AppCompatActivity {

    /****** GUI elements ********/
    TextView tv_lat, tv_long, tv_nombreProyecto, tv_idTransecto;
    EditText et_especie, et_cantidad;
    /****** Fragment ********/
    Fragment fragment_mapa;
    /****** SQLite ********/
    ConexionSQLite conexionSQLite;
    /****** Spinner ********/
    Spinner spinner_especies;
    ArrayList<String> listaEspecies;
    ArrayList<Entidad_Ttrack> entidadesEspecies;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_transecto);

        /****** GUI elements ********/
        tv_lat = findViewById(R.id.tv_lat);
        tv_long = findViewById(R.id.tv_lon);
        et_especie = findViewById(R.id.et_especie);
        et_cantidad = findViewById(R.id.et_cantidad);
        tv_idTransecto = findViewById(R.id.tv_idTransecto);
        tv_nombreProyecto = findViewById(R.id.tv_nombreProyecto);
        spinner_especies = (Spinner) findViewById(R.id.spinner_especies);

        /****** Extra information ********/
         String data1 = getIntent().getStringExtra("extra_1");
        tv_nombreProyecto.setText("Proyecto: "+ data1);
        String data2 = getIntent().getStringExtra("extra_2");
        tv_idTransecto.setText("transecto: " + data2);

        /****** Fragment ********/
        fragment_mapa = new MapsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.marco_fragment, fragment_mapa).commit();

        /****** SQLite ********/
        conexionSQLite = new ConexionSQLite(this, UtilidadesSQLite.DDBB_NAME, null, 1);

        /****** methods ********/
        spinnersqlite();

    }
    /*************************************
    ********  Spinner funciones ***********
    **************************************/
    private void spinnerlist() {
        listaEspecies = new ArrayList<String>();
        listaEspecies.add(" ");

        for(int i = 0; i<entidadesEspecies.size(); i++){
           listaEspecies.add(entidadesEspecies.get(i).getEspecie());
        }

    }

    public void spinnersqlite(){
        /****** SQLite ********/
        SQLiteDatabase ddbb =conexionSQLite.getReadableDatabase();
        Entidad_Ttrack entidad_ttrack= null;
        entidadesEspecies = new ArrayList<Entidad_Ttrack>();

        Cursor cursor = ddbb.rawQuery("SELECT DISTINCT especie FROM "+ UtilidadesSQLite.TABLA_TRACK, null);
        while(cursor.moveToNext()){
            entidad_ttrack = new Entidad_Ttrack();
            entidad_ttrack.setEspecie(cursor.getString(0));
         //   entidad_ttrack.setDensidad(cursor.getInt(1));

            entidadesEspecies.add(entidad_ttrack);
        }
        spinnerlist();
       /*****************************************/
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



    /**********************************
    *******  Validar EditText **********
    ***********************************/
    public String validar(){

        String validacion ="bien";
        String campo1 = et_especie.getText().toString();
        String campo2 = et_cantidad.getText().toString();
        if(campo1.isEmpty() || campo2.isEmpty()){
            validacion = "vacio";

        } else if (campo1.length() > 10 || campo2.length() > 4){
            validacion = "largo";
        }
        return validacion;
    }

    /****************************************
    *******  Funcionalidad Botones  *********
    *****************************************/
    public void terminar(View view) {


        Intent intent = new Intent(this, MainActivity.class);
        Toast.makeText(getApplicationContext(), "BBDD cerrada", Toast.LENGTH_LONG).show();
        startActivity(intent);

    }
    public void enviar(View view) {
            switch (validar()){
            case "vacio":
                Toast.makeText(this, "debes colocar un identificador", Toast.LENGTH_LONG).show();
                break;

            case "largo":
                Toast.makeText(this, "debes colocar cantidad", Toast.LENGTH_LONG).show();
                et_especie.setText("");
                et_cantidad.setText("");
                break;
            default:   enviarInformacion();
        }
    }
    /*******************************************
    *************  Enviar a BBDD ***************
    ********************************************/

    private void enviarInformacion() {

        SQLiteDatabase ddbb = conexionSQLite.getWritableDatabase();

        String insert1 = "INSERT INTO "+UtilidadesSQLite.TABLA_TRACK
                +" ( " +UtilidadesSQLite.ESPECIE+", "+UtilidadesSQLite.DENSIDAD+") "
                +" VALUES ('"+et_especie.getText().toString()+"', '"+et_cantidad.getText().toString()+"')";
        ddbb.execSQL(insert1);

        /**************************************************************************

        **************************************************************************/
        listaEspecies.add(et_especie.getText().toString());
        Set<String> hashSet = new HashSet<String>(listaEspecies);
        listaEspecies.clear();
        listaEspecies.addAll(hashSet);
        et_especie.setText("");
        et_cantidad.setText("");
    }


}