package com.kata.rover;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
class InputReaderTest {

    InputReader ir = new InputReader();

    @Test
    void plateauShouldBeParsed() {
        String plateau = "3 4";
        assertEquals(3, ir.parsePlateau(plateau).getMaxX());
        assertEquals(4, ir.parsePlateau(plateau).getMaxY());
    }

    @Test
    void roverPositionShouldBeParsed() {
        assertEquals("1 1 N", ir.parseRover("1 1 N").toString());
    }

    @Test
    void readNonBlankLinesShouldReturnAllLinesWhenNoneAreBlank(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("input.txt");
        Files.writeString(file, "5 5\n1 2 N\nLMLMLMLMM\n");

        List<String> lines = ir.readNonBlankLines(file);

        assertEquals(List.of("5 5", "1 2 N", "LMLMLMLMM"), lines);
    }

    @Test
    void readNonBlankLinesShouldThrowWhenFileDoesNotExist(@TempDir Path tempDir) {
        Path missingFile = tempDir.resolve("does-not-exist.txt");

        assertThrows(IOException.class, () -> ir.readNonBlankLines(missingFile));
    }
}
