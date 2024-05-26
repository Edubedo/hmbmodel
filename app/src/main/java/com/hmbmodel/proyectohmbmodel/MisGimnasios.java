package com.hmbmodel.proyectohmbmodel;

import android.content.SharedPreferences;
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

public class MisGimnasios extends AppCompatActivity {

    private FirebaseFirestore db;
    private RecyclerView recyclerViewGimnasios;
    private GimnasioAdapter adapter;
    private List<Gimnasio> gimnasioList;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_gimnasios);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String ownerEmail = sharedPreferences.getString("email", null);

        if (ownerEmail == null || ownerEmail.isEmpty()) {
            // Maneja el caso donde no hay correo electrónico en SharedPreferences
            return;
        }

        db = FirebaseFirestore.getInstance();
        recyclerViewGimnasios = findViewById(R.id.recyclerViewGimnasios);
        recyclerViewGimnasios.setLayoutManager(new LinearLayoutManager(this));

        gimnasioList = new ArrayList<>();
        adapter = new GimnasioAdapter(gimnasioList);
        recyclerViewGimnasios.setAdapter(adapter);

        db.collection("gimnasios")
                .whereEqualTo("propietarioEmail", ownerEmail)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Gimnasio gimnasio = document.toObject(Gimnasio.class);
                            gimnasioList.add(gimnasio);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        // Maneja errores
                    }
                });
    }
}
