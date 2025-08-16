package sweeper;

import sweeper.EnumGame.Box;
import sweeper.util.game.Ranges;

public final class Flag {
    private Matrix flagMap;
    private int totalFlagged;
    private int totalClosed;

    public void start() {
        flagMap = new Matrix(Box.CLOSED);
        totalFlagged = 0;
        totalClosed = Ranges.getSize().getX() * Ranges.getSize().getY();
    }

    public Box get(Coordinate coordinate){
        return flagMap.get(coordinate);
    }

    public int getTotalFlagged() {
        return totalFlagged;
    }

    public int getTotalClosed() {
        return totalClosed;
    }

    public void setTotalClosed(int totalClosed) {
        this.totalClosed = totalClosed;
    }

    public void setCellMisflagged(Coordinate coordinate){
        if (Box.FLAGGED == flagMap.get(coordinate)){
            flagMap.set(coordinate,Box.NOBOMB);
        }
    }

    public void setOpendToCells(Coordinate coordinate) {
        flagMap.set(coordinate, Box.OPENED);
        totalClosed--;
    }

    private void setFlaggedToCells(Coordinate coordinate) {
        flagMap.set(coordinate, Box.FLAGGED);
        totalFlagged++;
    }

    private void setClosedToCells(Coordinate coordinate) {
        flagMap.set(coordinate, Box.CLOSED);
        totalFlagged--;
    }

    public void setLastedBombs() {
        for (Coordinate coordinate : Ranges.getAllCoordinates()) {
            if (Box.CLOSED == flagMap.get(coordinate)) {
                setFlaggedToCells(coordinate);
            }
        }
    }

    public void toggleFlaggedToBox (Coordinate coordinate) {
        switch (flagMap.get(coordinate)){
            case FLAGGED -> setClosedToCells(coordinate);
            case CLOSED -> setFlaggedToCells(coordinate);
            default -> System.out.println("You can not toggle flag on this box: " + coordinate);
        }
    }

    public int getFlagCountAround(Coordinate coordinate) {
        int count = 0;
        for (Coordinate around : Ranges.getCoordinatesAround(coordinate)){
            if (flagMap.get(around) == Box.FLAGGED){
                count++;
            }
        }
        return count;
    }
}
