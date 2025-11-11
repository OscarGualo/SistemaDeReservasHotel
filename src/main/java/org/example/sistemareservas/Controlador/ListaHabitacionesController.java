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
import org.example.sistemareservas.Modelo.*;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ListaHabitacionesController implements Initializable {
    private PaqueteController paqueteController; // referencia al otro controlador
    private Hotel hotel = HotelData.getHotel(); // como lo obtenías antes
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

    @FXML
    void elegirHabitaciones(MouseEvent event) {
        ObservableList<Habitacion> seleccion = tablaHabDispo.getSelectionModel().getSelectedItems();

        if (seleccion == null || seleccion.isEmpty()) {
            // mostrar alerta o mensaje si quieres
            System.out.println("No seleccionó habitaciones.");
            return;
        }

        // Crear copia para evitar problemas de concurrencia con la SelectionModel / tablas
        List<Habitacion> copia = new ArrayList<>(seleccion);

        // 1) Cambiar estado en cada habitacion y en el modelo (si corresponde)
        for (Habitacion h : copia) {
            h.setEstado("OCUPADA"); // o Enum si usas enum
            // si tienes una estructura en Hotel que mantiene la lista, asegúrate de que
            // ese mismo objeto Habitacion sea el de la lista principal para que se actualice.
            // Si tu Hotel usa objetos distintos, tendrías que buscar por número y actualizar ahí.
        }

        // 2) Actualizar la lista que muestra tablaHabDispo: recargar filtrado
        tablaHabDispo.setItems(hotel.buscarHabitaciones("Estado", "DISPONIBLE"));

        // 3) Enviar las habitaciones elegidas al paqueteController
        if (paqueteController != null) {
            paqueteController.agregarHabitaciones(copia);
        } else {
            System.err.println("paqueteController es null — no se pudo enviar las habitaciones.");
        }
    }



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
    public void setPaqueteController(PaqueteController controller) {
        this.paqueteController = controller;
    }
}
