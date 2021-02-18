package com.noFreeGps.tas21;

import androidx.appcompat.app.AppCompatActivity;

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

    TextView tv_lat, tv_long;
    EditText et_especie, et_cantidad;
    ConexionSQLite conexionSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_transecto);

        tv_lat = findViewById(R.id.tv_lat);
        tv_long = findViewById(R.id.tv_lon);
        et_especie = findViewById(R.id.et_especie);
        et_cantidad = findViewById(R.id.et_cantidad);
        ConexionSQLite conexionSQLite = new ConexionSQLite(this, UtilidadesSQLite.DDBB_NAME, null, 1);

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

        SQLiteDatabase ddbb = conexionSQLite.getWritableDatabase();

    }


}