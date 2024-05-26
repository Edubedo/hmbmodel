package com.hmbmodel.proyectohmbmodel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormularioCrearGimnasios extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private EditText editTextFullname;
    private EditText editTextOwner;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private EditText editTextDescription;
    private Button buttonCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_crear_gimnasios);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        editTextFullname = findViewById(R.id.fullname);
        editTextOwner = findViewById(R.id.propietario);
        editTextEmail = findViewById(R.id.email);
        editTextPhone = findViewById(R.id.phone);
        editTextDescription = findViewById(R.id.password);
        buttonCreate = findViewById(R.id.buttonCreate);

        buttonCreate.setOnClickListener(v -> createGym());
    }

    private void createGym() {
        String fullname = editTextFullname.getText().toString().trim();
        String owner = editTextOwner.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Toast.makeText(this, "Usuario no autenticado", Toast.LENGTH_SHORT).show();
            return;
        }
        String ownerEmail = currentUser.getEmail();

        if (fullname.isEmpty() || owner.isEmpty() || email.isEmpty() || phone.isEmpty() || description.isEmpty() || ownerEmail == null || ownerEmail.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> gym = new HashMap<>();
        gym.put("nombre", fullname);
        gym.put("propietario", owner);
        gym.put("email", email);
        gym.put("telefono", phone);
        gym.put("descripcion", description);
        gym.put("propietarioEmail", ownerEmail);
        gym.put("fechaCreacion", FieldValue.serverTimestamp());

        db.collection("gimnasios").add(gym)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Gimnasio creado exitosamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FormularioCrearGimnasios.this, HomeUsuario.class);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al guardar los datos del gimnasio: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
