package com.example.workout;

import android.os.Bundle; // Corrected imports

import android.annotation.SuppressLint;
import android.widget.ImageView; // Corrected imports

import com.bumptech.glide.Glide; // Ensure you have Glide library in your build.gradle
import com.bumptech.glide.request.target.Target;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Abs extends AppCompatActivity { // Corrected class name casing


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Place `super.onCreate` at the start
        EdgeToEdge.enable(this); // Ensure EdgeToEdge is correctly implemented
        setContentView(R.layout.activity_abs);

        // Initialize the ImageViews for loading GIFs
        ImageView gifImage1 = findViewById(R.id.absgif1); // Correct variable name and use camelCase

        Glide
                .with(getApplicationContext())
                .load(R.drawable.flutterkicks) // Ensure `flutterkicks` resource exists
                .centerCrop() // Corrected Glide chaining
                .into(gifImage1); // Correct variable name

        ImageView gifImage2 = findViewById(R.id.absgif2); // Correct variable name and initialization

        Glide
                .with(getApplicationContext())
                .load(R.drawable.kneetouchcrunches) // Ensure this drawable exists
                .centerCrop() // Corrected Glide chaining
                .into(gifImage2); // Correct variable name

        ImageView gifImage3 = findViewById(R.id.absgif3); // Correct variable name and initialization

        Glide
                .with(getApplicationContext())
                .load(R.drawable.russiantwist) // Ensure this drawable exists
                .centerCrop() // Corrected Glide chaining
                .into(gifImage3); // Correct variable name
    }
}
