package org.example.sistemareservas.Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private String direccion;
    private String telefono;
    private List<Reserva> reservas = new ArrayList<>(); //RELACION BIDIRECCIONAL * CON LA CLASE RESERVA

    public Cliente(String cedula, String nombres, String apellidos, String email,String direccion, String telefono,String contrasena) {
        super(cedula, nombres, apellidos, email, contrasena);
        this.direccion = direccion;
        this.telefono = telefono;
    }


    @Override
    public boolean iniciarSesion(String username, String password) {

        if(username.equals("1") && password.equals("c")){
            return true;
        }
            return false;


    }

    @Override
    public void cerrarSesion() {

        System.out.println("Cerrando Sesion cliente-..... ");
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public void modificarDatos() {
        System.out.println("Modificando Sesion cliente-..... ");
    }
    public Habitacion seleccionarHabitaciones(Habitacion habitacion){
        return habitacion;
    }

    public void cancelarReserva(String codigoReserva) {
        for (Reserva r : reservas) {
            if (r.getCodigoReserva().equals(codigoReserva)) {
                r.cancelarReserva();
                System.out.println("Reserva cancelada: " + codigoReserva);
                return;
            }
        }
        System.out.println("No se encontró la reserva.");
    }
    public void verReservasActivas(){
        for (Reserva r : reservas) {
            System.out.println("Reserva activa: " + r.toString());
        }
    }

}
