package org.example.sistemareservas.Modelo;

public abstract class Usuario {
    private String cedula;
    private String nombres;
    private String apellidos;
    private String email;
    private String contrasena;

    public Usuario(String cedula, String nombres, String apellidos, String email, String contrasena) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.contrasena = contrasena;
    }

    public abstract boolean iniciarSesion(String pass, String username);
    public abstract void cerrarSesion();
    public abstract void modificarDatos();

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }
}
