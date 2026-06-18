package com.kata.rover.domain;

public class Plateau {
    private final int maxX;
    private final int maxY;

    public static final int MIN_Y = 0;
    public static final int MIN_X = 0;

    public Plateau(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public boolean doesNotContain(Position position) {
        return position.x() < MIN_X
                || position.y() < MIN_Y
                || position.x() > maxX
                || position.y() > maxY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plateau plateau = (Plateau) o;
        return maxX == plateau.maxX && maxY == plateau.maxY;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(maxX, maxY);
    }

}
