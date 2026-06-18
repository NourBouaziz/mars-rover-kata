package com.kata.rover;

import com.kata.rover.domain.Plateau;
import com.kata.rover.domain.Rover;
import com.kata.rover.domain.RoverOutsidePlateauException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MissionControl {
    private final Plateau mars;
    private final Map<Rover, String> rovers = new LinkedHashMap<>();

    public MissionControl(Plateau mars) {
        this.mars = mars;
    }

    public void addRover(Rover rover) {
        rovers.put(rover, "");
        checkPositionValidity(rover, "Initial position is out of scope of the plateau");
    }

    private void checkPositionValidity(Rover rover, String message) {
        if (mars.doesNotContain(rover.getPosition())) {
            appendWarning(rover, message);
            throw new RoverOutsidePlateauException(rover);
        }
    }

    public void attemptMove(Rover rover) {
        rover.move();
        checkPositionValidity(rover, "Attempted move is invalid");
    }

    public void appendWarning(Rover rover, String message) {
        String existing = rovers.getOrDefault(rover, "");
        StringBuilder sb = new StringBuilder();
        if (!existing.isBlank()) {
            sb.append(existing);
            sb.append("|");
        }
        sb.append(message);
        rovers.put(rover, sb.toString());
    }

    public void executeCommands(Rover rover, char[] commands) {
        for (char command : commands) {
            switch (command) {
                case 'M' -> attemptMove(rover);
                case 'L' -> rover.goLeft();
                case 'R' -> rover.goRight();
                default -> throw new IllegalArgumentException("Commande inconnue: " + command);
            }
        }
    }

    public String getWarnings(Rover rover) {
        return rovers.getOrDefault(rover, "");
    }

    public List<Rover> getRovers() {
        return List.copyOf(rovers.keySet());
    }
}