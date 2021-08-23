package com.noFreeGps.tas21.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.noFreeGps.tas21.MainActivity;
import com.noFreeGps.tas21.R;
import com.noFreeGps.tas21.SQLite.ConexionSQLite;
import com.noFreeGps.tas21.SQLite.UtilidadesSQLite;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tproyecto;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Ttrack;
import com.noFreeGps.tas21.SQLite.implementaciones.Dao_Tespecies_Imp;
import com.noFreeGps.tas21.SQLite.implementaciones.Dao_Tproyecto_Imp;
import com.noFreeGps.tas21.SQLite.implementaciones.Dao_Ttrack_Imp;
import com.noFreeGps.tas21.SQLite.interfaces.Dao_Tespecie;
import com.noFreeGps.tas21.SQLite.interfaces.Dao_Tproyecto;
import com.noFreeGps.tas21.SQLite.interfaces.Dao_Ttrack;
import com.noFreeGps.tas21.config.PermisoLocation;
import com.noFreeGps.tas21.config.ValidarEditText;

import java.util.ArrayList;

public class ContinuarIniciar extends AppCompatActivity {

    TextView et_ci_nombreProyecto;
    EditText et_ci_idTrack;
    Dao_Ttrack daoTtrack = new Dao_Ttrack_Imp(this);
    Dao_Tproyecto daoTproyecto = new Dao_Tproyecto_Imp(this);
    Dao_Tespecie daoTespecie = new Dao_Tespecies_Imp(this);
    String data1, nombreProyecto,  idTrack;

    PermisoLocation permisoLocation = new PermisoLocation(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuar_iniciar);

        et_ci_nombreProyecto = findViewById(R.id.et_ci_nombreProyecto);
        et_ci_idTrack = findViewById(R.id.et_ci_idTransecto);
        data1 = getIntent().getStringExtra("extra_proyecto");
        et_ci_nombreProyecto.setText(data1);
    }

    public void onClickContinuarIniciar(View view) {

        nombreProyecto = et_ci_nombreProyecto.getText().toString().trim();
        idTrack = et_ci_idTrack.getText().toString().trim();

        ValidarEditText validarEditText = new ValidarEditText(this);

        if (validarEditText.compararEditText(nombreProyecto, idTrack)) {
            if (!daoTtrack.verificarNuevoTrack(et_ci_idTrack.getText().toString().trim()).equals("existe")) {

                iniciarLocalizacion();

            }
        }
    }

    public void iniciarLocalizacion(){

        if(permisoLocation.verificacionInicialPermiso()){
            pasoaVistatransecto();
        } else {
            permisoLocation.solicitarPermisoLocation(101);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(permisoLocation.verificacionFinalPermisoLocation(requestCode, grantResults)){

            Toast.makeText(this, "      PERMISO NEGADO     ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        } else {
            pasoaVistatransecto();
        }
    }

    public void pasoaVistatransecto(){
        Intent intent = new Intent(getApplicationContext(), VistaTransecto.class);
        intent.putExtra("extra_1", et_ci_nombreProyecto.getText().toString());
        intent.putExtra("extra_2", et_ci_idTrack.getText().toString());

        daoTproyecto.iniciarProyecto(nombreProyecto);
        daoTtrack.iniciarTrack(nombreProyecto, idTrack);
       // daoTespecie.iniciarTespecies(idTrack, nombreProyecto); // Colocaba valor null en la BBDD

        et_ci_idTrack.setText("");
        startActivity(intent);
    }

    public void onClickContinuarvolver(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }



}