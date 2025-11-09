package org.example.sistemareservas.Modelo;

public class HotelData {
    private static Hotel hotel;

    // Inicializa los datos solo una vez
    static {
        hotel = new Hotel();
        hotel.inicializarDatos();
    }

    public static Hotel getHotel() {
        return hotel;
    }
}