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

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}
