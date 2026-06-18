package com.kata.rover.domain;


public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position translate(Direction direction) {
        this.x = x + direction.getDx();
        this.y = y + direction.getDy();
        return this;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

}
