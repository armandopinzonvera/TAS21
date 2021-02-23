package com.noFreeGps.tas21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.noFreeGps.tas21.SQLite.ConexionSQLite;
import com.noFreeGps.tas21.SQLite.UtilidadesSQLite;

import java.util.ArrayList;

public class Continuar extends AppCompatActivity {

    ArrayList<String> listDatos;
    RecyclerView recyclerView;

    EditText et_c_nombreproyecto;
    TextView tv_c_transecto, tv_c_sumaTransecto;
    ConexionSQLite conexionSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuar);

        conexionSQLite = new ConexionSQLite(getApplicationContext(), UtilidadesSQLite.DDBB_NAME, null, 1 );

        et_c_nombreproyecto =  findViewById(R.id.et_c_nombreProyecto);
        tv_c_transecto = findViewById(R.id.tv_c_transectos);
        recyclerView = findViewById(R.id.recyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listDatos = new ArrayList<String>();

        for(int i = 0; i < 20; i++){
            listDatos.add("dato: "+i+" ");
        }
        AdaptadorRecycler adaptadorRecycler = new AdaptadorRecycler(listDatos);
        recyclerView.setAdapter(adaptadorRecycler);
    }

    /////////////////////////////////////////////////////
    ///////////////// funcionalidad botones ///////////////
   ////////////// ///////////////////////////////

    public void buscar(View view) {
        SQLiteDatabase db = conexionSQLite.getReadableDatabase();
        String [] parametros = {et_c_nombreproyecto.getText().toString()};
        String[] campos = {UtilidadesSQLite.FK_TRANSECTO};

        try {
            Cursor cursor =  db.query(UtilidadesSQLite.TABLA_PROYECTO, campos, UtilidadesSQLite.NOMBRE_PROYECTO+"=?",
                    parametros, null, null, null );
            cursor.moveToFirst();
            tv_c_transecto.setText(cursor.getString(0));

            cursor.close();
        } catch (Exception e) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();;
            et_c_nombreproyecto.setText("");
        }
        llenadoTargetas();
    }
    public void continuar(View view) {

    }

    public void limpiar(View view) {
        tv_c_transecto.setText("");
        tv_c_sumaTransecto.setText("");

    }
    //////////////////////////////////////
    ////// llenado RecyclerView ///////////
    //////////////////////////////////////

    private void llenadoTargetas() {






    }

}