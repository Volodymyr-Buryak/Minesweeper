package sweeper;

import sweeper.EnumGame.Box;
import sweeper.util.game.Ranges;
import sweeper.EnumGame.GameState;

public final class Game {
    private final Bomb bomb;
    private final Flag flag;
    private GameState gameState;

    public Game (int columns, int rows, int totalBombs) {
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

    private boolean isGamePlaying() {
        return gameState == GameState.PLAYING;
    }

    private void openCells (Coordinate coordinate) {
        switch (flag.get(coordinate)){
            case OPENED -> {}
            case FLAGGED -> {}
            case CLOSED -> {
                switch (bomb.get(coordinate)){
                    case ZERO -> openCellsAroundZero(coordinate);
                    case BOMB -> processBombHit(coordinate);
                    default -> flag.setOpendToCells(coordinate);
                }
            }
        }
    }

    private void processBombHit(Coordinate coordinate) {
        gameState = GameState.BOMBED;
        flag.setOpendToCells(coordinate);
        bomb.setCellExplodedBomb(coordinate);
        displayBombs();
    }

    private void displayBombs() {
        for (Coordinate coordinate : Ranges.getAllCoordinates()){
            if (Box.BOMB == bomb.get(coordinate) && Box.CLOSED == flag.get(coordinate)) {
                flag.setOpendToCells(coordinate);
                flag.setTotalClosed(flag.getTotalClosed() + 1);
            } else {
                flag.setCellMisflagged(coordinate);
            }
        }
    }

    private void openCellsAroundZero(Coordinate coordinate) {
        flag.setOpendToCells(coordinate);
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
