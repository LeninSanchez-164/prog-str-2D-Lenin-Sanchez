package com.example.demo.controllers;

import com.example.demo.services.PersonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class appController {
    @FXML
    private ListView<String> listView;

    @FXML
    private Label lblMsg;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtEdad; //

    private final ObservableList<String> data = FXCollections.observableArrayList();
    private PersonService service = new PersonService();

    @FXML
    public void initialize(){
        listView.setItems(data);
        loadFromFile();
    }

    @FXML
    public void onAddPerson(){
        try{
            String name = txtName.getText();
            String email = txtEmail.getText();

            int age = Integer.parseInt(txtEdad.getText());

            service.addPersone(name, email, age);

            lblMsg.setText("Persona agregada con éxito");
            lblMsg.setStyle("-fx-text-fill: green");

            txtEmail.clear();
            txtName.clear();
            txtEdad.clear();
            loadFromFile();

        } catch (NumberFormatException e) {
            // Esto atrapa si el usuario escribe letras en el campo de edad
            lblMsg.setText("Error: La edad debe ser un número");
            lblMsg.setStyle("-fx-text-fill: red");

        } catch (IOException e) {
            lblMsg.setText("Hubo un error con el archivo");
            lblMsg.setStyle("-fx-text-fill: red");

        } catch (IllegalArgumentException ex){
            // Esto atrapa el error de "Menor de 18 años" (y cualquier otra validación)
            lblMsg.setText(ex.getMessage());
            lblMsg.setStyle("-fx-text-fill: red");
        }
    }

    @FXML
    private void loadFromFile(){
        try{
            List<String> items = service.loadDataList();
            data.setAll(items);
            lblMsg.setText("Datos cargados con normalidad");
            lblMsg.setStyle("-fx-text-fill: green");
        } catch (Exception e) {
            lblMsg.setText(e.getMessage());
            lblMsg.setStyle("-fx-text-fill: red");
        }
    }
}