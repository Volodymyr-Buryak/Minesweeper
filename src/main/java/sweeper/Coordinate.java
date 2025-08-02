package main.java.sweeper;

public class Coordinate{
    private int x;
    private int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Coordinate)){
            return super.equals(obj);
        }
        return ((Coordinate) obj).x == x &&  ((Coordinate) obj).y == y;
    }
}
