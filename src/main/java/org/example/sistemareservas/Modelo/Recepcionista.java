package org.example.sistemareservas.Modelo;

import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import java.util.ArrayList;
import java.util.List;
//USO O DEPENDENCIA CON CON HOTEL
public class Recepcionista extends Usuario{
    private String codigoEmpleado;

    public Recepcionista(String cedula, String nombres, String apellidos, String email, String contrasena) {
        super(cedula, nombres, apellidos, email, contrasena);
    }

    @Override
    public boolean iniciarSesion(String username, String password) {
        return false;
    }

    @Override
    public void cerrarSesion() {
        System.out.println("Cerrando Sesion cliente-..... ");
    }

    @Override
    public void modificarDatos() {
        System.out.println("Modificando Sesion cliente-..... ");
    }

    public void registrarHabitacion(Hotel hotel, Habitacion nueva) {
        hotel.agregarHabitacion(nueva);
    }


    public ObservableList<Habitacion> buscarHabitaciones(String criterio, String valor,Hotel hotel) {
        return hotel.buscarHabitaciones(criterio, valor);
    }
    public boolean eliminarHabitacion(Hotel hotel, Habitacion habitacion) {
        return hotel.eliminarHabitacion(habitacion);
    }
    public void modificarDatosHabitacion(Hotel hotel, Habitacion modificada, int nuevoNumero, int nuevoPiso ,double nuevoPrecio, int nuevaCapacidad, EstadoHabitacion estadoNuevo, TipoHabitacion tipoNuevo) {
        hotel.modificarDatosHabitacion(modificada,nuevoNumero,nuevoPiso,nuevoPrecio,nuevaCapacidad,estadoNuevo,tipoNuevo);
    }
}
