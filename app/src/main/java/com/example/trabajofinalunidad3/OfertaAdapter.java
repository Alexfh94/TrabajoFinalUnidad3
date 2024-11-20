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
        holder.imgComida.setImageResource(Comida.getImagen());
        holder.tv_titulo.setText(Comida.getTitulo());
        holder.tv_precio.setText(String.valueOf(Comida.getPrecio())+"€");
        holder.imgOferta.setImageResource(Comida.getImgOferta());


    }

    @Override
    public int getItemCount() {

        return coleccion.size();
    }

    public class OfertaViewHolder extends RecyclerView.ViewHolder{
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
        }
    }
}
