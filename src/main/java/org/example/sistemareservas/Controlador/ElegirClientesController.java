package org.example.sistemareservas.Controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.sistemareservas.Modelo.Cliente;
import org.example.sistemareservas.Modelo.Hotel;
import org.example.sistemareservas.Modelo.HotelData;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ElegirClientesController implements Initializable {
    Hotel hotel = HotelData.getHotel();

    @FXML
    private Button btbElegir;

    @FXML
    private TableColumn<Cliente,String> columApellidos;

    @FXML
    private TableColumn<Cliente ,String> columCedula;

    @FXML
    private TableColumn<Cliente, String> columDireccion;

    @FXML
    private TableColumn<Cliente, String> columEmail;

    @FXML
    private TableColumn<Cliente, String> columNombres;

    @FXML
    private TableColumn<Cliente, String> columTelefono;

    @FXML
    private TableView<Cliente> tablaElegClien;

    @FXML
    void elegirClientes(MouseEvent event) {
        Cliente seleccionado = tablaElegClien.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            // Guardamos el cliente en el "Hotel"
            hotel.setClienteSeleccionado(seleccionado);

            // Cerramos la ventana actual
            Stage stage = (Stage) btbElegir.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        columNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        columApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        columEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        columTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tablaElegClien.setItems(hotel.getClientes());
    }
}
