package org.example.sistemareservas.Controlador;

import java.util.Optional;

import org.example.sistemareservas.Modelo.Hotel;
import org.example.sistemareservas.Modelo.Servicio;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ServiciosController {

    // === FXML ===
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextArea  txtDescripcion;
    @FXML private TextField txtPrecio;
    @FXML private ComboBox<String> cmbEstado;   // "Activo" / "Inactivo"

    @FXML private TextField txtBuscar;
    @FXML private TableView<Servicio> tblServicios;
    @FXML private TableColumn<Servicio, Number> colCodigo;
    @FXML private TableColumn<Servicio, String> colNombre;
    @FXML private TableColumn<Servicio, Number> colPrecio;
    @FXML private TableColumn<Servicio, String> colEstado;

    // === Modelo / Datos ===
    private final Hotel hotel = new Hotel(); // ya trae servicios quemados en el constructor
    private final ObservableList<Servicio> fuente = hotel.getServicios(); // lista base
    private final ObservableList<Servicio> vista = FXCollections.observableArrayList(); // lo que se ve (filtrada)

    private Servicio seleccionado = null;

    @FXML
    private void initialize() {
        // Llenar combo estado
        cmbEstado.setItems(FXCollections.observableArrayList("Activo", "Inactivo"));
        cmbEstado.getSelectionModel().selectFirst();

        // Configurar columnas
        colCodigo.setCellValueFactory(cd -> new SimpleIntegerProperty(cd.getValue().getCodigo()));
        colNombre.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getNombre()));
        colPrecio.setCellValueFactory(cd -> new SimpleDoubleProperty(cd.getValue().getPrecio()));
        colEstado.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().isEstado() ? "Activo" : "Inactivo"));

        // Tabla: datos iniciales
        vista.setAll(fuente);
        tblServicios.setItems(vista);

        // Click fila -> cargar al formulario
        tblServicios.setOnMouseClicked(this::onTablaClick);
    }

    // ============== ACCIONES ==============

    @FXML
    private void onBuscar(ActionEvent e) {
        String q = txtBuscar.getText();
        if (q == null || q.isBlank()) {
            vista.setAll(fuente);
            return;
        }
        try {
            int codigo = Integer.parseInt(q.trim());
            ObservableList<Servicio> filtrados = FXCollections.observableArrayList();
            for (Servicio s : fuente) {
                if (s.getCodigo() == codigo) filtrados.add(s);
            }
            vista.setAll(filtrados);
        } catch (NumberFormatException ex) {
            showInfo("Búsqueda", "Ingrese un código numérico.");
        }
    }

    @FXML
    private void onNuevo(ActionEvent e) {
        limpiarFormulario();
        seleccionado = null;
        txtCodigo.requestFocus();
    }

    @FXML
    private void onGuardar(ActionEvent e) {
        // Validación básica
        Integer codigo = leerEntero(txtCodigo, "Código");
        if (codigo == null) return;

        String nombre = txtNombre.getText();
        if (nombre == null || nombre.isBlank()) {
            showInfo("Validación", "El nombre es obligatorio."); return;
        }

        Double precio = leerDouble(txtPrecio, "Precio");
        if (precio == null) return;

        String descripcion = (txtDescripcion.getText() == null) ? "" : txtDescripcion.getText();
        boolean estado = "Activo".equals(cmbEstado.getValue());

        if (seleccionado == null) {
            // Alta (nuevo)
            // Verificar que no exista ese código
            for (Servicio s : fuente) {
                if (s.getCodigo() == codigo) {
                    showInfo("Validación", "Ya existe un servicio con el código " + codigo);
                    return;
                }
            }
            Servicio nuevo = new Servicio(codigo, nombre, descripcion, precio, estado);
            fuente.add(nuevo);
            vista.setAll(fuente);
            tblServicios.getSelectionModel().select(nuevo);
            seleccionado = nuevo;
            showInfo("Éxito", "Servicio creado.");
        } else {
            // Edición
            seleccionado.setCodigo(codigo);
            seleccionado.setNombre(nombre);
            seleccionado.setDescripcion(descripcion);
            seleccionado.setPrecio(precio);
            seleccionado.setEstado(estado);
            // refrescar tabla
            tblServicios.refresh();
            showInfo("Éxito", "Servicio actualizado.");
        }
    }

    @FXML
    private void onCancelar(ActionEvent e) {
        if (seleccionado != null) {
            cargarFormulario(seleccionado);
        } else {
            limpiarFormulario();
        }
    }

    @FXML
    private void onEliminar(ActionEvent e) {
        Servicio s = tblServicios.getSelectionModel().getSelectedItem();
        if (s == null) { showInfo("Eliminar", "Seleccione un servicio en la tabla."); return; }

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("¿Eliminar servicio " + s.getNombre() + " (código " + s.getCodigo() + ")?");
        Optional<ButtonType> r = a.showAndWait();
        if (r.isPresent() && r.get() == ButtonType.OK) {
            fuente.remove(s);
            vista.setAll(fuente);
            limpiarFormulario();
            seleccionado = null;
        }
    }

    @FXML
    private void onReporte(ActionEvent e) {
        showInfo("Reporte", "Aquí puedes generar un PDF/Excel más adelante.");
    }

    @FXML
    private void onSalir(ActionEvent e) {
        // cierra la ventana actual
        txtCodigo.getScene().getWindow().hide();
    }

    private void onTablaClick(MouseEvent ev) {
        Servicio s = tblServicios.getSelectionModel().getSelectedItem();
        if (s != null) {
            seleccionado = s;
            cargarFormulario(s);
        }
    }

    // ============== UTIL ==============

    private void cargarFormulario(Servicio s) {
        txtCodigo.setText(String.valueOf(s.getCodigo()));
        txtNombre.setText(s.getNombre());
        txtDescripcion.setText(s.getDescripcion());
        txtPrecio.setText(String.valueOf(s.getPrecio()));
        cmbEstado.getSelectionModel().select(s.isEstado() ? "Activo" : "Inactivo");
    }

    private void limpiarFormulario() {
        txtCodigo.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtPrecio.clear();
        cmbEstado.getSelectionModel().selectFirst();
    }

    private Integer leerEntero(TextField tf, String campo) {
        try {
            return Integer.parseInt(tf.getText().trim());
        } catch (Exception ex) {
            showInfo("Validación", campo + " debe ser numérico.");
            return null;
        }
    }

    private Double leerDouble(TextField tf, String campo) {
        try {
            return Double.parseDouble(tf.getText().trim());
        } catch (Exception ex) {
            showInfo("Validación", campo + " debe ser numérico (usa punto decimal).");
            return null;
        }
    }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(title);
        a.setContentText(msg);
        a.showAndWait();
    }
}

