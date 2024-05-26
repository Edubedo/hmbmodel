package com.hmbmodel.proyectohmbmodel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleSuscripcionActivity extends AppCompatActivity {

    private TextView textNombreGimnasio;
    private TextView textFechaIngreso;
    private TextView textFechaPago;
    private TextView textNombrePropietario;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_suscripcion);

        textNombreGimnasio = findViewById(R.id.textNombreGimnasioDetalle);
        textFechaIngreso = findViewById(R.id.textFechaIngresoDetalle);
        textFechaPago = findViewById(R.id.textFechaPagoDetalle);
        textNombrePropietario = findViewById(R.id.textNombrePropietarioDetalle);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        // Obtén los datos pasados desde la intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombreGimnasio = extras.getString("nombreGimnasio");
            String fechaIngreso = extras.getString("fechaIngreso");
            String fechaPago = extras.getString("fechaPago");
            String nombrePropietario = extras.getString("nombrePropietario");

            // Asigna los valores a los TextViews
            textNombreGimnasio.setText(nombreGimnasio);
            textFechaIngreso.setText(fechaIngreso);
            textFechaPago.setText(fechaPago);
            textNombrePropietario.setText(nombrePropietario);
        }
    }

    public void logout(View view) {
        // Limpiar SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Redirigir al usuario a la pantalla de inicio de sesión
        Intent intent = new Intent(DetalleSuscripcionActivity.this, FormularioIniciarSesion.class);
        startActivity(intent);
        finish();
    }
}
