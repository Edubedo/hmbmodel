package com.hmbmodel.proyectohmbmodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class AdministrarGimnasiosUsuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_gimnasios_usuarios);
    }

    public void redirectoToVerSuscripciones(View view) {
        Intent intent = new Intent(this, MisGimnasios.class);
        startActivity(intent);
    }

    public void redirectToSuscribirme(View view) {
        Intent intent = new Intent(this, FormularioSuscribirGimnasio.class);
        startActivity(intent);
    }

    public void redirectToCrearGimnasio(View view) {
        Intent intent = new Intent(this, FormularioCrearGimnasios.class);
        startActivity(intent);
    }
}
