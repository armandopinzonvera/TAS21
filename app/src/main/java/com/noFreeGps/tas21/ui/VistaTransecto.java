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
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Ttransecto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class
VistaTransecto extends AppCompatActivity {

    // GUI elements
    TextView tv_lat, tv_long, tv_nombreProyecto, tv_idTransecto;
    EditText et_especie, et_cantidad;
    // Fragment
    Fragment fragment_mapa;
    // SQLite
    ConexionSQLite conexionSQLite;
    // Spinner
    Spinner spinner_especies;
    ArrayAdapter entidadArrayAdapter;

    String data1;
    String data2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_transecto);

        // GUI elements
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
        // SQLite
        conexionSQLite = new ConexionSQLite(this);

        // methods
      spinnersqlite();
    }

    //     Spinner funciones
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



    /**********************************
    *******  Validar EditText **********
    ***********************************/
    public String validar(){

        String validacion ="bien";
        String campo1 = et_especie.getText().toString();
        String campo2 = et_cantidad.getText().toString();
        if(campo1.isEmpty() || campo2.isEmpty()){
            validacion = "vacio";

        } else if (campo1.length() > 10 || campo2.length() > 4) {
            validacion = "largo";
         }else if (Integer.parseInt(campo2) < 1){
            validacion = "cero";

        }
        return validacion;
    }

    //  Funcionalidad Botones

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

                case "cero"   :
                    Toast.makeText(this, "no puede ser menor de 1", Toast.LENGTH_LONG).show();
                    et_cantidad.setText("");
                    break;
            default:   enviarInformacion();
        }
    }
    //   Enviar a BBDD

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
        conexionSQLite = new ConexionSQLite(this);
        conexionSQLite.addDatoTespecies(entidadTespecies);



        listaEspecies.add(et_especie.getText().toString().trim());
        Set<String> hashSet = new HashSet<String>(listaEspecies);
        listaEspecies.clear();
        listaEspecies.addAll(hashSet);

        et_especie.setText("");
        et_cantidad.setText("");
    }
    public void mostrarListView(ConexionSQLite conexionSQLite1){

       // entidadArrayAdapter = new ArrayAdapter<Entidad_Tespecies>(VistaTransecto.this, android.R.layout.simple_expandable_list_item_1, conexionSQLite1.getEveryoneEspecie());

      //    spinner_especies.setAdapter(entidadArrayAdapter);
    }

}