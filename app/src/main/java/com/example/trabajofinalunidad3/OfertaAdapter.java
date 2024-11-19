package com.example.trabajofinalunidad3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OfertaAdapter extends RecyclerView.Adapter<OfertaAdapter.OfertaViewHolder>{
    ArrayList<Comida> coleccion;

    public OfertaAdapter(ArrayList<Comida> coleccion) {
        this.coleccion = coleccion;
    }

    @NonNull
    @Override
    public OfertaAdapter.OfertaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OfertaAdapter.OfertaViewHolder OfertaViewHolder =
                new OfertaViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.ficha_oferta,parent,false)
                );
        return OfertaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OfertaAdapter.OfertaViewHolder holder, int position) {
        Comida Comida = coleccion.get(position);
        holder.imageView.setImageResource(Comida.getImagen());
        holder.tv_titulo.setText(Comida.getTitulo());
        holder.tv_descripcion.setText(Comida.getDescripcion());


    }

    @Override
    public int getItemCount() {

        return coleccion.size();
    }

    public class OfertaViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tv_titulo;
        TextView tv_descripcion;

        public OfertaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tv_titulo = itemView.findViewById(R.id.tv_titulo);
            tv_descripcion = itemView.findViewById(R.id.tv_descripcion);
        }
    }
}
