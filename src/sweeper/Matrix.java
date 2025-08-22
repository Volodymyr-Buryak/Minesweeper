package sweeper;

import sweeper.EnumGame.Box;
import sweeper.util.game.Ranges;

public final class Matrix {
    private final Box[][] matrix;

    public Matrix(Box box){
        matrix = new Box[Ranges.getSize().x()][Ranges.getSize().y()];
        for (Coordinate allCoordinates : Ranges.getAllCoordinates()){
            matrix[allCoordinates.x()][allCoordinates.y()] = box;
        }
    }

    public Box get(Coordinate coordinate) {
        return matrix[coordinate.x()][coordinate.y()];
    }

    public void set(Coordinate coordinate, Box box){
        matrix[coordinate.x()][coordinate.y()] = box;
    }
}
