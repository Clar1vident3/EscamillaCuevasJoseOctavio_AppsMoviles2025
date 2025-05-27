package com.example.a05032025;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

public class CalculadoraUI implements ICalculadoraUI {
    CalculadoraOnResult calculadoraResult = null;
    ICalculadora logica;
    ICalcudoraMemoria memoria = new CalculadoraMemoria();
    Context context;
    TextView txvDisplay;
    Button btnSuma;
    Button btnResta;
    Button btnMultiplicacion;
    Button btnDivision;
    Button btnPorcentaje;
    Button btnMasMenos;

    Button btnPunto;
    Button btnIgual;
    Button btnClear;

    Button btnUno;

    Button btnDos;

    Button btnTres;

    Button btnCuatro;

    Button btnCinco;

    Button btnSeis;

    Button btnSiete;

    Button btnOcho;

    Button btnNueve;

    Button btnZero;

    public CalculadoraUI(Activity activity, ICalculadora logica) {
        this.logica = logica;
        context = activity.getApplicationContext();
        txvDisplay = activity.findViewById(R.id.salida_textView);
        btnZero = activity.findViewById(R.id.cero_button);
        btnUno = activity.findViewById(R.id.uno_button);
        btnDos = activity.findViewById(R.id.dos_button);
        btnTres = activity.findViewById(R.id.tres_button);
        btnCuatro = activity.findViewById(R.id.cuatro_button);
        btnCinco = activity.findViewById(R.id.cinco_button);
        btnSeis = activity.findViewById(R.id.seis_button);
        btnSiete = activity.findViewById(R.id.siete_button);
        btnOcho = activity.findViewById(R.id.ocho_button);
        btnNueve = activity.findViewById(R.id.nueve_button);
        btnIgual = activity.findViewById(R.id.igual_button);
        btnSuma = activity.findViewById(R.id.mas_button);
        btnResta = activity.findViewById(R.id.menos_button);
        btnMultiplicacion = activity.findViewById(R.id.por_button);
        btnDivision = activity.findViewById(R.id.entre_button);
        btnMasMenos = activity.findViewById(R.id.masmenos_button);
        btnPorcentaje = activity.findViewById(R.id.porciento_button);
        btnPunto = activity.findViewById(R.id.punto_button);
        btnClear = activity.findViewById(R.id.ac_button);

        btnZero.setOnClickListener(v -> {
            addNumber("0");
        });
        btnUno.setOnClickListener(v -> {
            addNumber("1");
        });
        btnDos.setOnClickListener(v -> {
            addNumber("2");
        });
        btnTres.setOnClickListener(v -> {
            addNumber("3");
        });
        btnCuatro.setOnClickListener(v -> {
            addNumber("4");
        });
        btnCinco.setOnClickListener(v -> {
            addNumber("5");
        });
        btnSeis.setOnClickListener(v -> {
            addNumber("6");
        });
        btnSiete.setOnClickListener(v -> {
            addNumber("7");
        });
        btnOcho.setOnClickListener(v -> {
            addNumber("8");
        });
        btnNueve.setOnClickListener(v -> {
            addNumber("9");
        });
        btnClear.setOnClickListener(v -> {
            clearScreen();
        });
        btnMasMenos.setOnClickListener(v -> {
        });
        btnIgual.setOnClickListener(v -> {
            if (calculadoraResult != null) {
                memoria.igual();
                calculadoraResult.onResult(
                        memoria.getX(),
                        memoria.getY(),
                        memoria.getOperacion()
                );
            }
        });
        btnResta.setOnClickListener(v -> {
            addOperation(Operacion.RESTA);
        });
        btnMultiplicacion.setOnClickListener(v -> {
            addOperation(Operacion.MULT);
        });
        btnDivision.setOnClickListener(v -> {
            addOperation(Operacion.DIV);
        });
        btnPorcentaje.setOnClickListener(v -> {
            addOperation(Operacion.PORC);
        });
        btnPunto.setOnClickListener(v -> {

        });

        setOnResult((x, y, operacion) -> {
            BigDecimal result = logica.calculate(operacion, x, y);
            txvDisplay.setText(String.valueOf(result));
        });
    }

    @Override
    public void clearScreen() {
        txvDisplay.setText("");
        memoria.clear();
    }

    @Override
    public void showResult(String result) {
        txvDisplay.setText(result);
    }

    @Override
    public String addNumber(String numero) {
        String newValue = memoria.concat(numero);
        txvDisplay.setText(newValue);
        return newValue;
    }

    @Override
    public void addOperation(Operacion operacion) {
        txvDisplay.setText(Operacion.convert(operacion));
        memoria.concat(operacion);
    }

    public void setOnResult(CalculadoraOnResult result) {
        this.calculadoraResult = result;
    }
}
