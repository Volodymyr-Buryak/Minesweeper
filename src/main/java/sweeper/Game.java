package main.java.sweeper;

public class Game {
    private final Bomb bomb;

    public Game (int columns, int rows, int totalbomb) {
        // Встановлюємо розміри сітки гри
        Ranges.setSize(new Coordinate(columns, rows));
        bomb = new Bomb(totalbomb);
    }

    public void start() {
       bomb.start();
    }

    public Box getBox (Coordinate coordinate) {
        return bomb.get(coordinate);
    }
}
