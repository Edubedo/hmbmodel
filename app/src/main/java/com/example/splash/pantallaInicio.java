package com.example.splash;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class pantallaInicio extends AppCompatActivity {

    private Button btnLinkRegistro, btnLinkLogin;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);

        btnLinkRegistro = findViewById(R.id.btnLinkRegistro);
        btnLinkLogin = findViewById(R.id.btnLinkLogin);
        btnLinkRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(pantallaInicio.this, registroUsuarios.class));
                    finish();
            }
        });

        btnLinkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(pantallaInicio.this, loginUsuarios.class));
                finish();
            }
        });



    }
}
