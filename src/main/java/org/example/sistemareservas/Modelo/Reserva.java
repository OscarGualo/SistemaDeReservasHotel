package org.example.sistemareservas.Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private String codigoReserva;
    private String fechaEntrada;
    private String fechaSalida;
    private EstadoReserva estado;
    private Paquete paquete; // Relacion de agregacion 1 a n
    private Cliente cliente; // Relacion de asociacion 1 a n
    //private Pago pago;
    private List<cobrable> cobrables = new ArrayList<>();

    public Reserva(String codigoReserva,String fechaEntrada, String fechaSalida, EstadoReserva estado,Paquete paquete,Cliente cliente) {
        this.codigoReserva = codigoReserva;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.estado =estado;
        this.paquete = paquete;
        this.cliente = cliente;
       // this.pago = new Pago();
    }
    public void asignarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        this.cliente = cliente;
    }
    public void asignarPaquete(Paquete paquete) {
        if (paquete == null) {
            throw new IllegalArgumentException("El paquete no puede ser nulo");
        }
        this.paquete = paquete;
        calcularTotal();
    }
    public boolean estaCompleta() {
        return cliente != null && paquete != null &&
                fechaEntrada != null && fechaSalida != null;
    }
    public void confirmar() {
        if (!estaCompleta()) {
            throw new IllegalStateException("La reserva no está completa");
        }
        this.estado = EstadoReserva.CONFIRMADA;
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

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public EstadoReserva getEstado() {
        return estado;
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
