package com.example.trabajofinalunidad3;

import android.content.Context;
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
    Context context;

    public OfertaAdapter(ArrayList<Comida> coleccion, OnClickOferta listener, Context context) {
        this.coleccion = coleccion;
        this.listener = listener;
        this.context = context;
    }

    /**
     * Infla el layout de una tarjeta (ficha_oferta) para cada elemento del RecyclerView.
     */
    @NonNull
    @Override
    public OfertaAdapter.OfertaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(context.getString(R.string.TAGO), context.getString(R.string.creando_una_nueva_vista_para_una_tarjeta_de_oferta));
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
            Log.e(context.getString(R.string.TAGO), context.getString(R.string.posici_n_fuera_de_los_l_mites_de_la_colecci_nn)+ position);
            return;
        }

        Comida comida = coleccion.get(position);
        holder.imgComida.setImageResource(comida.getImagen());
        holder.tv_titulo.setText(comida.getTitulo());
        holder.tv_precio.setText(comida.getPrecio() + context.getString(R.string.EUR));

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
                Log.w(context.getString(R.string.TAGO), context.getString(R.string.comida_sin_oferta_asignada_en_la_posici_n)+ position);
                break;
        }

        holder.imgOferta.setImageResource(imgOfertaResId);
        Log.d(context.getString(R.string.TAGO), context.getString(R.string.configurando_tarjeta_de_oferta_para_posici_n)+ position);

        holder.itemView.setOnClickListener(v -> {
            Log.d(context.getString(R.string.TAGO), context.getString(R.string.tarjeta_de_oferta_clicada_en_la_posici_n)+ position);
            listener.onClickCardOferta(v, holder.getAdapterPosition());
        });
    }

    /**
     * Devuelve el tamaño de la colección de datos.
     */
    @Override
    public int getItemCount() {
        Log.d(context.getString(R.string.TAGO), context.getString(R.string.n_mero_total_de_elementoss)+ coleccion.size());
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
            Log.d(context.getString(R.string.TAGO), context.getString(R.string.vista_de_tarjeta_de_oferta_creada));
        }
    }

    /**
     * Interfaz para gestionar las acciones de clic sobre una tarjeta de oferta.
     */
    public interface OnClickOferta {
        void onClickCardOferta(View view, int position);
    }
}
