package org.example.sistemareservas.Controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextArea;
import org.example.sistemareservas.Modelo.Habitacion;
import org.example.sistemareservas.Modelo.Hotel;
import org.example.sistemareservas.Modelo.HotelData;
import org.example.sistemareservas.Modelo.Servicio;

public class VentanaReserva {
    Hotel hotel;
    @FXML
    private TextArea areaTest;

    @FXML
    private Button btInicio;

    @FXML
    private Button btReservas;

    @FXML
    private Button btSalir;
    @FXML
    private Button btDatos;
    @FXML
    void mostrarInicio(MouseEvent event) {
        areaTest.setText("xqwewq");
    }
    @FXML
    void salirSesion(MouseEvent event) {

    }
    @FXML
    void realizarReserva(MouseEvent event) {

    }

    @FXML
    void mostrarDatos(MouseEvent event) {
        hotel = HotelData.getHotel();
        areaTest.setText(hotel.toString());
    }
}
