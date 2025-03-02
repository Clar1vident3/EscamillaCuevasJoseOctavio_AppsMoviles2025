import java.io.*;
import java.util.Scanner;

class Main{

	public static void main(String [] args) {

		Scanner scanner = new Scanner(System.in);

		//scanner de las variables
		System.out.println("Introduce el primer numero");
		double num1 = scanner.nextDouble();
		System.out.println("Introduce el segundo numero");
		double num2 = scanner.nextDouble();
		
		//Scanner de la operacion a realizar
		System.out.println("Introduce la operacion a realizar SUMA, RESTA, MULT, DIV");
		String strOperacion = scanner.next().toUpperCase();
		Operando operacion = Operando.valueOf(strOperacion);

		//Crear la operacion
		Operacion op = new Operacion(num1, num2, operacion);

		//Crear la calculadora y calcular el resultado
		ICalculadora calculadora = new CalculadoraImpl();
		Double resultado = calculadora.calcular(op);

		//Mostrar el resultado
		System.out.println("El resultado de la operacion es: " + resultado);
		
		//Cerrar el scanner
		scanner.close();
	}
}
