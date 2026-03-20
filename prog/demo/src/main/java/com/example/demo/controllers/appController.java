package com.example.demo.controllers;

import com.example.demo.servicies.PersonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class appController {
    @FXML
    private ListView<String> listView;

    @FXML
    private TextField txtEdad;
    @FXML
    private Label lblMsg;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtBusqueda;

    private final ObservableList<String> data = FXCollections.observableArrayList();
    private PersonService service = new PersonService();


    @FXML
    public void initialize(){ // se va a ejecutar el inicia, cuando cargue
        //Iniciar listview
        loadFromFile();
        txtBusqueda.textProperty().addListener((observable, oldValue, newValue) -> {
            filterList(newValue);
        });
        listView.getSelectionModel().selectedItemProperty().addListener((obs,oldValue,newValue)->{
                    loadDataToFrom(newValue);//String con el valor del row 0 test | email@gmail.com | 18
                }
        );
        listView.setItems(data);
    }

    private void filterList(String search) {
        if (search == null || search.isEmpty()) {
            initialize();
            return;
        }
        ObservableList<String> filteredList = FXCollections.observableArrayList();
        String lowerCaseFilter = search.toLowerCase();
        for (String item : data) {
            String[] parts = item.split("\\|");
            String email = parts[1].trim().toLowerCase();
            if (email.contains(lowerCaseFilter)) {
                filteredList.add(item);
            }
        }
        listView.setItems(filteredList);
    }
    @FXML
    public void onUpdate() {
        int index = listView.getSelectionModel().getSelectedIndex();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String edad = txtEdad.getText();
        try {
            service.validatePersone(name, email,edad);
            service.updatePersone(index,name,email,edad);
            lblMsg.setText("Persona agregada con exito");
            lblMsg.setStyle("-fx-text-fill: green");
            txtEmail.clear();
            txtName.clear();
            txtEdad.clear();
            loadFromFile();
        } catch (IOException e) {
            lblMsg.setText("huno un error con el archivo");
            lblMsg.setStyle("-fx-text-fill: red");
        }catch (IllegalArgumentException ex){
            lblMsg.setText("huno un error con los datos");
            lblMsg.setStyle("-fx-text-fill: red");
        }
    }

    @FXML
    public void onDelate(){
        int index = listView.getSelectionModel().getSelectedIndex();
        try{
            service.delatePersone(index);
            loadFromFile();
            lblMsg.setText("Persona eliminada");
            lblMsg.setStyle("-fx-text-fill: green");
        } catch (IOException e) {
            lblMsg.setText("Hubo un error con el archivo en eliminar");
            lblMsg.setStyle("-fx-text-fill: red");
        }
    }
    @FXML
    public void onAddPerson(){
        try{
            String name= txtName.getText();
            String email= txtEmail.getText();
            String edad = txtEdad.getText();

            service.addPersone(name,email,edad);
            lblMsg.setText("Persona agregada con exito");
            lblMsg.setStyle("-fx-text-fill: green");
            txtEmail.clear();
            txtName.clear();
            txtEdad.clear();
            loadFromFile();
        } catch (IOException e) {
            lblMsg.setText("huno un error con el archivo");
            lblMsg.setStyle("-fx-text-fill: red");
        }catch (IllegalArgumentException ex){
            lblMsg.setText("huno un error con los datos");
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
    private void loadDataToFrom(String item){
        if (item == null || item.isEmpty()) {
            return;
        }
        String[] parts =  item.split("\\|");
        txtName.setText(parts[0]);//Correspondinete al nombre
        txtEmail.setText(parts[1]);//Correspondinete al correo
        txtEdad.setText(parts[2]);//Correspondinete a la edad
    }
}