package org.example.sistemareservas.Controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.sistemareservas.Modelo.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PaqueteController implements Initializable {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btElegirHabitacion;

    @FXML
    private Button btbGuardar;

    @FXML
    private TableColumn<Habitacion, Integer> columCapacidad;

    @FXML
    private TableColumn<Habitacion, EstadoHabitacion> columEstado;

    @FXML
    private TableColumn<Habitacion, Integer> columNumero;

    @FXML
    private TableColumn<Habitacion, Integer> columPiso;

    @FXML
    private TableColumn<Habitacion, Double> columPrecio;

    @FXML
    private TableColumn<Habitacion, TipoHabitacion> columTipo;

    @FXML
    private TableView<Habitacion> tableHabEleg;

    @FXML
    private TableView<Servicio> tableServEleg;

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
    private TextField txtSubHab;

    @FXML
    private TextField txtSubServ;
    @FXML
    private Button btbServicios;
    private ObservableList<Habitacion> habitacionesSeleccionadas = FXCollections.observableArrayList();
    private ObservableList<Servicio> serviciosSeleccionados = FXCollections.observableArrayList();
    @FXML
    void elegirHabitaciones(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/sistemareservas/ListaHabitacionesDisponibles.fxml"));
        Parent root = loader.load();


        ListaHabitacionesController listaController = loader.getController();

        listaController.setPaqueteController(this);


        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Seleccionar Habitaciones");
        stage.showAndWait();

    }
    public void agregarHabitacionesSeleccionadas(ObservableList<Habitacion> habitaciones) {
        for (Habitacion hab : habitaciones) {
            if (!habitacionesSeleccionadas.contains(hab)) {

                hab.cambiarEstado(EstadoHabitacion.OCUPADA);
                habitacionesSeleccionadas.add(hab);

            }
        }

        tableHabEleg.setItems(habitacionesSeleccionadas);
        tableHabEleg.refresh();
        double subtotal = 0;
        for (Habitacion h : habitacionesSeleccionadas) {
            subtotal += h.calcularCosto(); // cada habitación sabe su costo
        }

        txtSubHab.setText(String.format("%.2f", subtotal));
    }
    public void agregarServiciosSeleccionados(ObservableList<Servicio> servicios) {
        for (Servicio s : servicios) {
            if (!serviciosSeleccionados.contains(s)) {
                serviciosSeleccionados.add(s);
            }
        }

        tableServEleg.setItems(serviciosSeleccionados);
        tableServEleg.refresh();

        // Calcular subtotal de servicios
        double subtotal = 0;
        for (Servicio s : serviciosSeleccionados) {
            subtotal += s.calcularCosto(); // cada servicio sabe su costo
        }

        txtSubServ.setText(String.format("%.2f", subtotal));
    }
    @FXML
    void elegirServicios(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/sistemareservas/ListaServiciosDisponibles.fxml"));
        Parent root = loader.load();

        ListaServiciosDisponiblesController listaController = loader.getController();
        listaController.setPaqueteController(this); // Pasamos referencia para que pueda llamar agregarServiciosSeleccionados

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Seleccionar Servicios");
        stage.showAndWait();
    }


    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        columNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        columPiso.setCellValueFactory(new PropertyValueFactory<>("piso"));
        columCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidadPersonas"));
        columPrecio.setCellValueFactory(new PropertyValueFactory<>("precioPorNoche"));
        columEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        columTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

    }
    @FXML
    void crearPaquete(MouseEvent event) {

    }
}
