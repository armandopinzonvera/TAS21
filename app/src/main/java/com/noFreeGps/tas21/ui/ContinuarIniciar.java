package com.noFreeGps.tas21.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.noFreeGps.tas21.R;

public class ContinuarIniciar extends AppCompatActivity {

    TextView et_ci_nombreProyecto;
    EditText et_ci_idTransecto;
    String data1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuar_iniciar);

        et_ci_nombreProyecto = findViewById(R.id.et_ci_nombreProyecto);
        et_ci_idTransecto = findViewById(R.id.et_ci_idTransecto);
        data1 = getIntent().getStringExtra("extra_proyecto");
        et_ci_nombreProyecto.setText(data1);
    }

    public void onClickContinuarIniciar(View view) {
    }

    public void onClickContinuarvolver(View view) {
    }
}