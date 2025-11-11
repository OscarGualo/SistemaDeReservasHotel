package org.example.sistemareservas.Modelo;

public class Servicio implements  cobrable{
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

    // === GETTERS / SETTERS ===
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

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

    @Override
    public double calcularCosto() {
        return precio;
    }
}
