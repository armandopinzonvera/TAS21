package com.noFreeGps.tas21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.noFreeGps.tas21.ui.Continuar;
import com.noFreeGps.tas21.ui.Iniciar;

public class MainActivity extends AppCompatActivity {

    Button bt_Iniciar, bt_continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_Iniciar = findViewById(R.id.bt_iniciar);
        bt_continuar = findViewById(R.id.bt_continuar);

        bt_Iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Iniciar.class);
                startActivity(intent);
            }
        });
        bt_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Continuar.class);
                startActivity(intent);
            }
        });

    }
}