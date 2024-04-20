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

public class Biceps extends AppCompatActivity {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView gif_image;
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_biceps);
        gif_image = findViewById(R.id.bicepgif1);

        Glide
                .with(getApplicationContext())
                .load(R.drawable.bicepcurl)
                .centerCrop()
                .into((gif_image));
        gif_image = findViewById(R.id.bicepgif2);

        Glide
                .with(getApplicationContext())
                .load(R.drawable.hammercurls)
                .centerCrop()
                .into((gif_image));



    }
}