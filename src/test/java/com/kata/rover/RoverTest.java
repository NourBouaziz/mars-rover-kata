package com.kata.rover;

import com.kata.rover.domain.Direction;
import com.kata.rover.domain.Plateau;
import com.kata.rover.domain.Rover;
import com.kata.rover.domain.RoverOutsidePlateauException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoverTest {

    @Test
    void shouldMoveNorth() {
        Rover r = new Rover(0, 0, Direction.N);
        r.move();
        assertEquals("0 1 N", r.toString());
    }

    @Test
    void shouldMoveSouth() {
        Rover r = new Rover(0, 1, Direction.S);
        r.move();
        assertEquals("0 0 S", r.toString());
    }

    @Test
    void shouldRotateLeftFromNorth() {
        Rover r = new Rover(0, 1, Direction.N);
        r.goLeft();
        assertEquals("0 1 W", r.toString());
    }

    @Test
    void shouldRotateRightFromNorth() {
        Rover r = new Rover(0, 1, Direction.N);
        r.goRight();
        assertEquals("0 1 E", r.toString());
    }

    @Test
    void shouldExecuteSeveralCommands() {
        Plateau mars = new Plateau(5,5);
        MissionControl mc = new MissionControl(mars);
        Rover r = new Rover(1, 2, Direction.N);
        mc.executeCommands(r, "LMLMLMLMM".toCharArray());
        assertEquals("1 3 N", r.toString());
    }

    @Test
    void shouldThrowExceptionWhenOutsidePlateau() {
        Plateau mars = new Plateau(1,1);
        MissionControl mc = new MissionControl(mars);
        Rover r = new Rover(1, 2, Direction.N);
        assertThrows(RoverOutsidePlateauException.class, () -> mc.executeCommands(r, "LM".toCharArray()));
    }

    @Test
    void shouldMoveAccordingToSteps() {
        Rover r = new Rover(1, 2, Direction.S);
        r.goLeft(); //1, 2, E
        r.goLeft(); //1, 2, N
        r.move(); //1, 3, N
        r.move();//1, 4, N
        r.goRight(); //1, 4, E

        assertEquals("1 4 E", r.toString());
    }

    @Test
    void shouldDisplayWarningWhenInvalidAttemptedMove() {
        Plateau mars = new Plateau(1,1);
        MissionControl mc = new MissionControl(mars);
        Rover r = new Rover(1, 1, Direction.E);
        assertThrows(RoverOutsidePlateauException.class, () -> mc.executeCommands(r, new char[]{'M'}));
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        Plateau mars = new Plateau(1,1);
        MissionControl mc = new MissionControl(mars);
        Rover r = new Rover(1, 1, Direction.E);

        assertThrows(IllegalArgumentException.class, () -> mc.executeCommands(r, new char[]{'X'}));
    }



}
