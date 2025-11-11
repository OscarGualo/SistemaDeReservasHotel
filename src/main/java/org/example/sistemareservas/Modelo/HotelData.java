package org.example.sistemareservas.Modelo;

public class HotelData {
    private static final Hotel hotel = new Hotel();

    private HotelData() {
        // Evita instanciar esta clase
    }

    public static Hotel getHotel() {
        return hotel;
    }
}