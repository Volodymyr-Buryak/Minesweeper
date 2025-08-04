package sweeper;

import sweeper.EnumGame.Box;
import sweeper.util.game.Ranges;

public final class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    public Bomb(int totalBombs) {
        int maxBomb = Ranges.getSize().getX() * Ranges.getSize().getY();
        if (totalBombs > maxBomb){
            this.totalBombs =  maxBomb / 2;
            return;
        }
        this.totalBombs = totalBombs;
    }

    public Box get(Coordinate coordinate) {
        return bombMap.get(coordinate);
    }

    public void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBombs; i++) {
            generateBombs();
        }
    }

    private void generateBombs() {
        Coordinate coordinate = Ranges.generateCoordinate();
        while (Box.BOMB == bombMap.get(coordinate)){
            coordinate = Ranges.generateCoordinate();
        }
        bombMap.set(coordinate, Box.BOMB);
        setNumbersAroundBomb(coordinate);
    }

    private void setNumbersAroundBomb(Coordinate coordinate){
        for (Coordinate around : Ranges.getCoordinatesAround(coordinate)){
            if (Box.BOMB != bombMap.get(around)) {
                bombMap.set(around, bombMap.get(around).next());
            }
        }
    }
}
