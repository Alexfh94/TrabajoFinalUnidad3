package com.example.trabajofinalunidad3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        int imgOfertaResId;
        switch (Comida.getImgOferta()) {
            case 1:
                imgOfertaResId = R.drawable.ofertadiez; // Imagen para 10% de descuento
                break;
            case 2:
                imgOfertaResId = R.drawable.ofertaventicinco; // Imagen para 25% de descuento
                break;
            case 3:
                imgOfertaResId = R.drawable.oferta2x1; // Imagen para 50% de descuento
                break;
            default:
                imgOfertaResId = 0; // Imagen por defecto
                break;
        }
        holder.imgOferta.setImageResource(imgOfertaResId);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = Comida.getTitulo()+" "+ Comida.getPrecio()+" €" + "\n" + "Añadido al carrito.";
                Toast.makeText(v.getContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });


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
