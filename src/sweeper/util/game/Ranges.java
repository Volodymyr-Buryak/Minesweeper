package sweeper.util.game;

import java.util.Random;
import sweeper.Coordinate;
import java.util.ArrayList;

public final class Ranges {
    private static Coordinate size;
    private static final Random random = new Random();
    private static final ArrayList<Coordinate> allCoordinates = new ArrayList<>();

    public static void setSize(Coordinate size) {
        Ranges.size = size;
        for (int x = 0; x < size.x(); x++) {
            for (int y = 0; y < size.y(); y++) {
                allCoordinates.add(new Coordinate(x,y));
            }
        }
    }

    public static Coordinate getSize() {
        return size;
    }

    public static ArrayList<Coordinate> getAllCoordinates() {
        return allCoordinates;
    }

    public static Coordinate generateCoordinate() {
        return new Coordinate(random.nextInt(size.x()), random.nextInt(size.y()));
    }

    private static boolean isRange(Coordinate coordinate) {
        return (coordinate.x() >= 0 && coordinate.x() < size.x()) &&
                (coordinate.y() >= 0 && coordinate.y() < size.y());
    }

    public static ArrayList<Coordinate> getCoordinatesAround (Coordinate coord){
        Coordinate coordinate;
        var list = new ArrayList<Coordinate>();

        for (int x = coord.x() - 1; x <= coord.x() + 1; x++){
            for (int y = coord.y() - 1; y <= coord.y() + 1 ; y++) {
                coordinate = new Coordinate(x, y);
                if (!coordinate.equals(coord) && Ranges.isRange(coordinate)){
                    list.add(coordinate);
                }
            }
        }
        return list;
    }
}
