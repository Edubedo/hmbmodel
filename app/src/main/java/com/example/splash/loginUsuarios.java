package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginUsuarios extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button btnLogin;
    private SharedPreferences sharedPreferences;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);

        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        mediaPlayer = MediaPlayer.create(this, R.raw.lobo);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (!username.isEmpty() && !password.isEmpty()) {
                    authenticateUser(username, password);
                } else {
                    Toast.makeText(loginUsuarios.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void authenticateUser(String username, String password) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean userFound = false;

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String dbUsername = userSnapshot.child("usuario").getValue(String.class);
                    String dbPassword = userSnapshot.child("contraseña").getValue(String.class);
                    String rango = userSnapshot.child("rango").getValue(String.class);

                    if (dbUsername != null && dbPassword != null && dbUsername.equals(username) && dbPassword.equals(password)) {
                        userFound = true;
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.putString("password", password);
                        editor.apply();

                        if ("usuario".equals(rango)) {
                            startActivity(new Intent(loginUsuarios.this, homeUsuario.class));
                        } else if ("administrador".equals(rango)) {
                            startActivity(new Intent(loginUsuarios.this, homeAdministrador.class));
                            mediaPlayer.start();
                        }
                        finish();
                        break;
                    }
                }
                if (!userFound) {
                    Toast.makeText(loginUsuarios.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(loginUsuarios.this, "Error al conectarse a la base de datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}