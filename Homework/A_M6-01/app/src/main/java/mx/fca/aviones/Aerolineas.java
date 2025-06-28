package mx.fca.aviones;

import java.util.ArrayList;

public class Aerolineas {

    public static ArrayList<Avion> AEROMEXICO(){
        ArrayList<Avion> aviones = new ArrayList<>();
        // Avión 1: Se mueve en la fila 0
        aviones.add(new Avion("AM001", Direccion.EAST, 0, 0));

        // Avión 2: Se mueve en la fila 0, choca con AM001
        aviones.add(new Avion("AM002", Direccion.WEST, 2, 0));

        // --- Nuevos aviones para probar movimientos en otras filas o colisiones diferentes ---

        // Avión 3: Se mueve en la fila 1, de norte a sur
        aviones.add(new Avion("AM003", Direccion.SOUTH, 0, 1)); // Empieza en (0,1)

        // Avión 4: Se mueve en la fila 3, de este a oeste
        aviones.add(new Avion("AM004", Direccion.WEST, 4, 3)); // Empieza en (4,3)

        // Avión 5: Otro avión para intentar colisión en una fila diferente
        // Empieza en (2,2) y se mueve NORTH. Chocará con AM006 en (2,1)
        aviones.add(new Avion("AM005", Direccion.NORTH, 2, 2));

        // Avión 6: Empieza en (2,0) y se mueve SOUTH. Chocará con AM005 en (2,1)
        aviones.add(new Avion("AM006", Direccion.SOUTH, 2, 0));


        return aviones;
    }
}