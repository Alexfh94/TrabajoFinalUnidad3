package com.example.trabajofinalunidad3;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
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
    public ComidaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ficha_comida, parent, false);
        return new ComidaViewHolder(view);
    }

    /**
     * Vincula los datos de una instancia de Comida con la vista correspondiente.
     */
    @Override
    public void onBindViewHolder(@NonNull ComidaViewHolder holder, int position) {
        if (position >= coleccion.size()) {
            return;
        }

        Comida comida = coleccion.get(position);
        holder.imageView.setImageResource(comida.getImagen());
        holder.tv_titulo.setText(comida.getTitulo());
        holder.tv_descripcion.setText(comida.getDescripcion());
        holder.tv_precio.setText(comida.getPrecio() + context.getString(R.string.EUR));

        holder.itemView.setTag(position);


        holder.itemView.setOnClickListener(v -> {
            listener.onClickCardComida(v, holder.getAdapterPosition());
        });

        holder.btnAccion.setOnClickListener(v -> {
            listener.onClickCarrito(v, holder.getAdapterPosition());
        });
    }

    /**
     * Devuelve el tamaño de la colección de datos.
     */
    @Override
    public int getItemCount() {
        return coleccion.size();
    }

    /**
     * Representa la vista individual de cada elemento del RecyclerView.
     */
    public class ComidaViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
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
            itemView.setOnCreateContextMenuListener(this); // Registra el menú contextual en el item
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = ((MainActivity) itemView.getContext()).getMenuInflater();
            inflater.inflate(R.menu.context_menu, menu); // Inflar el menú

            // Añadir la posición al menú para utilizarla más tarde
            int position = getAdapterPosition();
            v.setTag(position); // Guardar la posición en el itemView
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
