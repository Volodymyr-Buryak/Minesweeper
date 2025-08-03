package main.java.sweeper;

public final class Flag {
    private Matrix flagMap;

    public void start(){
        flagMap = new Matrix(Box.CLOSED);
    }

    public Box get(Coordinate coordinate){
        return flagMap.get(coordinate);
    }
}
