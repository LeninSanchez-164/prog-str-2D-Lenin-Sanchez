package com.example.demo.servicies;

import com.example.demo.repositories.PersonFileRepositories;

import java.io.IOException;
import java.util.*;

public class PersonService {
    private PersonFileRepositories repo = new PersonFileRepositories();

    public List<String> loadDataList() throws IOException {
        List<String> lines = repo.readAllLines(); //Recupera las lineas del archivo
        List<String> result = new ArrayList<>();//el listado de resultado con el fomato deseado
        for (String Line : lines){
            if (Line==null || Line.isBlank()) continue; // ignora las lineas nulas
            String[] parts = Line.split(",",-1);
            String name = parts[0].trim();//obtiene el nombre del arreglo
            String core = parts[1].trim(); // obtiene el core del arreglo
            String age = (parts.length > 2) ? parts[2].trim(): "N/A"; //Obtiene la edad
            result.add(name+"|"+ core+ "|"+ age);//se afrefa a la lista de resultados con el formato deseado
        }
        return result;
    }
    public void addPersone(String name, String email,String edad) throws IOException {
        validatePersone(name,email,edad);
        String nameNoComa = name.replace(",","");
        String mailNoComa = email.replace(",","");
        repo.appendNewLine(nameNoComa + ","+mailNoComa+","+edad);
    }
    public void updatePersone(int index, String name, String email, String edad) throws IOException {
        List<String> lines = getAllCleanLines();
        if (index == -1){
            throw new IllegalArgumentException("El indice recibido es invalido");
        }
        lines.set(index,name+","+email+","+edad);
        repo.appendSentLine(lines);
    }
    public void delatePersone(int index) throws IOException {
        List<String> lines = getAllCleanLines();
        lines.remove(index);
        repo.appendSentLine(lines);
    }
    private List<String> getAllCleanLines() throws IOException {
        List<String> lines = repo.readAllLines();
        List<String> cleanLines = new ArrayList<>();
        for (String line: lines){
            if(line!=null && !line.isBlank()){
                cleanLines.add(line);
            }
        }
        return cleanLines;
    }

    public void validatePersone(String name, String email, String edad){
        if(name == null || name.isBlank() || name.length()<3) {
            throw new IllegalArgumentException("El nombre no cumple con los estandares");
        }
        String em = (email == null) ? "" : email.trim();
        if (em.isBlank() || !em.contains("@") || !em.contains(".")){
            throw new IllegalArgumentException("El correo es incorrecto");
        }
        try {
            if (edad == null || edad.isBlank()) {
                throw new IllegalArgumentException("La edad no puede estar vacía");
            }
            int Numerico = Integer.parseInt(edad.trim());
            if (Numerico < 18) {
                throw new IllegalArgumentException("La persona debe ser mayor de 18 años");
            }
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("La edad debe ser un numero valido/entero");
        }
    }
}