package com.example.splash;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.splash.databinding.ActivityHomeAdministradorBinding;

public class homeAdministrador extends AppCompatActivity {

    ActivityHomeAdministradorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHomeAdministradorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomnavigation.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home) {
                Intent intent = new Intent(homeAdministrador.this, pantallaInicio.class);
                startActivity(intent);
                finish();
            } else if (item.getItemId() == R.id.Config) {
                replaceFragment(new ConfigFragment());
            } else if (item.getItemId() == R.id.Cuenta) {
                replaceFragment(new CuentaFragment());
            } else if (item.getItemId()==R.id.Usuarios) {
                replaceFragment(new ListaFragment());
            }
            return true;
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final ImageView fondo1 = (ImageView) findViewById(R.id.fondo_one);
        final ImageView fondo2 = (ImageView) findViewById(R.id.fondo_two);

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = fondo1.getWidth();
                final float translationX = width * progress;
                fondo1.setTranslationX(translationX);
                fondo2.setTranslationX(translationX - width);
            }
        });
        animator.start();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.AdminFrameLayout,fragment);
        fragmentTransaction.commit();
    }
}