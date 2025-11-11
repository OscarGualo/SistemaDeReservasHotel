package org.example.sistemareservas.Controlador;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.sistemareservas.Modelo.EstadoServicio;
import org.example.sistemareservas.Modelo.Hotel;
import org.example.sistemareservas.Modelo.HotelData;
import org.example.sistemareservas.Modelo.Servicio;

import java.io.Serial;
import java.net.URL;
import java.util.ResourceBundle;

public class ListaServiciosDisponiblesController implements Initializable {

    @FXML
    private Button btbElegirS;

    @FXML
    private TableColumn<Servicio,Integer> columCodigo;

    @FXML
    private TableColumn<Servicio,String> columDesc;

    @FXML
    private TableColumn<Servicio, EstadoServicio> columEstado;

    @FXML
    private TableColumn<Servicio, String> columNombre;

    @FXML
    private TableColumn<Servicio, Double> columPrecio;

    @FXML
    private TableView<Servicio> serviciosDispo;
    private PaqueteController paqueteController;
    @FXML
    void elegirServiciosDis(MouseEvent event) {
        ObservableList<Servicio> seleccionados = serviciosDispo.getSelectionModel().getSelectedItems();
        paqueteController.agregarServiciosSeleccionados(seleccionados);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void setPaqueteController(PaqueteController paqueteController) {
        this.paqueteController = paqueteController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
      columDesc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
      columPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
      columEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        Hotel hotel = HotelData.getHotel();
        String hola = "hola";
    }
}
