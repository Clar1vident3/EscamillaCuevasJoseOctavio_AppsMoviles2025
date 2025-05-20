package com.example.actividad_m5_01;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Declaración de variables y de los elementos de la interfaz de usuario
    Button btnAvanzar;
    Button btnRetroceder;
    TextView txvMiTexto;
    // Contador para la serie de Fibonacci
    int contador = 1;
    // Mapa para almacenar los valores de la serie de Fibonacci
    Map<Integer, Long> memo = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización de los elementos de la interfaz de usuario (del layout)
        txvMiTexto = findViewById(R.id.txv_main);
        btnAvanzar = findViewById(R.id.btn_reinicio);
        btnRetroceder = findViewById(R.id.btnRetroceder);

        // Establecer el texto del TextView inicial
        txvMiTexto.setText("Serie de Fibonacci");

        //Comportamiento del boton de avance: siguiente numero de la serie de Fibonacci
        btnAvanzar.setOnClickListener(v -> {
            long valor = fibonacci(contador);
            txvMiTexto.setText(String.valueOf(valor));
            contador++;
        });

        //Comportamiento del boton de retroceso: numero anterior de la serie de Fibonacci
        btnRetroceder.setOnClickListener(v -> {
            if (contador > 1) {
                contador--;
                long valor = fibonacci(contador);
                txvMiTexto.setText(String.valueOf(valor));
            }
        });
    }

    /*
     * Metodo para calcular el numero de la serie de Fibonacci
     * @param n numero a calcular
     * @return numero de la serie de Fibonacci
     */
    public long fibonacci(int n) {
        if (n <= 1) return n;

        // Si el valor ya está en el mapa, lo retornamos
        if (memo.containsKey(n)) return memo.get(n);

        //Calculo recursivo y almacenamiento del resultado
        long result = fibonacci(n - 1) + fibonacci(n - 2);
        memo.put(n, result);
        return result;
    }
}
