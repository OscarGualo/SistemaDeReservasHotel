package org.example;

public class Recepcionista extends Usuario{
    private String codigoEmpleado;

    public Recepcionista(String cedula, String nombres, String apellidos, String email, String contrasena) {
        super(cedula, nombres, apellidos, email, contrasena);
    }

    @Override
    public void iniciarSesion() {
        System.out.println("Iniciando Sesion cliente-..... ");
    }

    @Override
    public void cerrarSesion() {
        System.out.println("Cerrando Sesion cliente-..... ");
    }

    @Override
    public void modificarDatos() {
        System.out.println("Modificando Sesion cliente-..... ");
    }
}
