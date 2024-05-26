package com.hmbmodel.proyectohmbmodel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SuscripcionAdapter extends RecyclerView.Adapter<SuscripcionAdapter.SuscripcionViewHolder> {

    private List<Suscripcion> suscripcionList;
    private Context context;

    public SuscripcionAdapter(Context context, List<Suscripcion> suscripcionList) {
        this.context = context;
        this.suscripcionList = suscripcionList;
    }

    @NonNull
    @Override
    public SuscripcionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suscripcion, parent, false);
        return new SuscripcionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuscripcionViewHolder holder, int position) {
        Suscripcion suscripcion = suscripcionList.get(position);
        holder.textNombreGimnasio.setText(suscripcion.getNombreGimnasio());
        holder.textFechaIngreso.setText(suscripcion.getFechaIngreso());
        holder.textFechaPago.setText(suscripcion.getFechaPago());
        holder.textNombrePropietario.setText(suscripcion.getNombrePropietario());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalleSuscripcionActivity.class);
            intent.putExtra("nombreGimnasio", suscripcion.getNombreGimnasio());
            intent.putExtra("fechaIngreso", suscripcion.getFechaIngreso());
            intent.putExtra("fechaPago", suscripcion.getFechaPago());
            intent.putExtra("nombrePropietario", suscripcion.getNombrePropietario());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return suscripcionList.size();
    }

    static class SuscripcionViewHolder extends RecyclerView.ViewHolder {
        TextView textNombreGimnasio;
        TextView textFechaIngreso;
        TextView textFechaPago;
        TextView textNombrePropietario;

        public SuscripcionViewHolder(@NonNull View itemView) {
            super(itemView);
            textNombreGimnasio = itemView.findViewById(R.id.textNombreGimnasio);
            textFechaIngreso = itemView.findViewById(R.id.textFechaIngreso);
            textFechaPago = itemView.findViewById(R.id.textFechaPago);
            textNombrePropietario = itemView.findViewById(R.id.textNombrePropietario);
        }
    }
}
