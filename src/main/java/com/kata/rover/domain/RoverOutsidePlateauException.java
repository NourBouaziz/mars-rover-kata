package com.kata.rover.domain;

public class RoverOutsideMapException extends RuntimeException {
    private final Rover rover;

    public RoverOutsideMapException(Rover rover) {
        this.rover = rover;
    }
}

