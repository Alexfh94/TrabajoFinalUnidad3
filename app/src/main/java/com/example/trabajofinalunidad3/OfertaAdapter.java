package com.example.trabajofinalunidad3;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OfertaAdapter extends RecyclerView.Adapter<OfertaAdapter.OfertaViewHolder> {
    ArrayList<Comida> coleccion;
    OnClickOferta listener;

    public OfertaAdapter(ArrayList<Comida> coleccion, OnClickOferta listener) {
        this.coleccion = coleccion;
        this.listener = listener;
    }

    /**
     * Infla el layout de una tarjeta (ficha_oferta) para cada elemento del RecyclerView.
     */
    @NonNull
    @Override
    public OfertaAdapter.OfertaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("OfertaAdapter", "Creando una nueva vista para una tarjeta de oferta");
        OfertaAdapter.OfertaViewHolder OfertaViewHolder =
                new OfertaViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.ficha_oferta, parent, false)
                );
        return OfertaViewHolder;
    }

    /**
     * Vincula los datos de una instancia de Comida con la vista correspondiente.
     */
    @Override
    public void onBindViewHolder(@NonNull OfertaAdapter.OfertaViewHolder holder, int position) {
        if (position >= coleccion.size()) {
            Log.e("OfertaAdapter", "Posición fuera de los límites de la colección: " + position);
            return;
        }

        Comida comida = coleccion.get(position);
        holder.imgComida.setImageResource(comida.getImagen());
        holder.tv_titulo.setText(comida.getTitulo());
        holder.tv_precio.setText(comida.getPrecio() + "€");

        int imgOfertaResId;
        switch (comida.getImgOferta()) {
            case 1:
                imgOfertaResId = R.drawable.ofertadiez;
                break;
            case 2:
                imgOfertaResId = R.drawable.ofertaventicinco;
                break;
            case 3:
                imgOfertaResId = R.drawable.oferta2x1;
                break;
            default:
                imgOfertaResId = 0;
                Log.w("OfertaAdapter", "Comida sin oferta asignada en la posición: " + position);
                break;
        }

        holder.imgOferta.setImageResource(imgOfertaResId);
        Log.d("OfertaAdapter", "Configurando tarjeta de oferta para posición: " + position);

        holder.itemView.setOnClickListener(v -> {
            Log.d("OfertaAdapter", "Tarjeta de oferta clicada en la posición: " + position);
            listener.onClickCardOferta(v, holder.getAdapterPosition());
        });
    }

    /**
     * Devuelve el tamaño de la colección de datos.
     */
    @Override
    public int getItemCount() {
        Log.d("OfertaAdapter", "Número total de elementos: " + coleccion.size());
        return coleccion.size();
    }

    /**
     * Representa la vista individual de cada elemento del RecyclerView.
     */
    public class OfertaViewHolder extends RecyclerView.ViewHolder {
        ImageView imgComida;
        TextView tv_titulo;
        TextView tv_precio;
        ImageView imgOferta;

        public OfertaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgComida = itemView.findViewById(R.id.imgComida);
            tv_titulo = itemView.findViewById(R.id.tv_titulo);
            tv_precio = itemView.findViewById(R.id.tv_precio);
            imgOferta = itemView.findViewById(R.id.imgOferta);
            Log.d("OfertaViewHolder", "Vista de tarjeta de oferta creada");
        }
    }

    /**
     * Interfaz para gestionar las acciones de clic sobre una tarjeta de oferta.
     */
    public interface OnClickOferta {
        void onClickCardOferta(View view, int position);
    }
}
