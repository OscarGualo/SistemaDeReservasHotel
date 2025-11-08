package org.example.sistemareservas.Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private String direccion;
    private String telefono;
    private List<Reserva> reservas = new ArrayList<>(); //RELACION BIDIRECCIONAL * CON LA CLASE RESERVA

    public Cliente(String cedula, String nombres, String apellidos, String email, String contrasena) {
        super(cedula, nombres, apellidos, email, contrasena);
    }
    @Override
    public boolean iniciarSesion(String pass, String username){

        if(pass.equals("1") && username.equals("c")){
            return true;
        }
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
    public Habitacion seleccionarHabitaciones(Habitacion habitacion){
        return habitacion;
    }
    public void realizarReserva(Paquete paquete, LocalDate entrada, LocalDate salida) {
        String codigo = "R" + (reservas.size() + 1);
        Reserva nueva = new Reserva(codigo, entrada, salida, paquete,this);
        reservas.add(nueva);
        System.out.println("Reserva creada: " + nueva);
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
