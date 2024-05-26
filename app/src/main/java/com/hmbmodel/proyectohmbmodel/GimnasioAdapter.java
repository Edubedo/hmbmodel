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

public class GimnasioAdapter extends RecyclerView.Adapter<GimnasioAdapter.GimnasioViewHolder> {

    private List<Gimnasio> gimnasioList;
    private Context context;

    public GimnasioAdapter(Context context, List<Gimnasio> gimnasioList) {
        this.context = context;
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

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ActivityDetalleGimnasio.class);
            intent.putExtra("nombre", gimnasio.getNombre());
            intent.putExtra("propietario", gimnasio.getPropietario());
            intent.putExtra("email", gimnasio.getEmail());
            intent.putExtra("telefono", gimnasio.getTelefono());
            intent.putExtra("descripcion", gimnasio.getDescripcion());
            context.startActivity(intent);
        });

        holder.buttonDelete.setOnClickListener(v -> {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("gimnasios").document(gimnasio.getId())
                    .delete()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(context, "Gimnasio eliminado", Toast.LENGTH_SHORT).show();
                        gimnasioList.remove(position);
                        notifyItemRemoved(position);
                        Intent intent = new Intent(context, MisGimnasios.class);
                        context.startActivity(intent);
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(context, "Error al eliminar el gimnasio", Toast.LENGTH_SHORT).show();
                    });
        });
    }

    @Override
    public int getItemCount() {
        return gimnasioList.size();
    }

    static class GimnasioViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre;
        TextView textViewPropietario;
        TextView textViewDescripcion;
        Button buttonDelete;

        public GimnasioViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewPropietario = itemView.findViewById(R.id.textViewPropietario);
            textViewDescripcion = itemView.findViewById(R.id.textViewDescripcion);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
