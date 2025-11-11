package org.example.sistemareservas.Modelo;

public class Pago {
    private double monto;
    private String metodo; // e.g., Tarjeta, Efectivo
    private boolean pagado;

    public Pago() {
        this.monto = 0.0;
        this.metodo = "";
        this.pagado = false;
    }

    public Pago(double monto, String metodo, boolean pagado) {
        this.monto = monto;
        this.metodo = metodo;
        this.pagado = pagado;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }
}
