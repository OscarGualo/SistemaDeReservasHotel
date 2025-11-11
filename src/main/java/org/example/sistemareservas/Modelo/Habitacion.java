package org.example.sistemareservas.Modelo;

public class Habitacion {
    private int numero;
    private int piso;
    private int capacidadPersonas;
    private double precioPorNoche;
    private EstadoHabitacion estado; //USO 1 A 1
    private TipoHabitacion tipo; //USO 1 A 1
    public Habitacion(){

    }

    public Habitacion(int numero, int piso, double precioPorNoche,int capacidadPersonas,EstadoHabitacion estado,TipoHabitacion tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.estado = estado;
        this.precioPorNoche = precioPorNoche;
        this.capacidadPersonas = capacidadPersonas;
        this.piso = piso;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "numero=" + numero +
                ", piso=" + piso +
                ", capacidadPersonas=" + capacidadPersonas +
                ", precioPorNoche=" + precioPorNoche +
                ", estado=" + estado +
                ", tipo=" + tipo +
                '}';
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getCapacidadPersonas() {
        return capacidadPersonas;
    }

    public void setCapacidadPersonas(int capacidadPersonas) {
        this.capacidadPersonas = capacidadPersonas;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    public EstadoHabitacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoHabitacion estado) {
        this.estado = estado;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }
}
