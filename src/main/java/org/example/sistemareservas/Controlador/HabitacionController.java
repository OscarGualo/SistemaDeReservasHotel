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
    private ComboBox<String> cbFiltroBusq;
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
        String criterio = cbFiltroBusq.getValue();
        String valorBusqueda = txtBuscar.getText() != null ? txtBuscar.getText().trim() : "";

        Hotel hotel = HotelData.getHotel();
        if (criterio == null) {
            mostrarAlerta("Seleccione un criterio de búsqueda.");
            return;
        }

        try {
            Recepcionista recepcionista = new Recepcionista("17102", "Oscar", "hernandez", "    x"," 213");
            ObservableList<Habitacion> resultados = recepcionista.buscarHabitaciones(criterio, valorBusqueda, hotel);
            tablaListaHab.setItems(resultados);
        } catch (IllegalArgumentException e) {
            mostrarAlerta(e.getMessage());
        }
    }




    public void modificarCamposActivo(){
        textIdHab.setEditable(true);
        textPiso.setEditable(true);
        txtPrecio.setEditable(true);
        txtCapacidadPersonas.setEditable(true);
        cbEstado.setDisable(false);
        cbTipoH.setDisable(false);
    }
    public void modificarCamposInactivo(){
        textIdHab.setEditable(false);
        textIdHab.setStyle("-fx-control-inner-background: #f0f0f0; -fx-opacity: 1;");

        textPiso.setEditable(false);
        textPiso.setStyle("-fx-control-inner-background: #f0f0f0; -fx-opacity: 1;");

        txtPrecio.setEditable(false);
        txtPrecio.setStyle("-fx-control-inner-background: #f0f0f0; -fx-opacity: 1;");

        txtCapacidadPersonas.setEditable(false);
        txtCapacidadPersonas.setStyle("-fx-control-inner-background: #f0f0f0; -fx-opacity: 1;");
        cbTipoH.setDisable(true);
        cbEstado.setDisable(true );
    }

    @FXML
    void cancelarHabitacion(MouseEvent event) {
        textIdHab.setText("");
        textPiso.setText("");
        txtPrecio.setText("");
        txtCapacidadPersonas.setText("");
        cbEstado.setValue(null);
        cbTipoH.setValue(null);
      modificarCamposInactivo();
    }

    @FXML
    void crearNuevaHabitacion(MouseEvent event) {

       modificarCamposActivo();
    }
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void eliminarHabitacion(MouseEvent event) {
       
    }
    @FXML
    void agregarHabitacion(MouseEvent event) {

        if (textIdHab.getText().isEmpty() ||
                textPiso.getText().isEmpty() ||
                txtPrecio.getText().isEmpty() ||
                txtCapacidadPersonas.getText().isEmpty() ||
                cbEstado.getValue() == null ||
                cbTipoH.getValue() == null) {

            mostrarAlerta("Por favor, completa todos los campos antes de continuar.");
            return;
        }

        try {
            int id = Integer.parseInt(textIdHab.getText());
            int piso = Integer.parseInt(textPiso.getText());
            double precio = Double.parseDouble(txtPrecio.getText());
            int capacidad = Integer.parseInt(txtCapacidadPersonas.getText());

            Recepcionista recepcionista = new Recepcionista("17102", "Oscar", "hernandez", "asd@gmail.com", "admin");
            Hotel hotel = HotelData.getHotel();

            Habitacion habitacionNueva = new Habitacion(id, piso, precio, capacidad, cbEstado.getValue(), cbTipoH.getValue());
            recepcionista.registrarHabitacion(hotel, habitacionNueva);


            mostrarAlerta("Habitación registrada correctamente.");

        } catch (NumberFormatException e) {
            mostrarAlerta("Verifica que los campos numéricos tengan números válidos.");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textIdHab.setEditable(false);
        textIdHab.setStyle("-fx-control-inner-background: #f0f0f0; -fx-opacity: 1;");

        textPiso.setEditable(false);
        textPiso.setStyle("-fx-control-inner-background: #f0f0f0; -fx-opacity: 1;");

        txtPrecio.setEditable(false);
        txtPrecio.setStyle("-fx-control-inner-background: #f0f0f0; -fx-opacity: 1;");

        txtCapacidadPersonas.setEditable(false);
        txtCapacidadPersonas.setStyle("-fx-control-inner-background: #f0f0f0; -fx-opacity: 1;");
        cbTipoH.setDisable(true);
        cbEstado.setDisable(true );
        ObservableList<TipoHabitacion> tipoHabitacions=FXCollections.observableArrayList(TipoHabitacion.values());
        ObservableList<EstadoHabitacion> listaEstado = FXCollections.observableArrayList(EstadoHabitacion.values());
        cbEstado.setItems(listaEstado);
        cbTipoH.setItems(tipoHabitacions);
        cbFiltroBusq.setItems(FXCollections.observableArrayList("SinFiltro","Numero","Estado","Tipo"));

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
