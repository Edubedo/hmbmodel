package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginUsuarios extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button btnLogin;
    private SharedPreferences sharedPreferences;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);

        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        mediaPlayer=MediaPlayer.create(this,R.raw.lobo);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                if (username.equals("usuario") && password.equals("contraseña")) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.apply();

                    startActivity(new Intent(loginUsuarios.this, homeUsuario.class));
                    finish();
                } else if (username.equals("Temach") && password.equals("mi pastor")) {
                    startActivity(new Intent(loginUsuarios.this, homeAdministrador.class));
                    finish();
                    mediaPlayer.start();

                } else {
                    Toast.makeText(loginUsuarios.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
