package com.kata.rover;

import com.kata.rover.domain.Plateau;
import com.kata.rover.domain.Rover;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MissionRunnerTest {

    private final InputReader inputReader = new InputReader();

    @Test
    void shouldProduceExpectedOutputFromInputFile(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("input.txt");
        Files.writeString(file, """
                5 5
                1 2 N
                LMLMLMLMM
                3 3 E
                MMRMMRMRRM
                """);

        List<String> lines = inputReader.readNonBlankLines(file);
        Plateau plateau = inputReader.parsePlateau(lines.getFirst());
        MissionControl missionControl = new MissionControl(plateau);
        MissionRunner runner = new MissionRunner(inputReader, missionControl);

        List<Rover> rovers = runner.run(lines);

        String result = rovers.stream()
                .map(Rover::toString)
                .collect(Collectors.joining("\n"));

        assertEquals("1 3 N\n5 1 E", result);
    }
}