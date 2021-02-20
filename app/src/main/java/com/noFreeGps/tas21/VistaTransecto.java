package com.noFreeGps.tas21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.noFreeGps.tas21.SQLite.ConexionSQLite;
import com.noFreeGps.tas21.SQLite.UtilidadesSQLite;

public class VistaTransecto extends AppCompatActivity {

    TextView tv_lat, tv_long, tv_nombreProyecto, tv_idTransecto;
    EditText et_especie, et_cantidad;
    Fragment fragment_mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_transecto);

        tv_lat = findViewById(R.id.tv_lat);
        tv_long = findViewById(R.id.tv_lon);
        et_especie = findViewById(R.id.et_especie);
        et_cantidad = findViewById(R.id.et_cantidad);
        tv_idTransecto = findViewById(R.id.tv_idTransecto);
        tv_nombreProyecto = findViewById(R.id.tv_nombreProyecto);


       String data1 = getIntent().getStringExtra("extra_1");
        tv_nombreProyecto.setText("Proyecto: "+ data1);
        String data2 = getIntent().getStringExtra("extra_2");
        tv_idTransecto.setText("transecto: " + data2);

        fragment_mapa = new MapFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.marco_fragment, fragment_mapa).commit();
    }

    ////////////////////////////////////
    /////   Validar EditText  /////////
    ///////////////////////////////////
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

    ///////////////////////////////////////////
    ///////  Funcionalidad Botones  ///////////
    ///////////////////////////////////////////
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

    private void enviarInformacion() {
        ConexionSQLite conexionSQLite = new ConexionSQLite(this, UtilidadesSQLite.DDBB_NAME, null, 1);
        SQLiteDatabase ddbb = conexionSQLite.getWritableDatabase();

        String insert1 = "INSERT INTO "+UtilidadesSQLite.TABLA_TRACK
                +" ( " +UtilidadesSQLite.ESPECIE+", "+UtilidadesSQLite.DENSIDAD+") "
                +" VALUES ('"+et_especie.getText().toString()+"', '"+et_cantidad.getText().toString()+"')";
        ddbb.execSQL(insert1);

    }


}