package main.java.sweeper;

public final class Game {
    private final Bomb bomb;
    private final Flag flag;

    public Game (int columns, int rows, int totalBombs) {
        // Встановлюємо розміри сітки гри
        Ranges.setSize(new Coordinate(columns, rows));
        bomb = new Bomb(totalBombs );
        flag = new Flag();
    }

    public void start() {
       bomb.start();
       flag.start();
    }

    public Box getBox (Coordinate coordinate) {
        return bomb.get(coordinate);
    }
}
