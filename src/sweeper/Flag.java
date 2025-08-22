package sweeper;

import sweeper.EnumGame.Box;
import sweeper.util.game.Ranges;

public final class Flag {
    private Matrix flagMap;
    private int totalClosed;
    private int totalFlagged;

    public void start() {
        flagMap = new Matrix(Box.CLOSED);
        totalFlagged = 0;
        totalClosed = Ranges.getSize().x() * Ranges.getSize().y();
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

    public void setBoxWrongFlag(Coordinate coordinate){
        if (Box.FLAGGED == flagMap.get(coordinate)){
            flagMap.set(coordinate, Box.NOBOMB);
        }
    }

    public void setOpenToBoxes(Coordinate coordinate) {
        flagMap.set(coordinate, Box.OPENED);
        totalClosed--;
    }

    public void setFlaggedToBoxes(Coordinate coordinate) {
        flagMap.set(coordinate, Box.FLAGGED);
        totalFlagged++;
    }

    public void setClosedToBoxes(Coordinate coordinate) {
        flagMap.set(coordinate, Box.CLOSED);
        totalFlagged--;
    }

    public void setLastedBombs() {
        for (Coordinate coordinate : Ranges.getAllCoordinates()) {
            if (Box.CLOSED == flagMap.get(coordinate)) {
                setFlaggedToBoxes(coordinate);
            }
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
