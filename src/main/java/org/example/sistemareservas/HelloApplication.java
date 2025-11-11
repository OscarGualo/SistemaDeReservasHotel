package org.example.sistemareservas;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ServiciosGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 650);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
