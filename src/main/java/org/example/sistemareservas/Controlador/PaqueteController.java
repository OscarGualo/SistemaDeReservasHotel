package org.example.sistemareservas.Controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.sistemareservas.Modelo.Habitacion;
import org.example.sistemareservas.Modelo.Paquete;

import java.io.IOException;
import java.util.Collection;

public class PaqueteController {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btElegirHabitacion;

    @FXML
    private Button btbGuardar;

    @FXML
    private TableColumn<?, ?> columCapacidad;

    @FXML
    private TableColumn<?, ?> columEstado;

    @FXML
    private TableColumn<?, ?> columNumero;

    @FXML
    private TableColumn<?, ?> columPiso;

    @FXML
    private TableColumn<?, ?> columPrecio;

    @FXML
    private TableColumn<?, ?> columTipo;

    @FXML
    private TableView<Habitacion> tableHabEleg;

    @FXML
    private TableView<?> tableServEleg;

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
    void elegirHabitaciones(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/sistemareservas/ListaHabitacionesDisponibles.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Lista Habitaciones");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void elegirServicios(MouseEvent event) {

    }
    private ObservableList<Habitacion> habitacionesElegidas = FXCollections.observableArrayList();

    public void agregarHabitaciones(Collection<Habitacion> nuevas) {
        if (nuevas == null || nuevas.isEmpty()) return;

        habitacionesElegidas.addAll(nuevas);
        // tableHabEleg.setItems(habitacionesElegidas); // ya está seteado en initialize
    }
}
