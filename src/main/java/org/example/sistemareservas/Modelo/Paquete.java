package org.example.sistemareservas.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Paquete implements cobrable{
    private String nombre;
    private String descripcion;
    private List<Servicio> serviciosIncluidos; //REALCION ENTRE PAQUETE Y SERVICIO 1 A N
    private List<Habitacion> habitaciones; //RELACION ENTRE PAQUETE Y HABITACION 1 A N
    private List<cobrable> elementos = new ArrayList<>();
    public Paquete(String nombre, String descripcion,List<Habitacion> habitaciones,List<Servicio> serviciosIncluidos){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.habitaciones = habitaciones;
        this.serviciosIncluidos = serviciosIncluidos;

    }
    public Paquete(String nombre, String descripcion, double precioBase) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.serviciosIncluidos = new ArrayList<>();
        this.habitaciones = new ArrayList<>();
    }

    public void setServiciosIncluidos(List<Servicio> serviciosIncluidos) {
        this.serviciosIncluidos = serviciosIncluidos;
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


    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }


    public List<Servicio> getServiciosIncluidos() {
        return serviciosIncluidos;
    }



    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    @Override
    public String toString() {
        return "Paquete{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", serviciosIncluidos=" + serviciosIncluidos +
                ", habitaciones=" + habitaciones +
                '}';
    }
    public double calcularSubtotalHabitaciones() {
        double subtotal = 0;
        for (Habitacion h : habitaciones) {
            subtotal += h.calcularCosto();
        }
        return subtotal;
    }
    public double calcularSubtotalServicios() {
        double subtotal = 0;
        for (Servicio s : serviciosIncluidos) {
            subtotal += s.calcularCosto();
        }
        return subtotal;
    }
    @Override
    public double calcularCosto() {
        double total = 0;
        for (cobrable c : elementos) {
            total += c.calcularCosto();  // cada elemento sabe su costo
        }
        return total;
    }
    }

