package com.noFreeGps.tas21;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.noFreeGps.tas21.SQLite.ConexionSQLite;
import com.noFreeGps.tas21.SQLite.UtilidadesSQLite;

public class Continuar extends AppCompatActivity {

    EditText et_c_nombreproyecto;
    TextView tv_c_transecto, tv_c_sumaTransecto;
    ConexionSQLite conexionSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuar);

        conexionSQLite = new ConexionSQLite(getApplicationContext(), UtilidadesSQLite.DDBB_NAME, null, 1 );

        et_c_nombreproyecto = findViewById(R.id.et_nombreProyecto);
        tv_c_transecto = findViewById(R.id.tv_c_transecto);
        tv_c_sumaTransecto = findViewById(R.id.tv_c_sumaTransecto);

    }

    public void buscar(View view) {
        SQLiteDatabase db = conexionSQLite.getReadableDatabase();
        String´+
    }

    public void continuar(View view) {

    }

}