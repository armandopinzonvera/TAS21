package com.noFreeGps.tas21;
//This class just take the project's information
// and send it to activity VistaTransecto or return
// to the past View

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Iniciar extends AppCompatActivity  {

    EditText et_nombreProyecto, et_IdTransecto;
    Button bt_iniciar, bt_volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

        et_nombreProyecto = findViewById(R.id.et_nombreProyecto);
        et_IdTransecto = findViewById(R.id.et_idTransecto);
        bt_iniciar = findViewById(R.id.bt_iniciar);
        bt_volver = findViewById(R.id.bt_volver);

        bt_iniciar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                iniciar();
            }
        });
        bt_volver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                volver();
            }
        });
    }
    ///////////////////////////////////
    //////  Functionality Buttons  ////
    ///////////////////////////////////
    private void volver() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }
    private void iniciar() {

        startLocationService();

    }

    ///////////////////////////////////
    ///// Permiso Localizacion ////////
    ////////////////////////////////

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    ////////////////////////////////
    ///// enviar Datos proyecto ////
    ///// Iniciar Localizacion /////
    ///// pasar a VistaTransecto ////////
    /////////////////////////////////////////////////////////////

    private void startLocationService() {

        Intent intent = new Intent(getApplicationContext(), VistaTransecto.class);
        startActivity(intent);
    }


}