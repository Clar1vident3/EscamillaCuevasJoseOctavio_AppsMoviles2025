package mx.fca.aviones;

public class Avion {
    Direccion direccion;
    int x;
    int y;

    public Avion(Direccion direccion, int x, int y) {
        this.direccion = direccion;
        this.x = x;
        this.y = y;
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
