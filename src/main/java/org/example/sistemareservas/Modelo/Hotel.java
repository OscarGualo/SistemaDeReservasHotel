package org.example.sistemareservas.Modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Hotel {

    // --- Listas principales ---
    private static ObservableList<Habitacion> habitaciones = FXCollections.observableArrayList();
    private static ObservableList<Servicio> servicios = FXCollections.observableArrayList();
    private static ObservableList<Paquete> paquetes = FXCollections.observableArrayList();
    private static ObservableList<Cliente> clientes = FXCollections.observableArrayList();
    private static ObservableList<Reserva> reservas = FXCollections.observableArrayList();

    // --- Selecciones actuales ---
    private static Cliente clienteSeleccionado;
    private static Reserva reservaSeleccionada;
    private static Paquete paqueteSeleccionado;

    // --- Constructor ---
    public Hotel() {
        habitaciones = FXCollections.observableArrayList();
        servicios = FXCollections.observableArrayList();
        paquetes = FXCollections.observableArrayList();
        clientes = FXCollections.observableArrayList();
        reservas = FXCollections.observableArrayList();
        inicializarDatos();
    }

    // --- Inicialización de datos de ejemplo ---
    private void inicializarDatos() {
        habitaciones.add(new Habitacion(101, 1, 30.0, 5, EstadoHabitacion.DISPONIBLE, TipoHabitacion.SUIT));
        habitaciones.add(new Habitacion(102, 2, 40, 6, EstadoHabitacion.DISPONIBLE, TipoHabitacion.SIMPLE));
        habitaciones.add(new Habitacion(103, 3, 50, 7, EstadoHabitacion.DISPONIBLE, TipoHabitacion.DOBLE));
        habitaciones.add(new Habitacion(104, 1, 30, 8, EstadoHabitacion.OCUPADA, TipoHabitacion.DOBLE));

        servicios.add(new Servicio(1, "Piscina", "Piscina x", 10, true));
        servicios.add(new Servicio(2, "Spa", "SPA X", 5, true));
        servicios.add(new Servicio(3, "Transporte al aeropuerto", "DRIVER", 2.5, true));

        clientes.add(new Cliente("3123", "oscar", "gualotuña", "@gmail.com", "dasd", "123123", "c"));
    }
    public void asignarClienteAReserva(Cliente cliente, Reserva reserva) {
        if (cliente != null && reserva != null) {
            reserva.setCliente(cliente);
        }
    }
    // --- Getters y setters principales ---
    public ObservableList<Habitacion> getHabitaciones() { return habitaciones; }
    public ObservableList<Servicio> getServicios() { return servicios; }
    public ObservableList<Paquete> getPaquetes() { return paquetes; }
    public ObservableList<Cliente> getClientes() { return clientes; }
    public ObservableList<Reserva> getReservas() { return reservas; }

    public Cliente getClienteSeleccionado() { return clienteSeleccionado; }
    public void setClienteSeleccionado(Cliente cliente) { clienteSeleccionado = cliente; }

    public Reserva getReservaSeleccionada() { return reservaSeleccionada; }
    public void setReservaSeleccionada(Reserva reserva) { reservaSeleccionada = reserva; }

    public Paquete getPaqueteSeleccionado() { return paqueteSeleccionado; }
    public void setPaqueteSeleccionado(Paquete paqueteSeleccionado) { this.paqueteSeleccionado = paqueteSeleccionado; }

    // --- Métodos funcionales ---
    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void agregarHabitacion(Habitacion h) {
        habitaciones.add(h);
    }

    public void eliminarHabitacion(int numero) {
        habitaciones.removeIf(h -> h.getNumero() == numero);
    }

    public boolean eliminarHabitacion(Habitacion habitacion) {
        return habitaciones.remove(habitacion);
    }

    public void modificarHabitacion(Habitacion habitacionModificada) {
        for (int i = 0; i < habitaciones.size(); i++) {
            if (habitaciones.get(i).getNumero() == habitacionModificada.getNumero()) {
                habitaciones.set(i, habitacionModificada);
                break;
            }
        }
    }

    public void modificarDatosHabitacion(Habitacion habitacionaModificar, int nuevoNumero, int nuevoPiso,
                                         double nuevoPrecio, int nuevaCapacidad,
                                         EstadoHabitacion estadoNuevo, TipoHabitacion tipoNuevo) {
        habitacionaModificar.setNumero(nuevoNumero);
        habitacionaModificar.setPiso(nuevoPiso);
        habitacionaModificar.setPrecioPorNoche(nuevoPrecio);
        habitacionaModificar.setEstado(estadoNuevo);
        habitacionaModificar.setTipo(tipoNuevo);
        habitacionaModificar.setCapacidadPersonas(nuevaCapacidad);
    }

    public ObservableList<Habitacion> buscarHabitaciones(String criterio, String valorBusqueda) {
        if (criterio == null) throw new IllegalArgumentException("Seleccione un criterio de búsqueda.");
        if (valorBusqueda == null || valorBusqueda.trim().isEmpty() || "SinFiltro".equalsIgnoreCase(criterio)) {
            return getHabitaciones();
        }

        ObservableList<Habitacion> filtradas = FXCollections.observableArrayList();
        switch (criterio) {
            case "Numero":
                try {
                    int numero = Integer.parseInt(valorBusqueda.trim());
                    for (Habitacion h : getHabitaciones()) {
                        if (h.getNumero() == numero) filtradas.add(h);
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Ingrese un número válido para la búsqueda por número.");
                }
                break;
            case "Estado":
                for (Habitacion h : getHabitaciones()) {
                    if (h.getEstado() != null && h.getEstado().toString().equalsIgnoreCase(valorBusqueda.trim())) {
                        filtradas.add(h);
                    }
                }
                break;
            case "Tipo":
                for (Habitacion h : getHabitaciones()) {
                    if (h.getTipo() != null && h.getTipo().toString().equalsIgnoreCase(valorBusqueda.trim())) {
                        filtradas.add(h);
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Criterio de búsqueda inválido.");
        }

        return filtradas;
    }

    public ObservableList<Servicio> buscarServiciosDisponibles() {
        ObservableList<Servicio> disponibles = FXCollections.observableArrayList();
        for (Servicio s : servicios) {
            if (s.isEstado()) {
                disponibles.add(s);
            }
        }
        return disponibles;
    }

    // --- Asociación entre reserva y paquete ---
    public void asociarPaqueteAReserva(Paquete paquete) {
        if (reservaSeleccionada != null && paquete != null) {
            reservaSeleccionada.setPaquete(paquete);
            setPaqueteSeleccionado(paquete);
            if (!paquetes.contains(paquete)) {
                paquetes.add(paquete);
            }
        }
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "habitaciones=" + habitaciones +
                ", servicios=" + servicios +
                ", paquetes=" + paquetes +
                '}';
    }
}
