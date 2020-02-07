package com.facci.examen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    ArrayList<Modelo> modelo;

    public Adaptador(ArrayList<Modelo> modelo) {
        this.modelo = modelo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Modelo estudiantes = modelo.get(i);
        viewHolder.id.setText(estudiantes.getId());
        viewHolder.cedula.setText(estudiantes.getCedula());
        viewHolder.nombre.setText(estudiantes.getNombre());
        viewHolder.estado.setText(estudiantes.getEstado());
    }

    @Override
    public int getItemCount() {
        return modelo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView cedula, nombre, estado, id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView)itemView.findViewById(R.id.LBLid);
            cedula = (TextView)itemView.findViewById(R.id.LBLcedula);
            nombre = (TextView)itemView.findViewById(R.id.LBLnombre);
            estado = (TextView)itemView.findViewById(R.id.LBLestado);
        }
    }
}
