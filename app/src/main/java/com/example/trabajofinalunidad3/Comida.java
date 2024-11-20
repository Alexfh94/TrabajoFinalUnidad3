package com.example.trabajofinalunidad3;

public class Comida {
    private String titulo;
    private int imagen; // Ruta o URL de la imagen
    private String descripcion;
    private double precio;
    private int imgBoton;
    private int imgOferta;



    // Constructor
    public Comida(String titulo, int imagen, String descripcion, double precio, int imgBoton, int imgOferta) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imgBoton=imgBoton;
        this.imgOferta=imgOferta;

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

        if (imgOferta == 2131165423) {
            finalPrecio = 0.9 * precio;
        } else if (imgOferta == 2131165424) {
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

    public int getImgBoton() {
        return imgBoton;
    }

    public void setImgBoton(int imgBoton) {
        this.imgBoton = imgBoton;
    }

    public int getImgOferta() {
        return imgOferta;
    }

    public void setImgOferta(int imgOferta) {
        this.imgOferta = imgOferta;
    }

    @Override
    public String toString() {
        return "Comida{" +
                "titulo='" + titulo + '\'' +
                ", imagen=" + imagen +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", imgBoton=" + imgBoton +
                ", imgOferta=" + imgOferta +
                '}';
    }
}
