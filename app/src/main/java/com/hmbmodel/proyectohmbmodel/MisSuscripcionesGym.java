package com.hmbmodel.proyectohmbmodel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MisSuscripcionesGym extends AppCompatActivity {

    private FirebaseFirestore db;
    private RecyclerView recyclerViewSuscripciones;
    private SuscripcionAdapter adapter;
    private List<Suscripcion> suscripcionList;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_suscripciones_gym);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = FirebaseFirestore.getInstance();
        recyclerViewSuscripciones = findViewById(R.id.recyclerViewSuscripciones);
        recyclerViewSuscripciones.setLayoutManager(new LinearLayoutManager(this));

        suscripcionList = new ArrayList<>();
        adapter = new SuscripcionAdapter(this, suscripcionList);
        recyclerViewSuscripciones.setAdapter(adapter);

        // Obtener SharedPreferences
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        // Cargar suscripciones
        cargarSuscripciones();

    }

    private void cargarSuscripciones() {
        db.collection("suscripciones")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Suscripcion suscripcion = document.toObject(Suscripcion.class);
                            suscripcionList.add(new Suscripcion(
                                    document.getId(),
                                    suscripcion.getNombreGimnasio(),
                                    suscripcion.getFechaIngreso(),
                                    suscripcion.getFechaPago(),
                                    suscripcion.getNombrePropietario()
                            ));
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        // Manejar errores
                    }
                });
    }

    public void logout(View view) {
        // Limpiar SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Redirigir al usuario a la pantalla de inicio de sesión
        Intent intent = new Intent(MisSuscripcionesGym.this, FormularioIniciarSesion.class);
        startActivity(intent);
        finish();
    }
}
