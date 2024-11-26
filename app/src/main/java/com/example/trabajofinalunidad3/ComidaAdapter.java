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

public class ComidaAdapter extends RecyclerView.Adapter<ComidaAdapter.ComidaViewHolder>{
    ArrayList<Comida> coleccion;

    public ComidaAdapter(ArrayList<Comida> coleccion) {
        this.coleccion = coleccion;
    }

    @NonNull
    @Override
    public ComidaAdapter.ComidaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ComidaAdapter.ComidaViewHolder ComidaViewHolder =
                new ComidaViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.ficha_comida,parent,false)
                );
        return ComidaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComidaAdapter.ComidaViewHolder holder, int position) {
        Comida Comida = coleccion.get(position);
        holder.imageView.setImageResource(Comida.getImagen());
        holder.tv_titulo.setText(Comida.getTitulo());
        holder.tv_descripcion.setText(Comida.getDescripcion());
        holder.tv_precio.setText(Comida.getPrecio() +"€");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = Comida.getTitulo()+"\n"+ Comida.getPrecio()+" €";
                Toast.makeText(v.getContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {

        return coleccion.size();
    }

    public class ComidaViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tv_titulo;
        TextView tv_descripcion;
        TextView tv_precio;

        public ComidaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgComida);
            tv_titulo = itemView.findViewById(R.id.tv_titulo);
            tv_descripcion = itemView.findViewById(R.id.tv_descripcion);
            tv_precio = itemView.findViewById(R.id.tv_precio);
        }
    }


}
