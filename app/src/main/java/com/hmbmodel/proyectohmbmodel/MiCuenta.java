package com.hmbmodel.proyectohmbmodel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MiCuenta extends AppCompatActivity {

    private TextView textViewFullName;
    private TextView textViewEmail;
    private TextView textViewPhone;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cuenta);

        // Habilitar EdgeToEdge y establecer el padding para evitar que los elementos se superpongan con la barra de estado
        EdgeToEdge.enable(this);

        // Enlazar vistas
        textViewFullName = findViewById(R.id.editTextName);
        textViewEmail = findViewById(R.id.editTextEmail);
        textViewPhone = findViewById(R.id.editTextPhone);

        // Aplicar el padding para la barra de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        // Obtener el correo electrónico del usuario
        String userEmail = sharedPreferences.getString("email", "");

        // Buscar al usuario en la base de datos y mostrar sus datos
        buscarUsuario(userEmail);
    }

    private void buscarUsuario(String email) {
        // Obtener instancia de Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Consulta para obtener los datos del usuario basado en el correo electrónico
        db.collection("usuarios")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Procesar el resultado de la consulta
                        for (DocumentSnapshot document : task.getResult()) {
                            // Obtener los datos del usuario
                            String fullname = document.getString("fullname");
                            String phone = document.getString("phone");

                            // Mostrar los datos en las vistas correspondientes
                            textViewFullName.setText(fullname);
                            textViewEmail.setText(email);
                            textViewPhone.setText(phone);
                        }
                    }  // Manejar errores

                });
    }

    public void logout(View view) {
        // Limpiar SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Redirigir al usuario a la pantalla de inicio de sesión
        Intent intent = new Intent(MiCuenta.this, FormularioIniciarSesion.class);
        startActivity(intent);
        finish();
    }
}
