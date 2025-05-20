package mx.fca.aviones;

import java.util.ArrayList;

public class Planificador {

    public static Plano crearRutaInicial() {
        ArrayList<Avion> aviones = Aerolineas.AEROMEXICO();
        Plano planoInicial = new Plano(0, aviones, new ArrayList<>());
        Analizador.inicializa(planoInicial);
        return planoInicial;
    }
}
