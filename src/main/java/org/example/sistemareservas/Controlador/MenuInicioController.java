package org.example.sistemareservas.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.example.sistemareservas.Modelo.Hotel;
import org.example.sistemareservas.Modelo.HotelData;

public class MenuInicioController {

    @FXML
    private MenuButton botonArchivo;

    @FXML
    private MenuButton botonReservas;

    @FXML
    private MenuItem botonServicios;

    @FXML
    private Button btProbar;

    @FXML
    private TextArea textField;

    @FXML
    void probar(MouseEvent event) {
        Hotel hotel = HotelData.getHotel();

        textField.setText(hotel.toString());
    }

}
