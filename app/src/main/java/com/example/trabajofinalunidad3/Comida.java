package com.example.trabajofinalunidad3;

public class Comida {
    private String titulo;
    private int imagen; // Ruta o URL de la imagen
    private String descripcion;
    private double precio;
    private int imgOferta;
    private TipoComida tipo;

    // Enum para representar los tipos de cómics
    public enum TipoComida {
        CARNE,
        PESCADO,
        VEGETALES,
        OTROS
    }


    // Constructor
    public Comida(String titulo, int imagen, String descripcion, double precio, int imgOferta, TipoComida tipo) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imgOferta=imgOferta;
        this.tipo=tipo;
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        double finalPrecio;

        if (imgOferta == 1) {
            finalPrecio = 0.9 * precio;
        } else if (imgOferta == 2) {
            finalPrecio = 0.75 * precio;
        } else {
            finalPrecio = precio;
        }

        // Redondear a 2 decimales
        return Math.round(finalPrecio * 100.0) / 100.0;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getImgOferta() {
        return imgOferta;
    }

    public void setImgOferta(int imgOferta) {
        this.imgOferta = imgOferta;
    }

    public TipoComida getTipo() {
        return tipo;
    }

    public void setTipo(TipoComida tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Comida{" +
                "titulo='" + titulo + '\'' +
                ", imagen=" + imagen +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", imgOferta=" + imgOferta +
                '}';
    }
}
