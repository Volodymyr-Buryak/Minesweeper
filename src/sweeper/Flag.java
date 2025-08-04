package sweeper;

import sweeper.EnumGame.Box;

public final class Flag {
    private Matrix flagMap;

    public Box get(Coordinate coordinate){
        return flagMap.get(coordinate);
    }
    public void start() {
        flagMap = new Matrix(Box.CLOSED);
    }

    public void setOpendToCells(Coordinate coordinate) {
        flagMap.set(coordinate, Box.OPENED);
    }

    private void setFlaggedToCells(Coordinate coordinate) {
        flagMap.set(coordinate, Box.FLAGGED);
    }

    private void setClosedToCells(Coordinate coordinate) {
        flagMap.set(coordinate, Box.CLOSED);
    }

    public void toggleFlaggedToBox (Coordinate coordinate) {
        switch (flagMap.get(coordinate)){
            case FLAGGED -> setClosedToCells(coordinate);
            case CLOSED -> setFlaggedToCells(coordinate);
            default -> System.out.println("You can not toggle flag on this box: " + coordinate);
        }
    }
}
