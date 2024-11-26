package com.example.trabajofinalunidad3;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ComidaAdapter extends RecyclerView.Adapter<ComidaAdapter.ComidaViewHolder> {
    ArrayList<Comida> coleccion;
    OnClickComida listener;

    public ComidaAdapter(ArrayList<Comida> coleccion, OnClickComida listener) {
        this.coleccion = coleccion;
        this.listener = listener;
    }

    /**
     * Infla el layout de una tarjeta (ficha_comida) para cada elemento del RecyclerView.
     */
    @NonNull
    @Override
    public ComidaAdapter.ComidaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("ComidaAdapter", "Creando una nueva vista para una tarjeta de comida");
        ComidaAdapter.ComidaViewHolder ComidaViewHolder =
                new ComidaViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.ficha_comida, parent, false)
                );
        return ComidaViewHolder;
    }

    /**
     * Vincula los datos de una instancia de Comida con la vista correspondiente.
     */
    @Override
    public void onBindViewHolder(@NonNull ComidaAdapter.ComidaViewHolder holder, int position) {
        if (position >= coleccion.size()) {
            Log.e("ComidaAdapter", "Posición fuera de los límites de la colección: " + position);
            return;
        }

        Comida comida = coleccion.get(position);
        holder.imageView.setImageResource(comida.getImagen());
        holder.tv_titulo.setText(comida.getTitulo());
        holder.tv_descripcion.setText(comida.getDescripcion());
        holder.tv_precio.setText(comida.getPrecio() + "€");

        Log.d("ComidaAdapter", "Configurando tarjeta para posición: " + position);

        holder.itemView.setOnClickListener(v -> {
            Log.d("ComidaAdapter", "Tarjeta clicada en la posición: " + position);
            listener.onClickCardComida(v, holder.getAdapterPosition());
        });

        holder.btnAccion.setOnClickListener(v -> {
            Log.d("ComidaAdapter", "Botón de carrito clicado en la posición: " + position);
            listener.onClickCarrito(v, holder.getAdapterPosition());
        });
    }

    /**
     * Devuelve el tamaño de la colección de datos.
     */
    @Override
    public int getItemCount() {
        Log.d("ComidaAdapter", "Número total de elementos: " + coleccion.size());
        return coleccion.size();
    }

    /**
     * Representa la vista individual de cada elemento del RecyclerView.
     */
    public class ComidaViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_titulo;
        TextView tv_descripcion;
        TextView tv_precio;
        Button btnAccion;

        public ComidaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgComida);
            tv_titulo = itemView.findViewById(R.id.tv_titulo);
            tv_descripcion = itemView.findViewById(R.id.tv_descripcion);
            tv_precio = itemView.findViewById(R.id.tv_precio);
            btnAccion = itemView.findViewById(R.id.btnCarrito);
            Log.d("ComidaViewHolder", "Vista de tarjeta creada");
        }
    }

    /**
     * Interfaz para gestionar las acciones de clic sobre una tarjeta o su botón.
     */
    public interface OnClickComida {
        void onClickCarrito(View view, int position);

        void onClickCardComida(View view, int position);
    }
}
