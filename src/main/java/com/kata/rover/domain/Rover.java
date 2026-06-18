package com.kata.rover.domain;

public class Rover {

    private Direction direction;
    private Position position;

    public Rover(int x, int y, Direction d) {
        this.position = new Position(x, y);
        this.direction = d;
    }

    public void move() {
        position = position.translate(direction);
    }

    public void goLeft() {
        direction = direction.left();
    }

    public void goRight() {
        direction = direction.right();
    }

    @Override
    public String toString() {
        return position.x() + " " + position.y() + " " + direction.name();
    }

    public Position getPosition() {
        return position;
    }


}
