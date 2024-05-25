package com.hmbmodel.proyectohmbmodel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormularioCreacionUsuario extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private EditText editTextFullname;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private EditText editTextPassword;
    private EditText editTextGymName;
    private Button buttonCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_creacion_usuario);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        editTextFullname = findViewById(R.id.fullname);
        editTextEmail = findViewById(R.id.email);
        editTextPhone = findViewById(R.id.phone);
        editTextPassword = findViewById(R.id.password);
        editTextGymName = findViewById(R.id.gymname);
        buttonCreate = findViewById(R.id.buttonCreate);

        buttonCreate.setOnClickListener(v -> createUser());
    }

    private void createUser() {
        String fullname = editTextFullname.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String nombre_gimnasio = editTextGymName.getText().toString().trim();

        if (fullname.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || nombre_gimnasio.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Aquí puedes agregar la lógica para guardar los datos en tu base de datos en lugar de usar Firebase Authentication
        // Por ejemplo, podrías usar una llamada a una API REST o acceder directamente a tu base de datos local si es SQLite

        // Suponiendo que estás utilizando Firebase Firestore como base de datos
        Map<String, Object> user = new HashMap<>();
        user.put("fullname", fullname);
        user.put("email", email);
        user.put("phone", phone);
        user.put("password", password);
        user.put("nombre_gimnasio", nombre_gimnasio);

        db.collection("usuarios").add(user)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FormularioCreacionUsuario.this, FormularioIniciarSesion.class);
                    startActivity(intent);
                    finish(); // Finaliza esta actividad para que el usuario no pueda volver aquí con el botón de retroceso
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al guardar los datos del usuario: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

}
