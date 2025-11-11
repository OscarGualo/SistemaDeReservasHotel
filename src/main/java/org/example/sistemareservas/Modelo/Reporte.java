package org.example.sistemareservas.Modelo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

public class Reporte {

    // Colores personalizados
    private static final BaseColor COLOR_HEADER = new BaseColor(14, 94, 134);
    private static final BaseColor COLOR_WHITE = BaseColor.WHITE;
    private static final BaseColor COLOR_LIGHT_GRAY = new BaseColor(240, 240, 240);

    /**
     * Genera un reporte PDF desde un ObservableList
     * @param lista ObservableList con los datos
     * @param tipoReporte Nombre del reporte (ej: "Productos", "Habitaciones")
     * @param nombreHotel Nombre del hotel
     * @param columnas Array con los nombres de las columnas a mostrar
     * @param campos Array con los nombres de los atributos/getters de la clase
     */
    public static <T> void Reporte(
            ObservableList<T> lista,
            String tipoReporte,
            String nombreHotel,
            String[] columnas,
            String[] campos
    ) {
        try {
            // Validaciones
            if (lista == null || lista.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "No hay datos para generar el reporte",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (columnas.length != campos.length) {
                throw new IllegalArgumentException(
                        "El número de columnas debe coincidir con el número de campos"
                );
            }

            // Crear nombre de archivo con timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String nombreArchivo = "Reporte_" + tipoReporte + "_" + timestamp + ".pdf";

            // Crear documento PDF
            Document documento = new Document(PageSize.A4);
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));

            documento.open();

            // Agregar encabezado
            agregarEncabezado(documento, nombreHotel, tipoReporte);

            // Agregar tabla con datos
            agregarTabla(documento, lista, columnas, campos);

            // Agregar pie de página
            agregarPiePagina(documento);

            documento.close();

            // Mostrar mensaje de éxito
            int opcion = JOptionPane.showConfirmDialog(
                    null,
                    "Reporte generado exitosamente: " + nombreArchivo + "\n¿Desea abrirlo?",
                    "Reporte Generado",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE
            );

            if (opcion == JOptionPane.YES_OPTION) {
                abrirPDF(nombreArchivo);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al generar el reporte: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    /**
     * Agrega el encabezado al PDF
     */
    private static void agregarEncabezado(Document documento, String nombreHotel, String tipoReporte)
            throws DocumentException {

        PdfPTable tablaHeader = new PdfPTable(2);
        tablaHeader.setWidthPercentage(100);
        tablaHeader.setSpacingAfter(20f);

        try {
            tablaHeader.setWidths(new float[]{60, 40});
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        // Celda izquierda - Nombre del Hotel
        PdfPCell celdaNombre = new PdfPCell();
        celdaNombre.setBorder(Rectangle.NO_BORDER);
        celdaNombre.setBackgroundColor(COLOR_HEADER);
        celdaNombre.setPadding(15);

        Font fuenteHotel = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 28, COLOR_WHITE);
        Paragraph nombreParrafo = new Paragraph(nombreHotel, fuenteHotel);
        celdaNombre.addElement(nombreParrafo);

        // Celda derecha - Tipo de Reporte
        PdfPCell celdaTipoReporte = new PdfPCell();
        celdaTipoReporte.setBorder(Rectangle.NO_BORDER);
        celdaTipoReporte.setBackgroundColor(COLOR_HEADER);
        celdaTipoReporte.setPadding(15);
        celdaTipoReporte.setHorizontalAlignment(Element.ALIGN_RIGHT);
        celdaTipoReporte.setVerticalAlignment(Element.ALIGN_MIDDLE);

        Font fuenteTipo = FontFactory.getFont(FontFactory.HELVETICA, 16, COLOR_WHITE);
        Paragraph tipoParrafo = new Paragraph("Listado de " + tipoReporte, fuenteTipo);
        celdaTipoReporte.addElement(tipoParrafo);

        tablaHeader.addCell(celdaNombre);
        tablaHeader.addCell(celdaTipoReporte);

        documento.add(tablaHeader);
    }

    /**
     * Agrega la tabla de datos al PDF usando reflexión
     */
    private static <T> void agregarTabla(
            Document documento,
            ObservableList<T> lista,
            String[] columnas,
            String[] campos
    ) throws DocumentException {

        int numColumnas = columnas.length;

        // Crear tabla PDF
        PdfPTable tablaPDF = new PdfPTable(numColumnas);
        tablaPDF.setWidthPercentage(100);
        tablaPDF.setSpacingBefore(10f);
        tablaPDF.setSpacingAfter(10f);

        // Fuentes
        Font fuenteHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, COLOR_WHITE);
        Font fuenteDatos = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

        // Agregar headers (nombres de columnas)
        for (String columna : columnas) {
            PdfPCell celda = new PdfPCell(new Phrase(columna, fuenteHeader));
            celda.setBackgroundColor(COLOR_HEADER);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.setPadding(8);
            celda.setBorderWidth(1);
            tablaPDF.addCell(celda);
        }

        // Agregar datos usando reflexión
        int filaIndex = 0;
        for (T objeto : lista) {
            for (String campo : campos) {
                String valor = obtenerValorCampo(objeto, campo);

                PdfPCell celda = new PdfPCell(new Phrase(valor, fuenteDatos));
                celda.setPadding(6);
                celda.setBorderWidth(0.5f);
                celda.setBorderColor(BaseColor.LIGHT_GRAY);

                // Alternar color de filas
                if (filaIndex % 2 == 0) {
                    celda.setBackgroundColor(COLOR_WHITE);
                } else {
                    celda.setBackgroundColor(COLOR_LIGHT_GRAY);
                }

                // Alinear columnas numéricas a la derecha
                if (esNumerico(valor)) {
                    celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
                } else {
                    celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                }

                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tablaPDF.addCell(celda);
            }
            filaIndex++;
        }

        documento.add(tablaPDF);
    }

    /**
     * Obtiene el valor de un campo usando reflexión
     * Soporta tanto atributos públicos como getters
     */
    private static <T> String obtenerValorCampo(T objeto, String nombreCampo) {
        try {
            Class<?> clase = objeto.getClass();

            // Primero intentar con getter (getXxx)
            String nombreGetter = "get" + nombreCampo.substring(0, 1).toUpperCase()
                    + nombreCampo.substring(1);
            try {
                Method getter = clase.getMethod(nombreGetter);
                Object valor = getter.invoke(objeto);
                return valor != null ? formatearValor(valor) : "";
            } catch (NoSuchMethodException e) {
                // Si no existe el getter, intentar con el campo directo
            }

            // Intentar con atributo directo
            Field campo = clase.getDeclaredField(nombreCampo);
            campo.setAccessible(true);
            Object valor = campo.get(objeto);
            return valor != null ? formatearValor(valor) : "";

        } catch (Exception e) {
            System.err.println("Error al obtener campo '" + nombreCampo + "': " + e.getMessage());
            return "N/A";
        }
    }

    /**
     * Formatea el valor según su tipo
     */
    private static String formatearValor(Object valor) {
        if (valor instanceof Double || valor instanceof Float) {
            return String.format("%.2f", valor);
        } else if (valor instanceof Date) {
            return new SimpleDateFormat("dd/MM/yyyy").format(valor);
        }
        return valor.toString();
    }

    /**
     * Verifica si un texto es numérico
     */
    private static boolean esNumerico(String texto) {
        if (texto == null || texto.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Agrega pie de página con fecha y hora
     */
    private static void agregarPiePagina(Document documento) throws DocumentException {
        Font fuentePie = FontFactory.getFont(FontFactory.HELVETICA, 9, BaseColor.GRAY);
        String fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        Paragraph pie = new Paragraph("Generado el: " + fechaHora, fuentePie);
        pie.setAlignment(Element.ALIGN_RIGHT);
        pie.setSpacingBefore(20f);
        documento.add(pie);
    }

    /**
     * Abre el PDF generado
     */
    private static void abrirPDF(String rutaArchivo) {
        try {
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop.getDesktop().open(new java.io.File(rutaArchivo));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "No se pudo abrir el archivo automáticamente.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}