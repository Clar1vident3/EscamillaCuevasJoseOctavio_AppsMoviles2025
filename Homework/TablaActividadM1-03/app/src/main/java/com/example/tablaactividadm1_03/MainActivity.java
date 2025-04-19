package com.example.tablaactividadm1_03;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ActivityM1-03";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ejecutarActividad();
    }

    private void ejecutarActividad() {
        // Arreglo con 1 millón de elementos enteros aleatorios
        Log.d(TAG, "Creando arreglo de 1,000,000 elementos...");
        int[] arreglo = new int[1000000];
        Random random = new Random();

        long tiempoInicio = System.currentTimeMillis();

        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = random.nextInt();
        }

        // Paso 4: Ordenar los elementos
        Log.d(TAG, "Ordenando arreglo...");
        Arrays.sort(arreglo);

        long tiempoFin = System.currentTimeMillis();
        long tiempoTotal = tiempoFin - tiempoInicio;

        // Paso 5 y 6: Medir e imprimir el tiempo
        Log.d(TAG, "Tiempo total de ejecución: " + tiempoTotal + " milisegundos");
        Log.d(TAG, "Primer elemento: " + arreglo[0]);
        Log.d(TAG, "Último elemento: " + arreglo[arreglo.length - 1]);
    }
}