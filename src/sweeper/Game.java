package sweeper;

import sweeper.EnumGame.Box;
import sweeper.EnumGame.GameState;
import sweeper.util.game.Ranges;

public final class Game {
    private final Bomb bomb;
    private final Flag flag;
    private GameState gameState;

    public Game (int columns, int rows, int totalBombs) {
        // Встановлюємо розміри сітки гри
        Ranges.setSize(new Coordinate(columns, rows));
        bomb = new Bomb(totalBombs);
        flag = new Flag();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void start() {
       gameState = GameState.PLAYING;
       bomb.start();
       flag.start();
    }

    public Box getBox (Coordinate coordinate) {
        Box box = flag.get(coordinate);
        if (Box.OPENED == box){
            return bomb.get(coordinate);
        }
        return box;
    }

    public void pressLeftButton (Coordinate coordinate) {
        openCells(coordinate);
    }

    private void openCells (Coordinate coordinate) {
        switch (flag.get(coordinate)){
            case OPENED -> {}
            case FLAGGED -> {}
            case CLOSED -> {
                switch (bomb.get(coordinate)){
                    case ZERO -> openCellsAroundZero(coordinate);
                    case BOMB -> flag.setOpendToCells(coordinate);
                    default -> flag.setOpendToCells(coordinate);
                }
            }
        }
    }

    private void openCellsAroundZero(Coordinate coordinate) {
        flag.setOpendToCells(coordinate);
        System.out.println(coordinate);
        for (Coordinate around: Ranges.getCoordinatesAround(coordinate)){
            openCells(around);
        }
    }

    public void pressRightButton (Coordinate coordinate) {
        flag.toggleFlaggedToBox(coordinate);
    }

}
