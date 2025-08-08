package sweeper;

import sweeper.EnumGame.Box;
import sweeper.util.game.Ranges;

public final class Matrix {
    private final Box[][] matrix;

    public Matrix(Box box){
        matrix = new Box[Ranges.getSize().getX()][Ranges.getSize().getY()];
        for (Coordinate allCoordinates : Ranges.getAllCoordinates()){
            matrix[allCoordinates.getX()][allCoordinates.getY()] = box;
        }
    }

    public Box get(Coordinate coordinate) {
        return matrix[coordinate.getX()][coordinate.getY()];
    }

    public void set(Coordinate coordinate, Box box){
        matrix[coordinate.getX()][coordinate.getY()] = box;
        printInfo();
    }

    public void printInfo(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println('\n');
    }
}
