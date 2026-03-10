package com.example.demo.services;

import com.example.demo.repositories.PersonFileRepositories;

import java.io.IOException;
import java.util.*;

public class PersonService {
    private PersonFileRepositories repo = new PersonFileRepositories();

    public List<String> loadDataList() throws IOException {
        List<String> lines = repo.readAllLines();
        List<String> result = new ArrayList<>();

        for (String Line : lines) {
            if (Line == null || Line.isBlank()) continue;

            String[] parts = Line.split(",", -1);
            // Aseguramos que tenemos 3 partes antes de leerlas para evitar errores
            if (parts.length >= 3) {
                String name = parts[0].trim();
                String email = parts[1].trim();
                String age = parts[2].trim();

                result.add(name + " | " + email + " | Edad: " + age);
            }
        }
        return result;
    }

    // Nota: Recibe int age ahora
    public void addPersone(String name, String email, int age) throws IOException {
        validatePersone(name, email, age);

        String nameNoComa = name.replace(",", "");
        String mailNoComa = email.replace(",", "");

        repo.appendNewLine(nameNoComa + "," + mailNoComa + "," + age);
    }

    private void validatePersone(String name, String email, int age) {
        if (name == null || name.isBlank() || name.length() < 3) {
            throw new IllegalArgumentException("El nombre no cumple con los estándares");
        }

        String em = (email == null) ? "" : email.trim();
        if (em.isBlank() || !em.contains("@") || !em.contains(".")) {
            throw new IllegalArgumentException("El correo es incorrecto");
        }

        // Validación obligatoria del profesor
        if (age < 18) {
            throw new IllegalArgumentException("El paciente debe ser mayor de edad");
        }
    }
}