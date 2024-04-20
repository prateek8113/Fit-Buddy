package com.example.workout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class Chest extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView gif_image;
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chest);
        gif_image = findViewById(R.id.chestgif1);

        Glide
                .with(getApplicationContext())
                .load(R.drawable.squats)
                .centerCrop()
                .into((gif_image));
        gif_image = findViewById(R.id.chestgif2);

        Glide
                .with(getApplicationContext())
                .load(R.drawable.bulgariansquats)
                .centerCrop()
                .into((gif_image));

        gif_image = findViewById(R.id.chestgif3);

        Glide
                .with(getApplicationContext())
                .load(R.drawable.hipthrustgif)
                .centerCrop()
                .into((gif_image));

    }
}