package sweeper;

public record Coordinate(int x, int y) {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinate(int x1, int y1)) {
            return x1 == x && y1 == y;
        }
        return false;
    }
}
