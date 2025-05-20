package mx.fca.aviones;

import java.util.ArrayList;
import java.util.HashMap;

public class Analizador {

    static HashMap<Integer, Plano> memoria = new HashMap<>();

    public static Plano inicializa(Plano plano) {
        memoria.put(0, plano);
        return plano;
    }

    public static Plano next(int noPaso, Plano plano){
        // Implementa usted
        if (memoria.containsKey(noPaso)){
            return memoria.get(noPaso);
        } else {
            Plano planoNuevo;
            ArrayList<Avion> nuevosAviones = new ArrayList<>();

            // Calcular colision
            /// Aqui
            ArrayList<Colision> colisiones = new ArrayList<>();

            // Cuantas colisiones hay en este plano ?

            ///// AQUIIIIII SE HACE EL CALCULO DE LAS COLISION


            // Esta calculando la sig. iteracion
            // (0,0,>), (2,0,<) NO PASO = 0
            // NO PASO = 1 + 1 COLISION EN DONDE? ((1,0,>), (1,0,<) )

            for (Avion avion: plano.aviones) {
                // Calcular movimiento
                switch (avion.direccion){
                    case NORTH:
                        avion.y = avion.y - 1;
                        break;
                    case SOUTH:
                        avion.y = avion.y + 1;
                        break;
                    case EAST:
                        avion.x = avion.x + 1;
                        break;
                    case WEST:
                        avion.x = avion.x - 1;
                        break;
                }
                nuevosAviones.add(avion);
            }
            planoNuevo = new Plano(noPaso, nuevosAviones, colisiones);
            memoria.put(noPaso, planoNuevo);
            return planoNuevo;
        }
    }

    public static Plano prev(int noPaso) {
        return memoria.get(noPaso);
    }
}
