package com.example.demo.repositories;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class PersonFileRepositories {
    private final Path filePath = Paths.get("datos", "personas.csv");

    private void ensureFileExist() throws IOException {
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }

    public List<String> readAllLines() throws IOException {
        ensureFileExist();
        return Files.readAllLines(filePath, StandardCharsets.UTF_8);
    }

    public void appendNewLine(String line) throws IOException {
        Files.writeString(filePath, line + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }
}