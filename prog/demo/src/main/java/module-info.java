module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.demo to javafx.fxml;
    opens com.example.demo.controllers to javafx.fxml;
    opens com.example.demo.repositories to javafx.fxml;
    opens com.example.demo.servicies to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.repositories;
    exports com.example.demo.controllers;
    exports com.example.demo.servicies;
}