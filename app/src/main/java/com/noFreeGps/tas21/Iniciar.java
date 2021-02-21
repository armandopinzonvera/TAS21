package com.noFreeGps.tas21;
//This class just take the project's information
// and send it to activity VistaTransecto or return
// to the past View

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.noFreeGps.tas21.SQLite.ConexionSQLite;
import com.noFreeGps.tas21.SQLite.UtilidadesSQLite;

public class Iniciar extends AppCompatActivity  {

    EditText et_nombreProyecto, et_IdTransecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

        et_nombreProyecto = findViewById(R.id.et_nombreProyecto);
        et_IdTransecto = findViewById(R.id.et_idTransecto);

    }

    ///////////////////////////////////
    //////  Functionality Buttons
    ////    Validacion Text
    ///////////////////////////////////
    public String validar(){

        String validacion ="bien";
        String campo1 = et_nombreProyecto.getText().toString();
        String campo2 = et_IdTransecto.getText().toString();
        if(campo1.isEmpty() || campo2.isEmpty()){
            validacion = "vacio";
        }else if (campo1.equals(campo2)){
            validacion = "iguales";
        } else if (campo1.length() > 10 || campo2.length() > 10){
            validacion = "largo";
        }
        return validacion;
    }

    public void onClickIniciar(View view) {
        switch (validar()){
            case "vacio":
                Toast.makeText(this, "No pueden estar vacios", Toast.LENGTH_LONG).show();
                break;
            case "iguales":
                Toast.makeText(this, "No pueden ser iguales", Toast.LENGTH_LONG).show();
                et_IdTransecto.setText("");
                break;
            case "largo":
                Toast.makeText(this, "no pueden ser tan grandes", Toast.LENGTH_LONG).show();
                et_nombreProyecto.setText("");
                et_IdTransecto.setText("");
                break;
            default:   iniciarProyecto();
        }
    }

    public void onClickvolver(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
<<<<<<< Updated upstream


=======
    public void onClickIniciar(View view) {
        startLocationService();
    }
>>>>>>> Stashed changes





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

    private void iniciarProyecto() {

        ConexionSQLite conexionSQLite = new ConexionSQLite(this, UtilidadesSQLite.DDBB_NAME, null, 1);
        SQLiteDatabase ddbb = conexionSQLite.getWritableDatabase();

        String insert = "INSERT INTO "+UtilidadesSQLite.TABLA_PROYECTO
                +" ( " +UtilidadesSQLite.NOMBRE_PROYECTO+", "+UtilidadesSQLite.FK_TRANSECTO+") "
                +" VALUES ('"+et_nombreProyecto.getText().toString()+"', '"+et_IdTransecto.getText().toString()+"')";
        ddbb.execSQL(insert);
        String insert2 = "INSERT INTO "+UtilidadesSQLite.TABLA_TRANSECTO
                +" ( " +UtilidadesSQLite.ID_TRANSECTO+", "+UtilidadesSQLite.FK_TRACK+") "
                +" VALUES ('"+et_IdTransecto.getText().toString()+"', '"+0+"')";

        ddbb.execSQL(insert2);
        et_nombreProyecto.setText("");
        et_IdTransecto.setText("");

        Intent intent = new Intent(getApplicationContext(), VistaTransecto.class);
        startActivity(intent);
    }



<<<<<<< Updated upstream
=======

   
>>>>>>> Stashed changes
}