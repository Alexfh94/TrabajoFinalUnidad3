package com.example.trabajofinalunidad3;

import android.content.Context;
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
    Context context;

    public ComidaAdapter(ArrayList<Comida> coleccion, OnClickComida listener, Context context) {
        this.coleccion = coleccion;
        this.listener = listener;
        this.context = context;
    }

    /**
     * Infla el layout de una tarjeta (ficha_comida) para cada elemento del RecyclerView.
     */
    @NonNull
    @Override
    public ComidaAdapter.ComidaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d(context.getString(R.string.TAGC), context.getString(R.string.creando_una_nueva_vista_para_una_tarjeta_de_comida));
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
            Log.e(context.getString(R.string.TAGC), context.getString(R.string.posici_n_fuera_de_los_l_mites_de_la_colecci_n)+ position);
            return;
        }

        Comida comida = coleccion.get(position);
        holder.imageView.setImageResource(comida.getImagen());
        holder.tv_titulo.setText(comida.getTitulo());
        holder.tv_descripcion.setText(comida.getDescripcion());
        holder.tv_precio.setText(comida.getPrecio() + context.getString(R.string.EUR));

        Log.d(context.getString(R.string.TAGC), context.getString(R.string.configurando_tarjeta_para_posici_n)+ position);

        holder.itemView.setOnClickListener(v -> {
            Log.d(context.getString(R.string.TAGC), context.getString(R.string.tarjeta_clicada_en_la_posici_n)+ position);
            listener.onClickCardComida(v, holder.getAdapterPosition());
        });

        holder.btnAccion.setOnClickListener(v -> {
            Log.d(context.getString(R.string.TAGC), context.getString(R.string.bot_n_de_carrito_clicado_en_la_posici_n)+ position);
            listener.onClickCarrito(v, holder.getAdapterPosition());
        });
    }

    /**
     * Devuelve el tamaño de la colección de datos.
     */
    @Override
    public int getItemCount() {
        Log.d(context.getString(R.string.TAGC), context.getString(R.string.n_mero_total_de_elementos)+ coleccion.size());
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
            Log.d(context.getString(R.string.TAGC), context.getString(R.string.vista_de_tarjeta_creada));
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
