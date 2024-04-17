package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button btnLogin;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Obtenemos SharedPreferences
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);

        // Comprobamos si ya hay un usuario almacenado
        if (sharedPreferences.contains("username") && sharedPreferences.contains("password")) {
            // Si hay un usuario almacenado, vamos directamente al MainActivity2
            startActivity(new Intent(MainActivity.this, PaginaLogin.class));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Verificamos las credenciales
                if (username.equals("usuario") && password.equals("contraseña")) {
                    // Guardamos el usuario en SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.apply();

                    // Iniciamos la actividad MainActivity2
                    startActivity(new Intent(MainActivity.this, PaginaLogin.class));
                    finish();
                } else {
                    // Si las credenciales son incorrectas, mostramos un mensaje de error
                    Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
