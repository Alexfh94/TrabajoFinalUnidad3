package com.example.trabajofinalunidad3;

public class Comida {
    private String titulo;
    private int imagen; // Ruta o URL de la imagen
    private String descripcion;
    private double precio;
    private int imgBoton;
    private int oferta;



    // Constructor
    public Comida(String titulo, int imagen, String descripcion, double precio, int imgBoton, int oferta) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imgBoton=imgBoton;
        this.oferta=oferta;

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
        return precio;
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

    public int getOferta() {
        return oferta;
    }

    public void setOferta(int oferta) {
        this.oferta = oferta;
    }

    // Método toString para mostrar la información del cómic
    @Override
    public String toString() {
        return "Comida [Título: " + titulo + ", Imagen: " + imagen + ", Descripcion: " + descripcion + "]";
    }
}
