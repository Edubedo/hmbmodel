package com.hmbmodel.proyectohmbmodel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormularioSuscribirGimnasio extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private SharedPreferences sharedPreferences;

    private EditText editTextNombreGimnasio;
    private EditText editTextNombrePropietario;
    private EditText editTextEmail;
    private EditText editTextFechaIngreso;
    private EditText editTextFechaPago;
    private Button buttonCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_suscribir_gimnasio);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        editTextNombreGimnasio = findViewById(R.id.fullname);
        editTextNombrePropietario = findViewById(R.id.propietario);
        editTextEmail = findViewById(R.id.email);
        editTextFechaIngreso = findViewById(R.id.fechaingreso);
        editTextFechaPago = findViewById(R.id.fechapago);
        buttonCreate = findViewById(R.id.buttonCreate);

        buttonCreate.setOnClickListener(v -> createSubscription());
    }

    private void createSubscription() {
        String nombreGimnasio = editTextNombreGimnasio.getText().toString().trim();
        String nombrePropietario = editTextNombrePropietario.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String fechaIngreso = editTextFechaIngreso.getText().toString().trim();
        String fechaPago = editTextFechaPago.getText().toString().trim();

        // Validar campos
        if (nombreGimnasio.isEmpty() || nombrePropietario.isEmpty() || email.isEmpty() || fechaIngreso.isEmpty() || fechaPago.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Consultar si el correo electrónico existe en la tabla de usuarios
        db.collection("usuarios")
                .whereEqualTo("email", email)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        // Correo electrónico válido, continuar con la creación de la suscripción
                        // Obtener el email del propietario desde SharedPreferences
                        String ownerEmail = sharedPreferences.getString("email", null);

                        if (ownerEmail == null || ownerEmail.isEmpty()) {
                            Toast.makeText(this, "Usuario no autenticado", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Consultar el gimnasio en la base de datos
                        db.collection("gimnasios")
                                .whereEqualTo("nombre", nombreGimnasio)
                                .get()
                                .addOnSuccessListener(queryDocumentSnapshots1 -> {
                                    if (!queryDocumentSnapshots1.isEmpty()) {
                                        // Gimnasio encontrado, crear suscripción
                                        Map<String, Object> suscripcion = new HashMap<>();
                                        suscripcion.put("nombreGimnasio", nombreGimnasio);
                                        suscripcion.put("nombrePropietario", nombrePropietario);
                                        suscripcion.put("email", email);
                                        suscripcion.put("fechaIngreso", fechaIngreso);
                                        suscripcion.put("fechaPago", fechaPago);
                                        suscripcion.put("propietarioEmail", ownerEmail);
                                        suscripcion.put("fechaCreacion", FieldValue.serverTimestamp());

                                        db.collection("suscripciones").add(suscripcion)
                                                .addOnSuccessListener(documentReference -> {
                                                    Toast.makeText(this, "Suscripción creada exitosamente", Toast.LENGTH_SHORT).show();
                                                    // Limpiar campos después de crear la suscripción
                                                    editTextNombreGimnasio.setText("");
                                                    editTextNombrePropietario.setText("");
                                                    editTextEmail.setText("");
                                                    editTextFechaIngreso.setText("");
                                                    editTextFechaPago.setText("");

                                                    // Redirigir a la sección "Mis Suscripciones"
                                                    Intent intent = new Intent(FormularioSuscribirGimnasio.this, MisSuscripcionesGym.class);
                                                    startActivity(intent);
                                                    finish();
                                                })
                                                .addOnFailureListener(e -> {
                                                    Toast.makeText(this, "Error al crear la suscripción: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                });
                                    } else {
                                        // Gimnasio no encontrado, mostrar mensaje de error
                                        Toast.makeText(this, "El gimnasio no existe", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(this, "Error al buscar el gimnasio: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                    } else {
                        // Correo electrónico no encontrado en la tabla de usuarios, mostrar mensaje de error
                        Toast.makeText(this, "El correo electrónico no es válido", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al buscar el correo electrónico: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }


}
