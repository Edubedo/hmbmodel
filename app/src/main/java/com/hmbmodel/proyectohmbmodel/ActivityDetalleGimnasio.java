package com.hmbmodel.proyectohmbmodel;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityDetalleGimnasio extends AppCompatActivity {

    private TextView textViewNombre, textViewPropietario, textViewEmail, textViewTelefono, textViewDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_gimnasio);

        textViewNombre = findViewById(R.id.textViewNombre);
        textViewPropietario = findViewById(R.id.textViewPropietario);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewTelefono = findViewById(R.id.textViewTelefono);
        textViewDescripcion = findViewById(R.id.textViewDescripcion);

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
}
