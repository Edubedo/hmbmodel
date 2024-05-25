package com.hmbmodel.proyectohmbmodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdministrarGimnasiosUsuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_administrar_gimnasios_usuarios);

    }
    public void redirectoToVerSuscripciones(View view) {
        Intent intent = new Intent(this, MiCuenta.class);
        startActivity(intent);
    }
    public void redirectToSuscribirme(View view) {
        Intent intent = new Intent(this, MiCuenta.class);
        startActivity(intent);
    }
}