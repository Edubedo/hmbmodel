package com.hmbmodel.proyectohmbmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class FormularioIniciarSesion extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;
    private Button btnLogin;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_iniciar_sesion);

        editTextUsername = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);

        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("username") && sharedPreferences.contains("password")) {
            startActivity(new Intent(FormularioIniciarSesion.this,HomeUsuario.class));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Realizamos la consulta a la base de datos para verificar las credenciales
                verifyCredentials(username, password);
            }
        });



    }

    private void verifyCredentials(String username, String password) {
        // Consulta en la colección "usuarios" de Firebase Firestore
        // para verificar si existe un documento con el correo electrónico ingresado
        // y si la contraseña coincide

        // Obtenemos la referencia a la colección "usuarios"
        CollectionReference usuariosRef = FirebaseFirestore.getInstance().collection("usuarios");

        // Realizamos la consulta buscando el documento con el correo electrónico ingresado
        usuariosRef.whereEqualTo("email", username)
                .whereEqualTo("password", password)  // Agregamos esta condición para verificar la contraseña
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Si se encuentra algún documento con el correo electrónico ingresado
                        // y la contraseña coincide
                        if (!task.getResult().isEmpty()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Las credenciales son correctas
                                // Obtener el nombre completo del usuario
                                String fullname = document.getString("fullname");

                                // Guardar nombre de usuario y nombre completo en SharedPreferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("username", username);
                                editor.putString("fullname", fullname);
                                editor.apply();

                                // Redirigir al usuario a la pantalla de inicio
                                startActivity(new Intent(FormularioIniciarSesion.this, HomeUsuario.class));
                                finish();
                            }
                        } else {
                            // Si no se encuentra ningún documento que coincida con las credenciales
                            // Muestra un mensaje de error
                            Toast.makeText(FormularioIniciarSesion.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Si ocurre algún error al realizar la consulta
                        // Muestra un mensaje de error
                        Toast.makeText(FormularioIniciarSesion.this, "Error al verificar las credenciales", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
