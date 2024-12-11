package com.example.trabajofinalunidad3;

import android.os.Parcel;
import android.os.Parcelable;

public class Comida implements Parcelable {
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

    protected Comida(Parcel in) {
        titulo = in.readString();
        imagen = in.readInt();
        descripcion = in.readString();
        precio = in.readDouble();
        imgOferta = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeInt(imagen);
        dest.writeString(descripcion);
        dest.writeDouble(precio);
        dest.writeInt(imgOferta);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Comida> CREATOR = new Creator<Comida>() {
        @Override
        public Comida createFromParcel(Parcel in) {
            return new Comida(in);
        }

        @Override
        public Comida[] newArray(int size) {
            return new Comida[size];
        }
    };

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

    //Calculo del precio segun la oferta asociada

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
                ", tipo=" + tipo +
                '}';
    }
}
