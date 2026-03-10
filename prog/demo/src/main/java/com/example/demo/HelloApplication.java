package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String fxmlPath = "/com/example/demo/views/app-vieww.fxml";
        URL url = getClass().getResource(fxmlPath);

        if (url == null) {
            throw new IOException("¡ERROR! No encontré el archivo FXML en: " + fxmlPath
                    + "\nPor favor, verifica que la carpeta 'src/main/resources/com/example/demo/views/' contenga el archivo app-vieww.fxml");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Software de Personas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}