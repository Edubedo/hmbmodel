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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginUsuarios extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button btnLogin;
    private SharedPreferences sharedPreferences;
    MediaPlayer mediaPlayer;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);

        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        mediaPlayer=MediaPlayer.create(this,R.raw.lobo);
        mAuth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                if (!username.isEmpty() && !password.isEmpty()) {
                    loginUser(username, password);
                } else {
                    Toast.makeText(loginUsuarios.this, "Por favor ingrese sus datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    //Que sea lo que diosito quiera :(
    public void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            String userEmail = user.getEmail();
                            startActivity(new Intent(loginUsuarios.this, homeUsuario.class));
                            finish();
                        }
                    } else {
                        Toast.makeText(loginUsuarios.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
