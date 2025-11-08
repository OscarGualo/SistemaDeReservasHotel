package org.example.sistemareservas.Modelo;

import java.time.LocalDate;

public class Promocion {
    private String idPromocion;
    private String descripcion;
    private double descuento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean estaActiva;

    public Promocion(String idPromocion, String descripcion, double descuento, LocalDate fechaInicio, LocalDate fechaFin, boolean estaActiva) {
        this.idPromocion = idPromocion;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estaActiva = estaActiva;

    }

    @Override
    public String toString() {
        return "Promocion{" +
                "idPromocion='" + idPromocion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", descuento=" + descuento +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", estaActiva=" + estaActiva +
                '}';
    }
}
