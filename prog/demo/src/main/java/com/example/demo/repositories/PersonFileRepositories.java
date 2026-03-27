package com.example.demo.repositories;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class PersonFileRepositories {
    // 1. CORRECCIÓN: Cambiamos "data" por "datos"
    private final Path filePath = Paths.get("datos","personas.csv");

    private void ensureFileExist() throws IOException {
        // 2. CORRECCIÓN: Nos aseguramos de que la carpeta y el archivo se creen si no existen
        if (Files.notExists(filePath)){
            if (filePath.getParent() != null && Files.notExists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            Files.createFile(filePath);
        }
    }

    public List<String> readAllLines() throws IOException {
        ensureFileExist();
        return Files.readAllLines(filePath, StandardCharsets.UTF_8);
    }

    public void appendNewLine(String line) throws IOException {
        ensureFileExist(); // <--- Agregamos esto para que no falle al Agregar
        Files.writeString(filePath, line + System.lineSeparator(), StandardCharsets.UTF_8,
                StandardOpenOption.APPEND);
    }

    public void appendSentLine(List<String> lines) throws IOException {
        ensureFileExist(); // <--- Agregamos esto para que no falle al Actualizar/Eliminar
        Files.write(filePath, lines, StandardCharsets.UTF_8,
                StandardOpenOption.TRUNCATE_EXISTING);
    }
}