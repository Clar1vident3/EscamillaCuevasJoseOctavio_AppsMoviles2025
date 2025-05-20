package com.example.actividad_m3_01;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Calculadora calculadora = new Calculadora();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Log.d("Calculadora","Resultado de la suma" + calculadora.sumar(5,2));
        Log.d("Calculadora","Resultado de la resta" + calculadora.restar(7,8));
        Log.d("Calculadora","Resultado de la multiplicacion" + calculadora.multiplicar(10,2));
        Log.d("Calculadora","Resultado de la division" + calculadora.dividir(90,2));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}