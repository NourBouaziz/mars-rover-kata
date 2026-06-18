package com.kata.rover.domain;

public class Plateau {
    private final int maxX;
    private final int maxY;

    private final int minY = 0;
    private final int minX = 0;

    public Plateau(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public boolean contains(Position position) {
        return position.x() >= minX
                && position.y() >= minY
                && position.x() <= maxX
                && position.y() <= maxY;
    }

}
