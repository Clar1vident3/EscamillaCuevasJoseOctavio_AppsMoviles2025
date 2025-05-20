package com.example.actividad_m5_02;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Llamada al metodo procesar
        procesar();
    }

    //Metodo procesar, se encarga de procesar el archivo de entrada
    private void procesar() {

        //Declaracion de variables
        int caloriasMaximas = 0;
        int caloriasActuales = 0;

        //Lectura del archivo de entrada
        InputStream inputStream;
        {
            try {
                inputStream = getAssets().open("input_M5_02.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //Lectura del archivo de entrada
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        //Declaracion de variable
        String linea;

        //Lectura del archivo de entrada mediante un bucle
        while (true)
        {
            try {
                //Lectura de la linea, si la linea es vacia, se sale del bucle
                if ((linea = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //Si la linea es vacia, se suma la cantidad de calorias del elfo actual
            if(linea.trim().isEmpty())
            {
                //Si la cantidad de calorias del elfo actual es mayor a la maxima, se actualiza la maxima
                if (caloriasActuales > caloriasMaximas)
                {
                    caloriasMaximas = caloriasActuales;
                }
                caloriasActuales = 0;
            } else {
                //Si la linea no es vacia, se suma la cantidad de calorias
                caloriasActuales += Integer.parseInt(linea.trim());
            }
        }

        //Si la cantidad de calorias del elfo actual es mayor a la maxima, se actualiza la maxima
        if (caloriasActuales > caloriasMaximas)
        {
            caloriasMaximas = caloriasActuales;
        }

        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Impresion del resultado en LogCat
        Log.d("El elfo con la mayor cantidad de calorias es: ", caloriasMaximas + " calorias");
    }

}