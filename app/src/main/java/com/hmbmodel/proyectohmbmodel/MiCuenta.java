package com.hmbmodel.proyectohmbmodel;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cuenta);

        // Habilitar EdgeToEdge y establecer el padding para evitar que los elementos se superpongan con la barra de estado
        EdgeToEdge.enable(this);

        // Enlazar vistas
        textViewFullName = findViewById(R.id.textViewFullName);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewPhone = findViewById(R.id.textViewPhone);

        // Aplicar el padding para la barra de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtener el correo electrónico del usuario (puedes obtenerlo de donde lo necesites en tu aplicación)
        String userEmail = "ezescobedo27@gmail.com";

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
                    } else {
                        // Manejar errores
                    }
                });
    }
}
