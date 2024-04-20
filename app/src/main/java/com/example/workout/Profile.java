package com.example.workout;

import android.content.Intent;
import android.net.Uri; // Import this to handle URIs
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth; // Import FirebaseAuth

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        View mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        // Set the listener for the health details button
        Button healthDetailsButton = findViewById(R.id.health_details_button);
        healthDetailsButton.setOnClickListener(v -> {
            Intent intent = new Intent(Profile.this, stats.class); // Ensure 'Stats' class is properly defined
            startActivity(intent);
        });

        Button Activecoach = findViewById(R.id.Activecoach); // Ensure this ID matches
        Activecoach.setOnClickListener(v -> {
            Intent openUrlIntent = new Intent(Intent.ACTION_VIEW);
            openUrlIntent.setData(Uri.parse("https://ivory-brynne-83.tiiny.site")); // Corrected to Uri.parse
            startActivity(openUrlIntent); // This should open a browser
        });


        // Listener for the logout button to sign out from Firebase
        Button logout = findViewById(R.id.logout); // Ensure this ID matches
        logout.setOnClickListener(v -> {
            // Sign out the user from Firebase
            FirebaseAuth.getInstance().signOut(); // Firebase logout command
            // Redirect to MainActivity or a login screen
            Intent intent = new Intent(Profile.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Ensures no back-stack
            startActivity(intent);
            finish(); // Close the Profile activity
        });
    }
}
