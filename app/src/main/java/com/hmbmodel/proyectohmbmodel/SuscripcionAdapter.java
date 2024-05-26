package com.hmbmodel.proyectohmbmodel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

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

        holder.buttonDelete.setOnClickListener(v -> {
            // Eliminar suscripción de Firestore
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("suscripciones").document(suscripcion.getId())
                    .delete()
                    .addOnSuccessListener(aVoid -> {
                        // Suscripción eliminada
                        Toast.makeText(context, "Suscripción eliminada", Toast.LENGTH_SHORT).show();
                        // Redirigir a la sección de "Mis Gimnasios"
                        Intent intent = new Intent(context, HomeUsuario.class);
                        context.startActivity(intent);
                    })
                    .addOnFailureListener(e -> {
                        // Error al eliminar
                        Toast.makeText(context, "Error al eliminar la suscripción", Toast.LENGTH_SHORT).show();
                    });
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
        Button buttonDelete;

        public SuscripcionViewHolder(@NonNull View itemView) {
            super(itemView);
            textNombreGimnasio = itemView.findViewById(R.id.textNombreGimnasio);
            textFechaIngreso = itemView.findViewById(R.id.textFechaIngreso);
            textFechaPago = itemView.findViewById(R.id.textFechaPago);
            textNombrePropietario = itemView.findViewById(R.id.textNombrePropietario);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
