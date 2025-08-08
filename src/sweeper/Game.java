package sweeper;

import sweeper.EnumGame.Box;
import sweeper.util.game.Ranges;
import sweeper.EnumGame.GameState;

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
        if (isGamePlaying()) {
            openCells(coordinate);
            checkWinner();
        }
    }

    public void pressRightButton (Coordinate coordinate) {
        if (isGamePlaying()){
            flag.toggleFlaggedToBox(coordinate);
        }
    }

    private void openCells (Coordinate coordinate) {
        switch (flag.get(coordinate)){
            case OPENED -> {}
            case FLAGGED -> {}
            case CLOSED -> {
                switch (bomb.get(coordinate)){
                    case ZERO -> openCellsAroundZero(coordinate);
                    case BOMB ->  {
                        gameState = GameState.BOMBED;
                        flag.setOpendToCells(coordinate);
                        bomb.setCellExplodedBomb(coordinate);
                    }
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

    private void checkWinner() {
        if (GameState.PLAYING == gameState && flag.getTotalClosed() == bomb.getTotalBombs()){
            gameState = GameState.WINNER;
            flag.setLastedBombs();
        }
    }

    private boolean isGamePlaying() {
        return gameState == GameState.PLAYING;
    }

    public String getMassage() {
        System.out.println("Game state: " + gameState);
        return switch (gameState) {
            case BOMBED -> "You lost!";
            case WINNER -> "You won!";
            case PLAYING -> {
                if (flag.getTotalFlagged() == 0){
                    yield "Click to start";
                } else {
                    yield "Flagged: " + flag.getTotalFlagged() + " of " + bomb.getTotalBombs();
                }
            }
        };
    }
}
