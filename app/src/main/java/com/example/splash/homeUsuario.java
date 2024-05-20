package com.example.splash;

import android.animation.ValueAnimator;
<<<<<<< HEAD
import android.content.Intent;
=======
>>>>>>> 7f9da0d88f64f1d40316413ba6d76b3ee73da4da
import android.os.Bundle;
import android.widget.ImageView;
import android.view.animation.LinearInterpolator;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.splash.databinding.ActivityHomeUsuarioBinding;

public class homeUsuario extends AppCompatActivity {

    ActivityHomeUsuarioBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHomeUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomnavigation.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home) {
                Intent intent = new Intent(homeUsuario.this, pantallaInicio.class);
                startActivity(intent);
                finish();
            } else if (item.getItemId() == R.id.Config) {
                replaceFragment(new ConfigFragment());
            } else if (item.getItemId() == R.id.Cuenta) {
                replaceFragment(new CuentaFragment());
            }
            return true;
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

<<<<<<< HEAD
        final ImageView backgroundOne = (ImageView) findViewById(R.id.background_one);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_two);
=======
        final ImageView fondo1 = (ImageView) findViewById(R.id.fondo1);
        final ImageView fondo2 = (ImageView) findViewById(R.id.fondo2);
>>>>>>> 7f9da0d88f64f1d40316413ba6d76b3ee73da4da

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
<<<<<<< HEAD
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                final float translationX = width * progress;
                backgroundOne.setTranslationX(translationX);
                backgroundTwo.setTranslationX(translationX - width);
            }
        });
        animator.start();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayout,fragment);
        fragmentTransaction.commit();
=======
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                final float progress=(float) animation.getAnimatedValue();
                final float width=fondo1.getWidth();
                final float translationX=width*progress;
                fondo1.setTranslationX(translationX);
                fondo2.setTranslationX(translationX-width);
            }
        });
        animator.start();
>>>>>>> 7f9da0d88f64f1d40316413ba6d76b3ee73da4da
    }
}