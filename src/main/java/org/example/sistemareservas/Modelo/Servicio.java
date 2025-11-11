package org.example.sistemareservas.Modelo;

public class Servicio implements cobrable {
    private int codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private EstadoServicio estado;
    public Servicio(int codigo, String nombre, String descripcion, double precio, EstadoServicio estado) {
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
    public void cambiarEstado(EstadoServicio estado) {
        this.estado = estado;
    }
    @Override
    public double calcularCosto() {
        return precio;
    }
}
