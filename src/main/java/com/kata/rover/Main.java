package com.kata.rover;

import com.kata.rover.domain.Direction;
import com.kata.rover.domain.Plateau;
import com.kata.rover.domain.Rover;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Try: java -jar rover.jar <input.txt>");
            System.exit(1);
        }


        List<String> lines;
        try {
            lines = readNonBlankLines(Path.of(args[0]));
        } catch (IOException e) {
            System.err.println("Impossible to read file " + args[0] + " : " + e.getMessage());
            System.exit(1);
            return;
        }

        if (lines.isEmpty()) {
            System.err.println("Input file is empty.");
            System.exit(1);
            return;
        }

        Plateau plateau = parsePlateau(lines.getFirst());
        MissionControl missionControl = new MissionControl(plateau);

        for (int i = 1; i + 1 < lines.size(); i += 2) {
            Rover rover = parseRover(lines.get(i));
            char[] commands = lines.get(i + 1).trim().toCharArray();

            missionControl.addRover(rover);
            missionControl.executeCommands(rover, commands);

            System.out.println(rover);
            String warnings = missionControl.getWarnings(rover);
            if (!warnings.isEmpty()) {
                System.out.println("  [WARN] " + warnings);
            }
        }

    }

    private static Plateau parsePlateau(String line) {
        String[] parts = line.trim().split("\\s+");
        int maxX = Integer.parseInt(parts[0]);
        int maxY = Integer.parseInt(parts[1]);
        return new Plateau(maxX, maxY);
    }

    private static Rover parseRover(String line) {
        String[] parts = line.trim().split("\\s+");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Direction direction = Direction.valueOf(parts[2]);
        return new Rover(x, y, direction);
    }

    private static List<String> readNonBlankLines(Path path) throws IOException {
        List<String> result = new ArrayList<>();
        for (String line : Files.readAllLines(path)) {
            if (!line.isBlank()) {
                result.add(line);
            }
        }
        return result;
    }
}