package com.hmbmodel.proyectohmbmodel;

import android.os.Bundle;

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
                        // Maneja errores
                    }
                });
    }
}
