package main.java.sweeper;

import java.util.ArrayList;

public class Ranges {
    private static Coordinate size; // Розміри сітки гри
    private static final ArrayList<Coordinate> allCoordinates = new ArrayList<Coordinate>();

    public static Coordinate getSize() {
        return size;
    }

    public static void setSize (int columns, int rows){
        size = new Coordinate(columns, rows);
        setSize();
    }

    public static void setSize() {
        for (int x = 0; x < size.getX(); x++) {
            for (int y = 0; y < size.getY(); y++) {
                allCoordinates.add(new Coordinate(x,y));
            }
        }
    }

    public static ArrayList<Coordinate> getAllCoordinates(){
        return allCoordinates;
    }
}
