package com.kata.rover.domain;

public class RoverOutsidePlateauException extends RuntimeException {

    public RoverOutsidePlateauException(Rover rover) {
        super("Rover " + rover + " is outside plateau");
    }
}

