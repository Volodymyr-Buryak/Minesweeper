package main.java.sweeper;

public class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    public Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
    }

    public void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBombs; i++) {
            generateBombs();
        }
        bombMap.printInfo();
    }

    public Box get(Coordinate coordinate) {
        return bombMap.get(coordinate);
    }

    private void generateBombs() {
        Coordinate coordinate = Ranges.generateCoordinate();
        bombMap.set(coordinate, Box.BOMB);
        for (Coordinate around : Ranges.getCoordinatesAround(coordinate)){
            bombMap.set(around, Box.NUM1);
        }
    }

}
