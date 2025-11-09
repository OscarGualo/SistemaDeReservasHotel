package org.example.sistemareservas.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private  List<Habitacion> habitaciones;
    private  List<Servicio> servicios;

    public Hotel() {
        habitaciones = new ArrayList<>();
        servicios = new ArrayList<>();

    }

    public  void inicializarDatos() {
        // 🔹 Habitaciones predefinidas
        habitaciones.add(new Habitacion(101, TipoHabitacion.SIMPLE,EstadoHabitacion.DISPONIBLE,10,5,3));
        habitaciones.add(new Habitacion(102,  TipoHabitacion.DOBLE,EstadoHabitacion.DISPONIBLE,20,10,1));
        habitaciones.add(new Habitacion(103, TipoHabitacion.SUIT,EstadoHabitacion.DISPONIBLE,30,4,2));


        // 🔹 Servicios predefinidos
        servicios.add(new Servicio("Piscina ","Piscina x", 10,"x"," Martes de 11 a 1" ));
        servicios.add(new Servicio("Spa", "SPA X",5,"Y","LUENS 11"));
        servicios.add(new Servicio("Transporte al aeropuerto", "DRIVER", 2.5,"DRive","SIEMPRE DISPONIBLE"));
    }

    public  List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public  List<Servicio> getServicios() {
        return servicios;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "habitaciones=" + habitaciones +
                ", servicios=" + servicios +
                '}';
    }
}
