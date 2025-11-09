package org.example.sistemareservas.Controlador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import org.example.sistemareservas.Modelo.*;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HabitacionController implements Initializable {
    @FXML
    private TableColumn<Habitacion, Integer> columCapacidad;

    @FXML
    private TableColumn<Habitacion,EstadoHabitacion> columEstado;

    @FXML
    private TableColumn<Habitacion,Integer> columId;

    @FXML
    private TableColumn<Habitacion,Integer> columPiso;

    @FXML
    private TableColumn<Habitacion,Double> columPrecio;
    @FXML
    private TableColumn<Habitacion, TipoHabitacion> columTipo;
    @FXML
    private Button btEliminar;

    @FXML
    private Button btSalir;

    @FXML
    private Button btnBuscar;

    @FXML
    private ComboBox<EstadoHabitacion> cbEstado;

    @FXML
    private ComboBox<TipoHabitacion> cbTipoH;

    @FXML
    private Pane listaHabitaciones;

    @FXML
    private TableView<Habitacion> tablaListaHab;

    @FXML
    private TextField textIdHab;

    @FXML
    private TextField textPiso;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TextField txtCapacidadPersonas;

    @FXML
    private TextField txtPrecio;

    @FXML
    void buscarHabitacion(MouseEvent event) {

    }

    @FXML
    void cancelarHabitacion(MouseEvent event) {

    }

    @FXML
    void crearNuevaHabitacion(MouseEvent event) {

    }

    @FXML
    void eliminarHabitacion(MouseEvent event) {

    }
    @FXML
    void agregarHabitacion(MouseEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<TipoHabitacion> tipoHabitacions=FXCollections.observableArrayList(TipoHabitacion.values());
        ObservableList<EstadoHabitacion> listaEstado = FXCollections.observableArrayList(EstadoHabitacion.values());
        cbEstado.setItems(listaEstado);
        cbTipoH.setItems(tipoHabitacions);
        
        columId.setCellValueFactory(new PropertyValueFactory<>("numero"));
        columPiso.setCellValueFactory(new PropertyValueFactory<>("piso"));
        columCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidadPersonas"));
        columPrecio.setCellValueFactory(new PropertyValueFactory<>("precioPorNoche"));
        columEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        columTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        // 🔹 Cargar los datos desde el "HotelData"
        Hotel hotel = HotelData.getHotel();
        tablaListaHab.setItems(hotel.getHabitaciones());


    }
}
