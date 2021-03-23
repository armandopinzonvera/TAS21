package com.noFreeGps.tas21.ui;

//************************************
//****************** This class just take the project's information
//****************** and send it to activity VistaTransecto or return
//****************** to the past View
//************************************

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.ArrayList;

public class Iniciar extends AppCompatActivity  {

    EditText et_nombreProyecto, et_IdTrack;
    ConexionSQLite conexionSQLite;
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

        verificarNuevoProyecto(et_nombreProyecto.getText().toString().trim());

        switch (validar()){
            case "vacio":
                Toast.makeText(this, "No pueden estar vacios", Toast.LENGTH_LONG).show();
                break;
            case "iguales":
                Toast.makeText(this, "No pueden ser iguales", Toast.LENGTH_LONG).show();
                et_IdTrack.setText("");
                break;
            case "largo":
                Toast.makeText(this, "no pueden ser tan grandes", Toast.LENGTH_LONG).show();
                et_nombreProyecto.setText("");
                et_IdTrack.setText("");
                break;
            case "existe":
                Toast.makeText(this, "proyecto ya existe", Toast.LENGTH_LONG).show();
                et_nombreProyecto.setText("");
                et_IdTrack.setText("");
                break;
            default:   iniciarProyecto();
        }
    }

    public void onClickvolver(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
                                           //////////////////////////////////////
    ///////////////////////////////     //     Validation EditText
                                          ///////////////////////////////////////
    public String validar(){

        String validacion ="bien";
        String campo1 = et_nombreProyecto.getText().toString().trim();
        String campo2 = et_IdTrack.getText().toString().trim();
        if(campo1.isEmpty() || campo2.isEmpty()){
            validacion = "vacio";
        }else if (campo1.equals(campo2)){
            validacion = "iguales";
        } else if (campo1.length() > 10 || campo2.length() > 10){
            validacion = "largo";
        } else if(verificarNuevoProyecto(et_nombreProyecto.getText().toString().trim()).equals("existe")) {
            validacion = "existe";
        }
        return validacion;
    }
//************************************   Verficar Proyecto no Exista en la BBDD
    ArrayList<Entidad_Tproyecto> arrayNombreProyecto;

    private String verificarNuevoProyecto(String nombreProyecto) {

        ConexionSQLite conexion = new ConexionSQLite(this);
        SQLiteDatabase db = conexion.getReadableDatabase();
        arrayNombreProyecto = new ArrayList<Entidad_Tproyecto>();

        Entidad_Tproyecto entidadTproyecto = null;

        String queryProyecto = "SELECT "+UtilidadesSQLite.NOMBRE_PROYECTO+
                " FROM "+UtilidadesSQLite.TABLA_PROYECTO+
                " WHERE "+UtilidadesSQLite.NOMBRE_PROYECTO+
                " = '"+nombreProyecto+"';";

        Cursor cursor = db.rawQuery(queryProyecto, null);
        int columIndex = cursor.getColumnIndex(UtilidadesSQLite.NOMBRE_PROYECTO);

        if(cursor.moveToNext()){
            Toast toast = Toast.makeText(getApplicationContext(), "   EXISTE   ", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0,0);
            toast.show();
            return "existe";
        }
                 return "";

    }

                                         //////////////////////////////////////
    ///////////////////////////////     //     location Permission
                                         ///////////////////////////////////////

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
                                         //////////////////////////////////////
 ///////////////////////////////     //     - Send data and begin a new project
                                     //     - Begin Location - Service
                                     //     - Continue to next activity: VistaTransecto.java
                                         ///////////////////////////////////////
    private void iniciarProyecto() {
        Entidad_Tproyecto entidadTproyecto;
        Entidad_Ttrack entidadTtrack;

        try {
            entidadTtrack = new Entidad_Ttrack(et_IdTrack.getText().toString(),"fecha", "hora", 1.111f, 2.222f,2222, et_nombreProyecto.getText().toString());
            entidadTproyecto = new Entidad_Tproyecto(et_nombreProyecto.getText().toString());

        } catch (Exception e) {
            entidadTproyecto = new Entidad_Tproyecto("error");
            entidadTtrack = new Entidad_Ttrack("error","error", "error", 1.111f, 2.222f,1, " error");
        }
        ConexionSQLite conexionSQLite = new ConexionSQLite(this);

        boolean success1 = conexionSQLite.addDatoTproyecto(entidadTproyecto);
        boolean success3 = conexionSQLite.addDatoTtrack(entidadTtrack);

        Toast.makeText(Iniciar.this, "Exito: "+success1+ ", "+success3, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), VistaTransecto.class);
        intent.putExtra("extra_1", et_nombreProyecto.getText().toString());
        intent.putExtra("extra_2", et_IdTrack.getText().toString());

        //showMessage("Nuevo proyecto creado: ", et_nombreProyecto.getText().toString());
        et_nombreProyecto.setText("");
        et_IdTrack.setText("");
        startActivity(intent);
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}