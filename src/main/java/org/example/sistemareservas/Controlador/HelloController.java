package org.example.sistemareservas.Controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.sistemareservas.HelloApplication;
import org.example.sistemareservas.Modelo.*;
import javafx.scene.image.ImageView;
import java.io.IOException;

public class HelloController {

    @FXML
    private Button btbIniciar;

    @FXML
    private Label lblTesting;

    @FXML
    private TextField txtPass;

    @FXML
    private TextField txtUsuario;
    @FXML
    private ImageView userImage;
    @FXML
    void IniciarSesionTest(MouseEvent event) throws IOException {
        userImage.setVisible(true);
        String  pass = txtPass.getText();
        String usuario = txtUsuario.getText();
        Usuario cliente1 = new Cliente("3123",usuario,usuario,"Playdota.az@gmail.com",pass);
        if(cliente1.iniciarSesion("1","c")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/sistemareservas/VentanaEntradaReservas.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Crear nueva escena y asignarla
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
