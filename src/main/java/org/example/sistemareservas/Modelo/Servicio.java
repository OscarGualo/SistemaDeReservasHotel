package org.example.sistemareservas.Modelo;

public class Servicio {
    private String nombre;
    private String descripcion;
    private double precio;
    private String categoria;
    private String horario;

    public Servicio(String nombre, String descripcion, double precio, String categoria, String horario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                ", horario='" + horario + '\'' +
                '}';
    }
}
