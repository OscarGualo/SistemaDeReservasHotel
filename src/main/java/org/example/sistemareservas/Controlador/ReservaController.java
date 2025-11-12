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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.sistemareservas.Modelo.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.EmptyStackException;
import java.util.ResourceBundle;

public class ReservaController implements Initializable {

    @FXML
    private Button btEliminarReserva;

    @FXML
    private Button btNuevaReserva;

    @FXML
    private Button btRealizarPago;

    @FXML
    private Button btSalir;

    @FXML
    private Button btbBuscarReserva;

    @FXML
    private Button btbCancelar;

    @FXML
    private Button btbCrearPaquete;

    @FXML
    private Button btbElegirCliente;

    @FXML
    private Button btbGuardarReserva;

    @FXML
    private ComboBox<EstadoReserva> cbEstadoReserva;

    @FXML
    private TableColumn<Reserva,String> columCliente;

    @FXML
    private TableColumn<Reserva,Integer> columCodigo;

    @FXML
    private TableColumn<Reserva,String> columEntrada;

    @FXML
    private TableColumn<Reserva, EstadoReserva> columEstado;

    @FXML
    private TableColumn<Reserva,String> columPaquete;

    @FXML
    private TableColumn<Reserva,String> columSal;

    @FXML
    private TableView<Reserva> tablaReserva;

    @FXML
    private TextField txtCosto;

    @FXML
    private TextField txtEntrada;

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPaquete;
    @FXML
    private TextField txtSalida;
    private Hotel hotel = HotelData.getHotel();

    @FXML
    void accionCrearPaquete(MouseEvent event) {

    }

    @FXML
    void accionElegirCliente(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/sistemareservas/ElegirCliente.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Elegir Cliente");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait(); // Espera a que se cierre

            // 🔹 Recuperamos el cliente seleccionado desde el Hotel
            Cliente seleccionado = hotel.getClienteSeleccionado();
            if (seleccionado != null) {
                txtNombre.setText(seleccionado.getNombres() + " " + seleccionado.getApellidos());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void buscarReserva(MouseEvent event) {

    }

    @FXML
    void crearNuevaReserva(MouseEvent event) {

    }
    private void cargarReservas() {
        tablaReserva.setItems(hotel.getReservas());
        tablaReserva.refresh();
    }
    @FXML
    void eliminarReserva(MouseEvent event) {

    }

    @FXML
    void guardarReserva(MouseEvent event) {
        try {
            // ✅ VALIDAR CAMPOS OBLIGATORIOS
            if (txtCodigo.getText().isEmpty() || txtEntrada.getText().isEmpty() ||
                    txtSalida.getText().isEmpty() || cbEstadoReserva.getValue() == null) {
                mostrarAlerta("Error", "Complete todos los campos obligatorios (Código, Fechas, Estado)");
                return;
            }

            // ✅ VALIDAR QUE HAYA CLIENTE SELECCIONADO
            Cliente cliente = hotel.getClienteSeleccionado();
            if (cliente == null) {
                mostrarAlerta("Error", "Debe elegir un cliente antes de guardar la reserva");
                return;
            }

            // ✅ CREAR PAQUETE (temporal o puedes hacer una ventana para elegir)
            Paquete paquete = new Paquete(
                    "Paquete Básico",
                    "Paquete de prueba",
                    hotel.getHabitaciones(),
                    hotel.getServicios()
            );

            // ✅ CREAR LA RESERVA
            Reserva nuevaReserva = new Reserva(
                    txtCodigo.getText(),
                    txtEntrada.getText(),
                    txtSalida.getText(),
                    cbEstadoReserva.getValue(),
                    paquete,
                    cliente
            );

            // ✅ AGREGAR AL HOTEL
            hotel.agregarReserva(nuevaReserva);

            // ✅ ACTUALIZAR LA TABLA
            cargarReservas();

            // ✅ LIMPIAR FORMULARIO


            mostrarAlerta("Éxito", "Reserva guardada correctamente");

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El código debe ser un número válido");
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo guardar la reserva: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void mostrarAlerta(String camposVacíos, String s) {
        JOptionPane.showMessageDialog(null, "Debe completar los campos ");
    }

    @FXML
    void realizarPago(MouseEvent event) {

    }

    @FXML
    void salirMenuInicio(MouseEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<EstadoReserva> tipoReserva= FXCollections.observableArrayList(EstadoReserva.values());
        cbEstadoReserva.setItems(tipoReserva);
        columCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoReserva"));
        columEntrada.setCellValueFactory(new PropertyValueFactory<>("fechaEntrada"));
        columSal.setCellValueFactory(new PropertyValueFactory<>("fechaSalida"));
        columEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        // Columnas complejas que necesitan obtener datos de objetos anidados
        columPaquete.setCellValueFactory(cellData -> {
            Paquete paquete = cellData.getValue().getPaquete();
            String nombrePaquete = paquete != null ? paquete.getNombre() : "Sin asignar";
            return new javafx.beans.property.SimpleStringProperty(nombrePaquete);
        });

        columCliente.setCellValueFactory(cellData -> {
            Cliente cliente = cellData.getValue().getCliente();
            String nombreCompleto = cliente != null ?
                    cliente.getNombres() + " " + cliente.getApellidos() : "Sin asignar";
            return new javafx.beans.property.SimpleStringProperty(nombreCompleto);
        });



    }
}
