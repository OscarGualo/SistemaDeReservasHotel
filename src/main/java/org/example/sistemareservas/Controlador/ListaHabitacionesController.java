package org.example.sistemareservas.Controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Axis;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.sistemareservas.Modelo.*;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListaHabitacionesController implements Initializable {

    @FXML
    private Button btElegir;

    @FXML
    private TableColumn<Habitacion, Integer> columCapacidad;

    @FXML
    private TableColumn<Habitacion, EstadoHabitacion> columEstado;

    @FXML
    private TableColumn<Habitacion, Integer> columId;

    @FXML
    private TableColumn<Habitacion, Integer> columPiso;

    @FXML
    private TableColumn<Habitacion, Double> columPrecio;

    @FXML
    private TableColumn<Habitacion, TipoHabitacion> columTipo;

    @FXML
    private TableView<Habitacion> tablaHabDispo;
    private PaqueteController paqueteController;

    public void setPaqueteController(PaqueteController paqueteController) {
        this.paqueteController = paqueteController;
    }
    @FXML
    void elegirHabitaciones(MouseEvent event) {
        ObservableList<Habitacion> seleccionadas = tablaHabDispo.getSelectionModel().getSelectedItems();

        if (paqueteController != null && !seleccionadas.isEmpty()) {
            paqueteController.agregarHabitacionesSeleccionadas(seleccionadas);


            tablaHabDispo.refresh();
        }

        Stage stage = (Stage) btElegir.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columId.setCellValueFactory(new PropertyValueFactory<>("numero"));
        columPiso.setCellValueFactory(new PropertyValueFactory<>("piso"));
        columPrecio.setCellValueFactory(new PropertyValueFactory<>("precioPorNoche"));
        columCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidadPersonas"));
        columEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        columTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        Hotel hotel = HotelData.getHotel();
        tablaHabDispo.setItems(hotel.buscarHabitaciones("Estado","DISPONIBLE"));
        tablaHabDispo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

}
