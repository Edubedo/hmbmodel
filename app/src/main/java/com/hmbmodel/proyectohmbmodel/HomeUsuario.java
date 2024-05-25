package com.hmbmodel.proyectohmbmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeUsuario extends AppCompatActivity {

    private TextView textWelcomeMessage;
    private ImageButton btnLogout;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_usuario);

        textWelcomeMessage = findViewById(R.id.textWelcomeMessage);
        btnLogout = findViewById(R.id.btnLogout);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        // Obtener el nombre de usuario del SharedPreferences y establecerlo en el TextView
        String username = sharedPreferences.getString("username", "");
        if (!username.isEmpty()) {
            textWelcomeMessage.setText("¡Bienvenido/a, " + username + "!");
        }

        // Escuchar clics en el botón de cerrar sesión
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Limpiar SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                // Redirigir al usuario a la pantalla de inicio de sesión
                Intent intent = new Intent(HomeUsuario.this, FormularioIniciarSesion.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Método para manejar el clic en "Administrar gimnasios"
    public void redirectToAdministrarGimnasios(View view) {
        Intent intent = new Intent(this, AdministrarGimnasiosUsuarios.class);
        startActivity(intent);
    }

    // Método para manejar el clic en "Mi cuenta"
    public void redirectToMiCuenta(View view) {
        Intent intent = new Intent(this, MiCuenta.class);
        startActivity(intent);
    }
}
