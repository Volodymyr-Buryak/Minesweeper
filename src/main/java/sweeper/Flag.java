package main.java.sweeper;

public final class Flag {
    private Matrix flagMap;

    public void start(){
        flagMap = new Matrix(Box.CLOSED);
    }

    public Box get(Coordinate coordinate){
        return flagMap.get(coordinate);
    }

    public void setOpendToBox(Coordinate coordinate) {
        flagMap.set(coordinate, Box.OPENED);
    }

    private void setFlaggedToBox(Coordinate coordinate) {
        flagMap.set(coordinate, Box.FLAGGED);
    }

    private void setClosedToBox(Coordinate coordinate) {
        flagMap.set(coordinate, Box.CLOSED);
    }

    public void toggleFlaggedToBox (Coordinate coordinate) {
        switch (flagMap.get(coordinate)){
            case FLAGGED -> setClosedToBox(coordinate);
            case CLOSED -> setFlaggedToBox(coordinate);
            default -> System.out.println("You can not toggle flag on this box: " + coordinate);
        }
    }
}
