package com.kata.rover.domain;

public enum Direction {

    N(0,1),
    E(1,0),
    S(0,-1),
    W(-1,0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Direction left() {
        return values()[(ordinal() + 3) % 4];
    }

    public Direction right() {
        return values()[(ordinal() + 1) % 4];
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
