package mx.fca.aviones;

import android.util.Log;

import java.util.ArrayList;

public class Plano {

    public ArrayList<Avion> aviones;
    public ArrayList<Colision> colisiones;
    public int col; // Ancho del grid (número de columnas)
    public int row; // Alto del grid (número de filas)
    public int noPaso;

    // Constructor
    Plano(int noPaso, ArrayList<Avion> aviones, ArrayList<Colision> colisiones) {
        this.noPaso = noPaso;
        this.aviones = aviones;
        this.colisiones = colisiones;

        // Calculamos el tamaño del grid a partir de la posición MÁXIMA de los aviones
        // Consideramos un tamaño mínimo si no hay aviones.
        int tmpMaxX = 0;
        int tmpMaxY = 0;
        for (Avion avion: aviones) {
            if (avion.x > tmpMaxX) {
                tmpMaxX = avion.x;
            }
            if (avion.y > tmpMaxY) {
                tmpMaxY = avion.y;
            }
        }
        // El tamaño del grid será el máximo índice + 1 (para incluir la celda 0)
        col = tmpMaxX + 1;
        row = tmpMaxY + 1;

        // Aseguramos un tamaño mínimo para el grid (ej. 5x5)
        // Puedes ajustar estos valores según tus necesidades.
        if (col < 5) col = 5;
        if (row < 5) row = 5;

        Log.i("Plano", "Paso: " + noPaso + ", Aviones: " + aviones.size() + ", Colisiones: " + colisiones.size());
        Log.i("Plano", "Grid W: " + col + ", Grid H: " + row);
    }

    public Plano next() {
        Log.i("Cristian", String.valueOf(noPaso));
        // Usamos noPaso + 1 para pedir el siguiente plano al Analizador
        return Analizador.next(noPaso + 1, this);
    }

    public Plano prev() {
        // Implementación de prev()
        if (noPaso > 0) { // Solo podemos retroceder si no estamos en el primer paso (0)
            return Analizador.prev(noPaso - 1);
        } else {
            return this; // Si ya estamos en el paso 0, devolvemos el plano actual
        }
    }

    public int getNumeroColisiones() {
        return colisiones.size(); // Usar .size() para ArrayList
    }

    public int getNumeroAviones() {
        return aviones.size(); // Usar .size() para ArrayList
    }
}