package org.example.sistemareservas.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.sistemareservas.Modelo.Paquete;

public class PaqueteController {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btElegirHabitacion;

    @FXML
    private Button btbGuardar;

    @FXML
    private TextField txtDes;

    @FXML
    private TextField txtHabitaciones;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtServicio;

    @FXML
    void elegirHabitaciones(MouseEvent event) {
        Paquete paquete = new Paquete()
    }

    @FXML
    void elegirServicios(MouseEvent event) {

    }

}
