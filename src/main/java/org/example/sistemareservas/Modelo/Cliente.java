package org.example.sistemareservas.Modelo;

public class Cliente extends Usuario {
    private String direccion;
    private String telefono;
    //private List<Reserva> reservas = new ArrayList<>(); //RELACION BIDIRECCIONAL * CON LA CLASE RESERVA

    public Cliente(String cedula, String nombres, String apellidos, String email, String contrasena) {
        super(cedula, nombres, apellidos, email, contrasena);
    }
    @Override
    public boolean iniciarSesion(String pass, String username){

        if(pass.equals("1") && username.equals("c")){
            return true;
        }
            return false;


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
