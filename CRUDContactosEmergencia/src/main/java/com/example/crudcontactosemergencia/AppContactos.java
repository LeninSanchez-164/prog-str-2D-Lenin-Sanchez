package com.example.crudcontactosemergencia;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AppContactos extends Application {

    // Lista para almacenar los datos en memoria
    private List<Contacto> listaContactos = new ArrayList<>();

    // Arreglo obligatorio para cargar el ComboBox
    private String[] opcionesParentesco = {
            "Padre", "Madre", "Hermano", "Hermana",
            "Abuelo", "Abuela", "Tio", "Tia"
    };

    private TextField txtNombre;
    private TextField txtTelefono;
    private ComboBox<String> cbParentesco;
    private ListView<Contacto> listViewContactos;
    private ObservableList<Contacto> observableContactos;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registro de Contactos de Emergencia");

        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre del contacto");

        txtTelefono = new TextField();
        txtTelefono.setPromptText("Telefono (10 digitos)");

        cbParentesco = new ComboBox<>();
        cbParentesco.getItems().addAll(opcionesParentesco);
        cbParentesco.setPromptText("Seleccione parentesco");

        Button btnAgregar = new Button("Agregar");
        Button btnBuscar = new Button("Buscar");
        Button btnActualizar = new Button("Actualizar");
        Button btnEliminar = new Button("Eliminar");
        Button btnLimpiar = new Button("Limpiar");

        listViewContactos = new ListView<>();
        observableContactos = FXCollections.observableArrayList();
        listViewContactos.setItems(observableContactos);

        btnAgregar.setOnAction(e -> agregarContacto());
        btnBuscar.setOnAction(e -> buscarContacto());
        btnActualizar.setOnAction(e -> actualizarContacto());
        btnEliminar.setOnAction(e -> eliminarContacto());
        btnLimpiar.setOnAction(e -> limpiarCampos());

        listViewContactos.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtNombre.setText(newVal.getNombre());
                txtTelefono.setText(newVal.getTelefono());
                cbParentesco.setValue(newVal.getParentesco());
            }
        });

        GridPane gridInput = new GridPane();
        gridInput.setVgap(10);
        gridInput.setHgap(10);
        gridInput.add(new Label("Nombre:"), 0, 0);
        gridInput.add(txtNombre, 1, 0);
        gridInput.add(new Label("Telefono:"), 0, 1);
        gridInput.add(txtTelefono, 1, 1);
        gridInput.add(new Label("Parentesco:"), 0, 2);
        gridInput.add(cbParentesco, 1, 2);

        HBox hboxBotones = new HBox(10, btnAgregar, btnBuscar, btnActualizar, btnEliminar, btnLimpiar);
        hboxBotones.setAlignment(Pos.CENTER);

        VBox layoutPrincipal = new VBox(15, gridInput, hboxBotones, new Label("Lista de Contactos:"), listViewContactos);
        layoutPrincipal.setPadding(new Insets(20));

        Scene scene = new Scene(layoutPrincipal, 450, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void agregarContacto() {
        if (!validarEntradas()) return;
        String nombre = txtNombre.getText().trim();

        if (existeContacto(nombre)) {
            mostrarAlerta(Alert.AlertType.WARNING, "Error", "Ya existe un contacto con ese nombre.");
            return;
        }

        Contacto nuevoContacto = new Contacto(nombre, txtTelefono.getText().trim(), cbParentesco.getValue());
        listaContactos.add(nuevoContacto);
        actualizarListView();
        limpiarCampos();
        mostrarAlerta(Alert.AlertType.INFORMATION, "Exito", "Contacto agregado correctamente.");
    }

    private void buscarContacto() {
        String nombreBuscar = txtNombre.getText().trim();
        if (nombreBuscar.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Error", "Ingrese un nombre para buscar.");
            return;
        }

        Contacto encontrado = obtenerContactoPorNombre(nombreBuscar);

        if (encontrado != null) {
            txtNombre.setText(encontrado.getNombre());
            txtTelefono.setText(encontrado.getTelefono());
            cbParentesco.setValue(encontrado.getParentesco());
            listViewContactos.getSelectionModel().select(encontrado);
        } else {
            mostrarAlerta(Alert.AlertType.INFORMATION, "No encontrado", "No se encontró ningún contacto con ese nombre.");
            limpiarCampos();
        }
    }

    private void actualizarContacto() {
        String nombreBuscar = txtNombre.getText().trim();
        Contacto contacto = obtenerContactoPorNombre(nombreBuscar);

        if (contacto != null) {
            if (!validarEntradas()) return;
            contacto.setTelefono(txtTelefono.getText().trim());
            contacto.setParentesco(cbParentesco.getValue());
            actualizarListView();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Exito", "Contacto actualizado correctamente.");
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Error", "No se puede actualizar. El contacto no existe.");
        }
    }

    private void eliminarContacto() {
        String nombreBuscar = txtNombre.getText().trim();
        Contacto contacto = obtenerContactoPorNombre(nombreBuscar);

        if (contacto != null) {
            listaContactos.remove(contacto);
            actualizarListView();
            limpiarCampos();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Exito", "Contacto eliminado correctamente.");
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Error", "El contacto que intenta eliminar no existe.");
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtTelefono.clear();
        cbParentesco.setValue(null);
        listViewContactos.getSelectionModel().clearSelection();
    }

    private boolean validarEntradas() {
        if (txtNombre.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Validacion", "El nombre no debe estar vacio.");
            return false;
        }
        String telefono = txtTelefono.getText().trim();
        if (telefono.isEmpty() || !telefono.matches("\\d{10}")) {
            mostrarAlerta(Alert.AlertType.ERROR, "Validacion", "El telefono debe contener exactamente 10 digitos numericos.");
            return false;
        }
        if (cbParentesco.getValue() == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Validacion", "Debe seleccionar un parentesco.");
            return false;
        }
        return true;
    }

    private boolean existeContacto(String nombre) {
        return obtenerContactoPorNombre(nombre) != null;
    }

    private Contacto obtenerContactoPorNombre(String nombre) {
        for (Contacto c : listaContactos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    private void actualizarListView() {
        observableContactos.setAll(listaContactos);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}