package main.java.sweeper;

import java.util.ArrayList;
import java.util.Random;

public final class Ranges {
    private static Coordinate size; // Розміри сітки гри
    private static final Random random = new Random();
    private static final ArrayList<Coordinate> allCoordinates = new ArrayList<>();

    public static void setSize(Coordinate size) {
        Ranges.size = size;
        for (int x = 0; x < size.getX(); x++) {
            for (int y = 0; y < size.getY(); y++) {
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
        return new Coordinate(random.nextInt(size.getX()), random.nextInt(size.getY()));
    }

    private static boolean isRange(Coordinate coordinate) {
        return (coordinate.getX() >= 0 && coordinate.getX() < size.getX()) &&
                (coordinate.getY() >= 0 && coordinate.getY() < size.getY());
    }

    public static ArrayList<Coordinate> getCoordinatesAround (Coordinate coord){
        Coordinate coordinate;
        var list = new ArrayList<Coordinate>();

        for (int x = coord.getX() - 1; x <= coord.getX() + 1; x++){
            for (int y = coord.getY() - 1; y <= coord.getY() + 1 ; y++) {
                coordinate = new Coordinate(x, y);
                if (!coordinate.equals(coord) && Ranges.isRange(coordinate)){
                    list.add(coordinate);
                }
            }
        }

        return list;
    }
}
