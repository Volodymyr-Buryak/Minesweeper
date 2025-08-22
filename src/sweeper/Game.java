package sweeper;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Dimension;

import sweeper.EnumGame.*;
import sweeper.util.game.Ranges;

public final class Game {
    private final Bomb bomb;
    private final Flag flag;
    private GameState gameState;

    public Game (int columns, int rows, int totalBombs) {
        Ranges.setSize(new Coordinate(columns, rows));
        flag = new Flag();
        bomb = new Bomb(totalBombs);
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
            openBox(coordinate);
            checkWinner();
        }
    }

    public void pressRightButton (Coordinate coordinate) {
        if (isGamePlaying()){
            switch (flag.get(coordinate)) {
                case FLAGGED -> flag.setClosedToBoxes(coordinate);
                case CLOSED -> {
                    if (getRemainingFlags() != 0){
                        flag.setFlaggedToBoxes(coordinate);
                    }
                }
            }
        }
    }

    private boolean isGamePlaying() {
        return gameState == GameState.PLAYING;
    }

    private void openBox (Coordinate coordinate) {
        switch (flag.get(coordinate)){
            case OPENED -> openAdjacentBoxes(coordinate);
            case CLOSED -> {
                switch (bomb.get(coordinate)){
                    case ZERO -> openBoxesAroundZero(coordinate);
                    case BOMB -> processBombHit(coordinate);
                    default -> flag.setOpenToBoxes(coordinate);
                }
            }
        }
    }

    private void openAdjacentBoxes(Coordinate coordinate) {
        int numberBox = bomb.get(coordinate).getNumberBox();
        if (numberBox != -1 && numberBox== flag.getFlagCountAround(coordinate)){
            for (Coordinate around: Ranges.getCoordinatesAround(coordinate)){
                if (Box.CLOSED == flag.get(around)){
                    openBox(around);
                }
            }
        }
    }

    private void processBombHit(Coordinate coordinate) {
        gameState = GameState.BOMBED;
        flag.setOpenToBoxes(coordinate);
        bomb.setCellExplodedBomb(coordinate);
        displayBombs();
    }

    private void displayBombs() {
        for (Coordinate coordinate : Ranges.getAllCoordinates()){
            if (Box.BOMB == bomb.get(coordinate)) {
                if (Box.FLAGGED != flag.get(coordinate)){
                    flag.setOpenToBoxes(coordinate);
                    flag.setTotalClosed(flag.getTotalClosed() + 1);
                }
            } else {
                flag.setBoxWrongFlag(coordinate);
            }
        }
    }

    private void openBoxesAroundZero(Coordinate coordinate) {
        flag.setOpenToBoxes(coordinate);
        for (Coordinate around: Ranges.getCoordinatesAround(coordinate)){
            openBox(around);
        }
    }

    private void checkWinner() {
        if (GameState.PLAYING == gameState && flag.getTotalClosed() == bomb.getTotalBombs()){
            gameState = GameState.WINNER;
            flag.setLastedBombs();
        }
    }

    public void updateButtonIconOnGame(JButton button) {
        ImageIcon icon = (ImageIcon) switch (gameState){
            case WINNER -> GameState.WINNER.getImage();
            case BOMBED -> GameState.BOMBED.getImage();
            default -> GameState.PLAYING.getImage();
        };
        button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        button.setIcon(icon);
    }

    public int getRemainingFlags(){
        return bomb.getTotalBombs() - flag.getTotalFlagged();
    }

    public void stopTimer(TimerSweeper timerSweeper) {
        if (gameState != GameState.PLAYING) {
            timerSweeper.stop();
        }
    }

    public void restartTimer(TimerSweeper timerSweeper) {
        if (gameState == GameState.PLAYING) {
            timerSweeper.restart();
        }
    }
}
