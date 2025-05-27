package com.example.a05032025;

public interface ICalculadoraUI {

    void clearScreen();
    void showResult(String result);

    String addNumber(String numero);

    void addOperation(Operacion operacion);
}
