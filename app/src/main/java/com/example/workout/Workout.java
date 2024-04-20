package com.example.workout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Workout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView absbutton = findViewById(R.id.absbutton);
        absbutton.setOnClickListener(v -> {
            Intent intent = new Intent(Workout.this, Abs.class); // Ensure correct class name
            startActivity(intent);
        });
        TextView Gym_button = findViewById(R.id.Gym_button);
        absbutton.setOnClickListener(v -> {
            Intent intent = new Intent(Workout.this, Abs.class);
            startActivity(intent);
        });
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(Workout.this, location.class);
            startActivity(intent);

        });
        ImageView Biceps = findViewById(R.id.bicepbutton);
        Biceps.setOnClickListener(v -> {
            Intent intent = new Intent(Workout.this, Biceps.class); // Ensure 'Stats' class is properly defined
            startActivity(intent);
        });
        ImageView legs = findViewById(R.id.legsbutton);
        legs.setOnClickListener(v -> {
            Intent intent = new Intent(Workout.this, Chest.class); // Ensure 'Stats' class is properly defined
            startActivity(intent);
        });


    }
}