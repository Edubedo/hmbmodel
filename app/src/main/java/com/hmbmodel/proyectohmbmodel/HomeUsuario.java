package com.hmbmodel.proyectohmbmodel;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeUsuario extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // Método para manejar el clic en "Administrar gimnasios"
    public void redirectToAdministrarGimnasios(View view) {
        Intent intent = new Intent(this, MiCuenta.class);
        startActivity(intent);
    }

    // Método para manejar el clic en "Mi cuenta"
    public void redirectToMiCuenta(View view) {
        Intent intent = new Intent(this, MiCuenta.class);
        startActivity(intent);
    }
}
