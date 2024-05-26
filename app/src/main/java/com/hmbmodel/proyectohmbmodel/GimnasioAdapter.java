package com.hmbmodel.proyectohmbmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GimnasioAdapter extends RecyclerView.Adapter<GimnasioAdapter.GimnasioViewHolder> {

    private List<Gimnasio> gimnasioList;

    public GimnasioAdapter(List<Gimnasio> gimnasioList) {
        this.gimnasioList = gimnasioList;
    }

    @NonNull
    @Override
    public GimnasioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gimnasio, parent, false);
        return new GimnasioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GimnasioViewHolder holder, int position) {
        Gimnasio gimnasio = gimnasioList.get(position);
        holder.textViewNombre.setText(gimnasio.getNombre());
        holder.textViewPropietario.setText(gimnasio.getPropietario());
        holder.textViewDescripcion.setText(gimnasio.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return gimnasioList.size();
    }

    static class GimnasioViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre;
        TextView textViewPropietario;
        TextView textViewDescripcion;

        public GimnasioViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewPropietario = itemView.findViewById(R.id.textViewPropietario);
            textViewDescripcion = itemView.findViewById(R.id.textViewDescripcion);
        }
    }
}
