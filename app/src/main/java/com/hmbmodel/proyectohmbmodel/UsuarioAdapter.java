package com.hmbmodel.proyectohmbmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    private List<Usuario> usuarios;

    public UsuarioAdapter(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_usuario, parent, false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Usuario usuario = usuarios.get(position);
        holder.textViewFullname.setText(usuario.getFullname());
        holder.textViewPhone.setText(usuario.getPhone());
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFullname;
        TextView textViewPhone;

        UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFullname = itemView.findViewById(R.id.textViewFullname);
            textViewPhone = itemView.findViewById(R.id.textViewPhone);
        }
    }
}
