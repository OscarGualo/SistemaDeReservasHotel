package org.example.sistemareservas.Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private String codigoReserva;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private EstadoReserva estado;
    private List<Habitacion> habitaciones; // relacion de agreagacion 1 a 1 o 1 a n
    private Paquete paquete; // Relacion de agregacion 1 a n
    private Cliente cliente; // Relacion de asociacion 1 a n
    //private Pago pago;
    private List<cobrable> cobrables = new ArrayList<>();
    public Reserva(String codigoReserva, LocalDate fechaEntrada, LocalDate fechaSalida, Paquete paquete,Cliente cliente) {
        this.codigoReserva = codigoReserva;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.estado = EstadoReserva.PENDIENTE;
        this.paquete = paquete;
        this.cliente = cliente;
       // this.pago = new Pago();
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }
    public void confirmarReserva() {
        this.estado = EstadoReserva.CONFIRMADA;
    }

    public void cancelarReserva() {
        this.estado = EstadoReserva.CANCELADA;
    }
    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void agregarCobrable(cobrable c) {
        cobrables.add(c);
    }
    public double calcularTotal() {
        double total = 0;
        for (cobrable c : cobrables) {
            total += c.calcularCosto();
        }
        return total;
    }

    @Override
    public String toString() {
    return "Reserva " + codigoReserva + " - Cliente: " + cliente.getNombres() +
                " - Paquete: " + paquete.getNombre() +
                " "+paquete.toString() +
                " - Estado: " + estado;
    }
}
