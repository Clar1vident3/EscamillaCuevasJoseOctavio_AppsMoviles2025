package com.example.actividad_m3_01;

public class Calculadora implements iCalculadora
{

    public int sumar(int n1, int n2)
    {
        return n1 + n2;
    }
    public int restar(int n1, int n2)
    {
        return n1 - n2;
    }
    public int multiplicar(int n1, int n2)
        {
        return n1 * n2;
    }
    //Es un metodo double ya que, a diferencia de int, sí acepta el manejo de NaN
    public double dividir(double n1, double n2)
    {
        if (n2 == 0)
        {
            return Double.NaN;
        }else{
            return n1 / n2;
        }
    }
}
