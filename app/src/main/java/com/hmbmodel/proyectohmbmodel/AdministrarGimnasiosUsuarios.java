package com.hmbmodel.proyectohmbmodel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AdministrarGimnasiosUsuarios extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_gimnasios_usuarios);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
    }

    public void redirectoToVerSuscripciones(View view) {
        Intent intent = new Intent(this, MisSuscripcionesGym.class);
        startActivity(intent);
    }

    public void redirectToSuscribirme(View view) {
        Intent intent = new Intent(this, FormularioSuscribirGimnasio.class);
        startActivity(intent);
    }

    public void redirectToCrearGimnasio(View view) {
        Intent intent = new Intent(this, FormularioCrearGimnasios.class);
        startActivity(intent);
    }

    public void redirectToMisGimnasios(View view) {
        Intent intent = new Intent(this, MisGimnasios.class);
        startActivity(intent);
    }

    public void logout(View view) {
        // Limpiar SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Redirigir al usuario a la pantalla de inicio de sesión
        Intent intent = new Intent(AdministrarGimnasiosUsuarios.this, FormularioIniciarSesion.class);
        startActivity(intent);
        finish();
    }
}
