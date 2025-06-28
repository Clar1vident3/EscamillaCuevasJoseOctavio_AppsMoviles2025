package mx.fca.aviones;

public class Avion {
    String id; // Añadimos un ID para identificar el avión
    Direccion direccion;
    int x;
    int y;

    public Avion(String id, Direccion direccion, int x, int y) { // Constructor modificado
        this.id = id;
        this.direccion = direccion;
        this.x = x;
        this.y = y;
    }

    public Avion(Direccion direccion, int x, int y) {
        this("A" + System.currentTimeMillis(), direccion, x, y); // Genera un ID por defecto
    }

    public String getId() { // Getter para el ID
        return id;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Nuevo: Metodo para mover el avión (el Analizador lo llamará)
    public void mover(int gridWidth, int gridHeight) {
        switch (direccion){
            case NORTH:
                y = y - 1;
                break;
            case SOUTH:
                y = y + 1;
                break;
            case EAST:
                x = x + 1;
                break;
            case WEST:
                x = x - 1;
                break;
        }

        if (x < 0) x = gridWidth - 1;
        if (x >= gridWidth) x = 0;
        if (y < 0) y = gridHeight - 1;
        if (y >= gridHeight) y = 0;
    }

    public int getImage() {
        switch (direccion){
            case NORTH:
                return R.mipmap.north;
            case SOUTH:
                return R.mipmap.south;
            case EAST:
                return R.mipmap.east;
            case WEST:
                return R.mipmap.west;
        }
        return R.mipmap.north;
    }
}