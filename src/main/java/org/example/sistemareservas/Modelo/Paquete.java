package org.example.sistemareservas.Modelo;

import org.example.sistemareservas.Modelo.Promocion;

import java.util.ArrayList;
import java.util.List;

public class Paquete {
    private String nombre;
    private String descripcion;
    private double precioBase;
    private List<Servicio> serviciosIncluidos; //REALCION ENTRE PAQUETE Y SERVICIO 1 A N
    private List<Promocion> promocionesAplicadas; //RELACION ENTRE PAQUETE Y PROMOCION 1 A N ACA SE INCLUYE COMPOSICION ENVEZ DE AGREGACION COMO ESTA EN EL DIAGGRAMA
    private List<Habitacion> habitaciones; //RELACION ENTRE PAQUETE Y HABITACION 1 A N

    public Paquete(String nombre, String descripcion,List<Habitacion> habitaciones,List<Promocion> promocionesAplicadas,List<Servicio> serviciosIncluidos){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.habitaciones = habitaciones;
        this.serviciosIncluidos = serviciosIncluidos;
        this.promocionesAplicadas = promocionesAplicadas;
    }
    public Paquete(String nombre, String descripcion, double precioBase) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.serviciosIncluidos = new ArrayList<>();
        this.promocionesAplicadas = new ArrayList<>();
        this.habitaciones = new ArrayList<>();
    }

    public void setServiciosIncluidos(List<Servicio> serviciosIncluidos) {
        this.serviciosIncluidos = serviciosIncluidos;
    }

    public void setPromocionesAplicadas(List<Promocion> promocionesAplicadas) {
        this.promocionesAplicadas = promocionesAplicadas;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void agregarHabitacion(Habitacion habitacion){
        this.habitaciones.add(habitacion);
    }
    public void agregarServicio(Servicio servicio){
        this.serviciosIncluidos.add(servicio);
    }
    public void agregarPromocion(Promocion promocion){
        this.promocionesAplicadas.add(promocion);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public List<Servicio> getServiciosIncluidos() {
        return serviciosIncluidos;
    }

    public List<Promocion> getPromocionesAplicadas() {
        return promocionesAplicadas;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    @Override
    public String toString() {
        return "Paquete{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioBase=" + precioBase +
                ", serviciosIncluidos=" + serviciosIncluidos +
                ", promocionesAplicadas=" + promocionesAplicadas +
                ", habitaciones=" + habitaciones +
                '}';
    }
}
