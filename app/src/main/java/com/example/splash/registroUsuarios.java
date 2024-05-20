package com.example.splash;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.splash.cuentas.cuenta;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registroUsuarios extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Button boton;
    Button seleccionar;
    CharSequence[] gimnasios = {"Fit not fat", "Bestias", "Lobos", "Power Gym", "Pepe's Gym", "FitGym"};
    TextView ngim;

    EditText usuario;
    EditText contra;
    String rango = "usuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_usuarios);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnLogin), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        boton = findViewById(R.id.btnLogin);
        mediaPlayer = MediaPlayer.create(this, R.raw.registrousuario);
        seleccionar = findViewById(R.id.gimnasio);
        ngim = findViewById(R.id.ngym);
        usuario = findViewById(R.id.usuario);
        contra = findViewById(R.id.contraseña);

        seleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(registroUsuarios.this);
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setSingleChoiceItems(gimnasios, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ngim.setText(gimnasios[which]);
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setCancelable(true);
                alertDialog.show();
            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoUsuario = usuario.getText().toString().trim();
                String textoContra = contra.getText().toString().trim();
                String textoNgim = ngim.getText().toString().trim();
                if (!textoUsuario.isEmpty() && !textoContra.isEmpty() && !textoNgim.isEmpty()) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference usersRef = database.getReference("users");
                    String userId = usersRef.push().getKey();
                    if (userId != null) {
                        cuenta gymrat = new cuenta(textoUsuario, textoContra, textoNgim, rango);
                        usersRef.child(userId).setValue(gymrat);
                        mediaPlayer.start();
                        startActivity(new Intent(registroUsuarios.this, homeUsuario.class));
                        finish();
                    } else {
                        Toast.makeText(registroUsuarios.this, "Error al generar el ID del usuario", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(registroUsuarios.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}