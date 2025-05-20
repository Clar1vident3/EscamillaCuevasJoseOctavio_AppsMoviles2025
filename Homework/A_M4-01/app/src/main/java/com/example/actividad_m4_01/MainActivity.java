package com.example.actividad_m4_01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Declarar el elemento de texto
    TextView txvEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

       //Imprimir al log de Android el estado de la actividad
       Log.d("onCreate:", "La actividad se ha creado");

       //Inicializar el elemento de texto
       txvEstado = findViewById(R.id.txvEstado);
       //Imprimir al layoout el estado de la actividad
       agregarEstado("onCreate: La actividad se ha creado");

       //Inicializar el botón
        Button btnCambiarTexto = findViewById(R.id.btnCambiarTexto);

        //Definir el comportamiento del botón
        btnCambiarTexto.setOnClickListener(v -> {
            agregarEstado("onClick: La actividad se ha creado");
        });

        //Inicializar el botón
        Button btnIrSegunda = findViewById(R.id.btnIrSegunda);
        //Definir el comportamiento del botón
        btnIrSegunda.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondaryActivity2.class);
            startActivity(intent);
        });

    }

    protected void onStart() {
        super.onStart();
        Log.d("onStart:", "La actividad ha comenzado");
        agregarEstado("onStart: La actividad ha comenzado");
    }

    protected void onResume() {
        super.onResume();
        Log.d("onResume:", "La actividad ha continuado");
        agregarEstado("onResume: La actividad ha continuado");
    }

    protected void onPause() {
        super.onPause();
        Log.d("onPause:", "La actividad se ha pausado");
        agregarEstado("onPause: La actividad se ha pausado");
    }

    protected void onStop() {
        super.onStop();
        Log.d("onStop:", "La actividad se ha detenido");
        agregarEstado("onStop: La actividad se ha detenido");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy:", "La actividad se ha destruido");
        agregarEstado("onDestroy: La actividad se ha destruido");
    }

    //Método para acumular un estado de la actividad
    private void agregarEstado(String estado)
    {
        String estadoActual = txvEstado.getText().toString();
        txvEstado.setText(estadoActual + estado + "\n");
    }
}