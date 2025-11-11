package org.example.sistemareservas.Modelo;

public class Servicio {
    private int codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean estado;

    public Servicio(int codigo, String nombre, String descripcion, double precio, boolean estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
       this.codigo = codigo;
       this.estado = estado;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", estado=" + estado +
                '}';
    }
}
