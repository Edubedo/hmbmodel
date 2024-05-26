package com.hmbmodel.proyectohmbmodel;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleSuscripcionActivity extends AppCompatActivity {

    private TextView textNombreGimnasio;
    private TextView textFechaIngreso;
    private TextView textFechaPago;
    private TextView textNombrePropietario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_suscripcion);

        textNombreGimnasio = findViewById(R.id.textNombreGimnasioDetalle);
        textFechaIngreso = findViewById(R.id.textFechaIngresoDetalle);
        textFechaPago = findViewById(R.id.textFechaPagoDetalle);
        textNombrePropietario = findViewById(R.id.textNombrePropietarioDetalle);

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
}
