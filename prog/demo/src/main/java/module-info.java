module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.demo.controllers to javafx.fxml;
    opens com.example.demo.services to javafx.fxml;
    opens com.example.demo.repositories to javafx.fxml;

    exports com.example.demo;
    exports com.example.demo.controllers;
    exports com.example.demo.services;
    exports com.example.demo.repositories;
}