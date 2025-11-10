package org.example.sistemareservas.Modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private static ObservableList<Habitacion> habitaciones = FXCollections.observableArrayList();
    private static ObservableList<Servicio> servicios = FXCollections.observableArrayList();
    public Hotel() {
        habitaciones = FXCollections.observableArrayList();
        servicios = FXCollections.observableArrayList();
        habitaciones.add(new Habitacion(101, 1,30,5,EstadoHabitacion.DISPONIBLE,TipoHabitacion.SUIT));
        habitaciones.add(new Habitacion(102,  2,40,6,EstadoHabitacion.DISPONIBLE,TipoHabitacion.SIMPLE));
        habitaciones.add(new Habitacion(103, 3,50,7,EstadoHabitacion.DISPONIBLE,TipoHabitacion.DOBLE));


        // 🔹 Servicios predefinidos
        servicios.add(new Servicio(1,"Piscina ","Piscina x", 10,true ));
        servicios.add(new Servicio(2,"Spa", "SPA X",5,true));
        servicios.add(new Servicio(3,"Transporte al aeropuerto", "DRIVER", 2.5,true));
    }

    public  void inicializarDatos() {
        // 🔹 Habitaciones predefinidas
        habitaciones.add(new Habitacion(101, 1,30.0,5,EstadoHabitacion.DISPONIBLE,TipoHabitacion.SUIT));
        habitaciones.add(new Habitacion(102,  2,40,6,EstadoHabitacion.DISPONIBLE,TipoHabitacion.SIMPLE));
        habitaciones.add(new Habitacion(103, 3,50,7,EstadoHabitacion.DISPONIBLE,TipoHabitacion.DOBLE));


        // 🔹 Servicios predefinidos
        servicios.add(new Servicio(1,"Piscina ","Piscina x", 10,true ));
        servicios.add(new Servicio(2,"Spa", "SPA X",5,true));
        servicios.add(new Servicio(3,"Transporte al aeropuerto", "DRIVER", 2.5,true));
    }

    // --- Métodos de acceso ---
    public ObservableList<Habitacion> getHabitaciones() { return habitaciones; }
    public ObservableList<Servicio> getServicios() { return servicios; }


    @Override
    public String toString() {
        return "Hotel{" +
                "habitaciones=" + habitaciones +
                ", servicios=" + servicios +
                '}';
    }

    public void agregarHabitacion(Habitacion h) {
        habitaciones.add(h);
    }

    public void eliminarHabitacion(int numero) {
        habitaciones.removeIf(h -> h.getNumero() == numero);
    }

    public void modificarHabitacion(Habitacion habitacionModificada) {
        for (int i = 0; i < habitaciones.size(); i++) {
            if (habitaciones.get(i).getNumero() == habitacionModificada.getNumero()) {
                habitaciones.set(i, habitacionModificada);
                break;
            }
        }
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
    public boolean eliminarHabitacion(Habitacion habitacion) {
        return habitaciones.remove(habitacion);
    }
    public void modificarDatosHabitacion(Habitacion habitacionaModificar , int nuevoNumero, int nuevoPiso, double nuevoPrecio, int nuevaCapacidad, EstadoHabitacion estadoNuevo, TipoHabitacion tipoNuevo) {
            habitacionaModificar.setNumero(nuevoNumero);
            habitacionaModificar.setPiso(nuevoPiso);
            habitacionaModificar.setPrecioPorNoche(nuevoPrecio);
            habitacionaModificar.setEstado(estadoNuevo);
            habitacionaModificar.setTipo(tipoNuevo);
            habitacionaModificar.setCapacidadPersonas(nuevaCapacidad);
    }

}
