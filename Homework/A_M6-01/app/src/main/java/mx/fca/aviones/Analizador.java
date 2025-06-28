package mx.fca.aviones;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analizador {

    static HashMap<Integer, Plano> memoria = new HashMap<>();

    public static Plano inicializa(Plano plano) {
        memoria.put(0, plano);
        return plano;
    }

    public static Plano next(int noPaso, Plano plano){
        // Si el plano para este noPaso ya existe en memoria, lo devolvemos
        if (memoria.containsKey(noPaso)){
            return memoria.get(noPaso);
        } else {
            // El plano no existe, calculamos el siguiente estado
            ArrayList<Avion> nuevosAviones = new ArrayList<>();

            for (Avion avionActual: plano.aviones) {
                // Creamos una nueva instancia de Avion para el siguiente paso, con el mismo ID
                nuevosAviones.add(new Avion(avionActual.getId(), avionActual.direccion, avionActual.x, avionActual.y));
            }


            // Usamos el tamaño del grid actual del plano para la lógica de movimiento.
            int gridWidth = plano.col;
            int gridHeight = plano.row;

            for (Avion avion: nuevosAviones) {
                avion.mover(gridWidth, gridHeight); // Usamos el método mover del Avion
            }

            // Calcular colisiones para el nuevo estado
            ArrayList<Colision> colisiones = detectarColisiones(nuevosAviones);

            // Crear el nuevo Plano
            Plano planoNuevo = new Plano(noPaso, nuevosAviones, colisiones);
            memoria.put(noPaso, planoNuevo); // Guardar el nuevo plano en memoria
            return planoNuevo;
        }
    }

    // Metodo para detectar colisiones en una lista de aviones dada
    private static ArrayList<Colision> detectarColisiones(ArrayList<Avion> aviones) {
        ArrayList<Colision> colisionesDetectadas = new ArrayList<>();
        HashMap<String, List<Avion>> posicionesOcupadas = new HashMap<>();

        // Agrupar aviones por su posición
        for (Avion avion : aviones) {
            String posicionKey = avion.x + "_" + avion.y; // Clave única para la posición
            posicionesOcupadas.computeIfAbsent(posicionKey, k -> new ArrayList<>()).add(avion);
        }

        // Identificar posiciones con colisiones
        for (Map.Entry<String, List<Avion>> entry : posicionesOcupadas.entrySet()) {
            if (entry.getValue().size() > 1) { // Si hay más de un avión en la misma posición
                String[] coords = entry.getKey().split("_");
                int colX = Integer.parseInt(coords[0]);
                int colY = Integer.parseInt(coords[1]);
                colisionesDetectadas.add(new Colision(colX, colY)); // Crea una Colision
                // Opcional: Podrías querer almacenar qué aviones colisionaron en el objeto Colision
                // colisionesDetectadas.add(new Colision(colX, colY, entry.getValue()));
            }
        }
        return colisionesDetectadas;
    }

    public static Plano prev(int noPaso) {
        return memoria.get(noPaso);
    }
}