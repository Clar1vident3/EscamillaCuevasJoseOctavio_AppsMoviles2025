package mx.fca.aviones;

public class Colision {

    int x;
    int y;

    // Constructor para la clase Colision
    public Colision(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { // Getter para x
        return x;
    }

    public int getY() { // Getter para y
        return y;
    }

    public int getImage() {
        return R.mipmap.collision;
    }
}