package com.example.workout;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeEventListener;
    private boolean isRunning = false;
    private boolean isCycling = false;
    private boolean isRunningPaused = false;
    private boolean isCyclingPaused = false;
    private int stepsRunningCount = 0;
    private int stepsCyclingCount = 0;
    private int timerRunningCount = 0;
    private int timerCyclingCount = 0;
    private Handler timerHandler = new Handler();
    private Runnable timerRunnable;
    private long lastStepTime = 0;
    private static final long STEP_DELAY_MS = 1000; // 1 second delay between each step

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomePage.this, HomePage.class);

                startActivity(intent);
            }
        });
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomePage.this, Workout.class);

                startActivity(intent);
            }
        });Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomePage.this, Profile.class);

                startActivity(intent);
            }
        });


        // Initialize sensor manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // Check if gyroscope sensor is available
        if (gyroscopeSensor == null) {
            // Gyroscope sensor is not available on this device
            Toast.makeText(this, "Gyroscope sensor is not available on this device", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity
        }

        // Initialize gyroscope event listener
        gyroscopeEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                // Handle gyroscope sensor changes here
                if (!isRunningPaused && !isCyclingPaused) {
                    // Calculate the total change in rotation rate (angular velocity) around the x, y, and z axes
                    float deltaX = event.values[0];
                    float deltaY = event.values[1];
                    float deltaZ = event.values[2];

                    // Calculate the magnitude of the change in rotation rate
                    float deltaMagnitude = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);

                    // Define a threshold for detecting movement
                    float threshold = 0.5f; // Adjust this threshold as needed

                    // If the change in rotation rate exceeds the threshold and sufficient time has passed since the last step
                    if (deltaMagnitude > threshold && System.currentTimeMillis() - lastStepTime > STEP_DELAY_MS) {
                        // Increment the steps count based on the current activity (running or cycling)
                        if (isRunning) {
                            stepsRunningCount++;
                            updateRunningStepsCount(stepsRunningCount);
                        }
                        if (isCycling) {
                            stepsCyclingCount++;
                            updateCyclingStepsCount(stepsCyclingCount);
                        }
                        // Update the last step time
                        lastStepTime = System.currentTimeMillis();
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // Handle accuracy changes if needed
            }
        };

        // Get reference to the Start Running button
        Button startRunningButton = findViewById(R.id.Startrunning_button);
        // Set OnClickListener to start tracking when the button is clicked
        startRunningButton.setOnClickListener(v -> {
            if (!isCycling) {
                isRunning = true;
                isRunningPaused = false; // Resume counting
                stepsRunningCount = 0; // Reset steps count when starting to run
                timerRunningCount = 0; // Reset timer count when starting to run
                updateRunningStepsCount(stepsRunningCount);
                updateRunningTimerCount(timerRunningCount);
                // Register gyroscope sensor listener
                sensorManager.registerListener(gyroscopeEventListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
                // Start timer
                startTimer();
            } else {
                Toast.makeText(this, "Please stop cycling before starting to run", Toast.LENGTH_SHORT).show();
            }
        });

        // Get reference to the Stop Running button
        Button stopRunningButton = findViewById(R.id.Stoprunning_button);
        // Set OnClickListener to stop tracking when the button is clicked
        stopRunningButton.setOnClickListener(v -> {
            isRunningPaused = true; // Pause counting
            isRunning = false; // Stop running
        });

        // Get reference to the Start Cycling button
        Button startCyclingButton = findViewById(R.id.Startcycling_button);
        // Set OnClickListener to start tracking when the button is clicked
        startCyclingButton.setOnClickListener(v -> {
            if (!isRunning) {
                isCycling = true;
                isCyclingPaused = false; // Resume counting
                stepsCyclingCount = 0; // Reset steps count when starting to cycle
                timerCyclingCount = 0; // Reset timer count when starting to cycle
                updateCyclingStepsCount(stepsCyclingCount);
                updateCyclingTimerCount(timerCyclingCount);
                // Register gyroscope sensor listener
                sensorManager.registerListener(gyroscopeEventListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
                // Start timer
                startTimer();
            } else {
                Toast.makeText(this, "Please stop running before starting to cycle", Toast.LENGTH_SHORT).show();
            }
        });

        // Get reference to the Stop Cycling button
        Button stopCyclingButton = findViewById(R.id.StopCycling_button);
        // Set OnClickListener to stop tracking when the button is clicked
        stopCyclingButton.setOnClickListener(v -> {
            isCyclingPaused = true; // Pause counting
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register gyroscope sensor listener
        sensorManager.registerListener(gyroscopeEventListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister gyroscope sensor listener when the activity is paused to conserve resources
        sensorManager.unregisterListener(gyroscopeEventListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister gyroscope sensor listener when the activity is destroyed to conserve resources
        sensorManager.unregisterListener(gyroscopeEventListener);
        // Stop timer
        stopTimer();
    }

    // Method to update the running steps count TextView
    private void updateRunningStepsCount(int count) {
        TextView stepsCountTextView = findViewById(R.id.Stepsrunning_count);
        stepsCountTextView.setText(String.valueOf(count));
    }

    // Method to update the cycling steps count TextView
    private void updateCyclingStepsCount(int count) {
        TextView stepsCountTextView = findViewById(R.id.Stepscycling_count);
        stepsCountTextView.setText(String.valueOf(count));
    }

    // Method to update the running timer count TextView
    private void updateRunningTimerCount(int count) {
        TextView timerCountTextView = findViewById(R.id.Timerrunning_count);
        timerCountTextView.setText(String.valueOf(count));
    }

    // Method to update the cycling timer count TextView
    private void updateCyclingTimerCount(int count) {
        TextView timerCountTextView = findViewById(R.id.Timercycling_count);
        timerCountTextView.setText(String.valueOf(count));
    }

    // Method to start the timer
    private void startTimer() {
        if (timerRunnable == null) {
            timerRunnable = new Runnable() {
                @Override
                public void run() {
                    if ((!isRunningPaused || !isCyclingPaused)) {
                        if (isRunning) {
                            timerRunningCount++;
                            updateRunningTimerCount(timerRunningCount);
                        }
                        if (isCycling) {
                            timerCyclingCount++;
                            updateCyclingTimerCount(timerCyclingCount);
                        }
                    }
                    // Schedule the timer to run again after 1 second (1000 milliseconds)
                    timerHandler.postDelayed(this, 1000);
                }
            };
            timerHandler.postDelayed(timerRunnable, 1000); // Start the timer immediately
        }
    }

    // Method to stop the timer
    private void stopTimer() {
        timerHandler.removeCallbacks(timerRunnable);
    }
}
