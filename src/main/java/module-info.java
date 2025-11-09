module org.example.sistemareservas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens org.example.sistemareservas to javafx.fxml;
    exports org.example.sistemareservas;
    exports org.example.sistemareservas.Controlador;
    opens org.example.sistemareservas.Controlador to javafx.fxml;
    exports org.example.sistemareservas.Modelo;
    opens org.example.sistemareservas.Modelo to javafx.fxml;
}