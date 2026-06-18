package com.kata.rover;

import com.kata.rover.domain.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Try: java -jar rover.jar <input.txt>");
        }
        InputReader inputReader = new InputReader();
        List<String> lines = readInput(args, inputReader);

        Plateau plateau = inputReader.parsePlateau(lines.getFirst());
        MissionControl missionControl = new MissionControl(plateau);

        MissionRunner missionRunner = new MissionRunner(inputReader, missionControl);

        missionRunner.run(lines);

    }

    private static List<String> readInput(String[] args, InputReader inputReader) {
        List<String> lines;
        try {
            lines = inputReader.readNonBlankLines(Path.of(args[0]));
        } catch (IOException e) {
            throw new IllegalArgumentException("Try: java -jar rover.jar <input.txt>", e);
        }

        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Input file is empty.");
        }
        return lines;
    }

}