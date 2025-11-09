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
}
