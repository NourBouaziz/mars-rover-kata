package com.kata.rover.domain;

import java.util.Arrays;

public enum Direction {

    N(0,1, 0),
    E(1,0, 1),
    S(0,-1, 2),
    W(-1,0, 3);

    private final int dx;
    private final int dy;
    private final int id;

    Direction(int dx, int dy, int id) {
        this.dx = dx;
        this.dy = dy;
        this.id = id;
    }

    public static Direction findById(int id) {
        return Arrays.stream(values()).filter(
                x -> x.id == id)
                .findFirst().orElseThrow(
                        () -> new IllegalArgumentException("Unknown direction id: " + id));

    }

    public Direction left() {
        return findById((id + 3) % 4);
    }

    public Direction right() {
        return findById((id + 1) % 4);
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
