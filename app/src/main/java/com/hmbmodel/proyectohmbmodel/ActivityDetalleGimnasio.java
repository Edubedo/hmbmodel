package com.hmbmodel.proyectohmbmodel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetalleGimnasio extends AppCompatActivity {

    private TextView textViewNombre, textViewPropietario, textViewEmail, textViewTelefono, textViewDescripcion;
    private RecyclerView recyclerViewUsuarios;
    private UsuarioAdapter usuariosAdapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_gimnasio);

        textViewNombre = findViewById(R.id.textViewNombre);
        textViewPropietario = findViewById(R.id.textViewPropietario);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewTelefono = findViewById(R.id.textViewTelefono);
        textViewDescripcion = findViewById(R.id.textViewDescripcion);

        // Obtener la instancia de SharedPreferences
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        // Obtener los datos del Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombre = extras.getString("nombre");
            String propietario = extras.getString("propietario");
            String email = extras.getString("email");
            String telefono = extras.getString("telefono");
            String descripcion = extras.getString("descripcion");

            // Establecer los valores en los TextViews
            textViewNombre.setText(nombre);
            textViewPropietario.setText(propietario);
            textViewEmail.setText(email);
            textViewTelefono.setText(telefono);
            textViewDescripcion.setText(descripcion);
        }

    }

    public void logout(View view) {
        // Limpiar SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Redirigir al usuario a la pantalla de inicio de sesión
        Intent intent = new Intent(ActivityDetalleGimnasio.this, FormularioIniciarSesion.class);
        startActivity(intent);
        finish();
    }
}
