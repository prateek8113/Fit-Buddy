package com.example.workout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class location extends AppCompatActivity {

    private static final String[] gyms = {"KingFitness Club", "Abs and Curves", "Armour Fitness Studio"};
    private static final String[] longitude = {"77.6167", "77.6134", "77.6032"};
    private static final String[] latitude = {"28.8667", "28.8736", "28.8696"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_location);

        // Find the TextViews by their IDs
        TextView textView5 = findViewById(R.id.textView5);
        TextView textView7 = findViewById(R.id.textView7);
        TextView textView6 = findViewById(R.id.textView6);

        // Set gym names as text for the TextViews
        textView5.setText(gyms[0]);
        textView7.setText(gyms[1]);
        textView6.setText(gyms[2]);

        // Set Click Listeners to open Google Maps with the corresponding coordinates
        textView5.setOnClickListener(v -> openMap(latitude[0], longitude[0]));
        textView7.setOnClickListener(v -> openMap(latitude[1], longitude[1]));
        textView6.setOnClickListener(v -> openMap(latitude[2], longitude[2]));

        // Ensure TextViews are clickable
        textView5.setClickable(true);
        textView7.setClickable(true);
        textView6.setClickable(true);
    }

    private void openMap(String lat, String lon) {
        // Create the URI for Google Maps with the latitude and longitude
        String uri = "geo:" + lat + "," + lon + "?q=" + lat + "," + lon;
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        mapIntent.setPackage("com.google.android.apps.maps"); // Ensure Google Maps is used

            startActivity(mapIntent); // Start the intent if it can be resolved

    }
}
