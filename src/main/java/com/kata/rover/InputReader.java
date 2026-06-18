package com.kata.rover;

import com.kata.rover.domain.Direction;
import com.kata.rover.domain.Plateau;
import com.kata.rover.domain.Rover;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
    InputReader() {
    }


    public List<String> readNonBlankLines(Path path) throws IOException {
        List<String> result = new ArrayList<>();
        for (String line : Files.readAllLines(path)) {
            if (!line.isBlank()) {
                result.add(line);
            }
        }
        return result;
    }

    public Plateau parsePlateau(String line) {
        String[] parts = line.trim().split("\\s+");
        int maxX = Integer.parseInt(parts[0]);
        int maxY = Integer.parseInt(parts[1]);
        return new Plateau(maxX, maxY);
    }

    public Rover parseRover(String line) {
        String[] parts = line.trim().split("\\s+");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Direction direction = Direction.valueOf(parts[2]);
        return new Rover(x, y, direction);
    }
}
