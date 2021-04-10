package com.noFreeGps.tas21.ui;

//************************************
//****************** This class just take the project's information
//****************** and send it to activity VistaTransecto or return
//****************** to the past View
//************************************

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.noFreeGps.tas21.MainActivity;
import com.noFreeGps.tas21.R;
import com.noFreeGps.tas21.SQLite.ConexionSQLite;
import com.noFreeGps.tas21.SQLite.UtilidadesSQLite;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tproyecto;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Ttrack;
import com.noFreeGps.tas21.SQLite.implementaciones.Dao_Tproyecto_Imp;
import com.noFreeGps.tas21.SQLite.implementaciones.Dao_Ttrack_Imp;
import com.noFreeGps.tas21.SQLite.interfaces.Dao_Tproyecto;
import com.noFreeGps.tas21.SQLite.interfaces.Dao_Ttrack;
import com.noFreeGps.tas21.config.PermisoLocation;
import com.noFreeGps.tas21.config.ValidarEditText;

import java.util.ArrayList;

public class Iniciar extends AppCompatActivity  {

    EditText et_nombreProyecto, et_IdTrack;
    String nombreProyecto, idTrack;
    Dao_Tproyecto daoTproyecto = new Dao_Tproyecto_Imp(this);
    Dao_Ttrack daoTtrack = new Dao_Ttrack_Imp(this);
    ConexionSQLite conexionSQLite;

    PermisoLocation permisoLocation = new PermisoLocation(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

        et_nombreProyecto = findViewById(R.id.et_nombreProyecto);
        et_IdTrack = findViewById(R.id.et_idTransecto);
    }
                                          //////////////////////////////////////
    ///////////////////////////////     //     Function buttons
                                         ////////////////////////////////////////

    public void onClickIniciar(View view) {
        nombreProyecto = et_nombreProyecto.getText().toString().trim();
        idTrack = et_IdTrack.getText().toString().trim();

        ValidarEditText validarEditText = new ValidarEditText(this);

        if (validarEditText.compararEditText(nombreProyecto, idTrack)){
            if( !daoTproyecto.verificarExiteProyecto(et_nombreProyecto.getText().toString().trim()).equals("existe")){

                daoTproyecto.iniciarProyecto(nombreProyecto);
                daoTtrack.iniciarTrack(nombreProyecto, idTrack);

            }
            }
        iniciarLocalizacion();
        }


    public void onClickvolver(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
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
        intent.putExtra("extra_1", et_nombreProyecto.getText().toString());
        intent.putExtra("extra_2", et_IdTrack.getText().toString());

        et_nombreProyecto.setText("");
        et_IdTrack.setText("");
        startActivity(intent);
    }


}