package com.example.workout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//learn view binding and data binding
public class stats extends AppCompatActivity { // use proper naming convention for activity name, first letter caption of class name
    EditText nameEditText;
    RadioButton maleRadioButton;
    RadioButton femaleRadioButton;
    SeekBar heightSeekBar;
    TextView heightTextView;
    SeekBar weightSeekBar;
    TextView weightTextView;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_stats);

        nameEditText = findViewById(R.id.Name);
        maleRadioButton = findViewById(R.id.male_button);
        femaleRadioButton = findViewById(R.id.female_button);
        heightSeekBar = findViewById(R.id.Height_seekbar);
        heightTextView = findViewById(R.id.Height);
        weightSeekBar = findViewById(R.id.Weight_seekbar);
        weightTextView = findViewById(R.id.Weight);
        continueButton = findViewById(R.id.button);

        heightSeekBar.setMax(250);
        heightSeekBar.setMin(50);
        weightSeekBar.setMax(250);
        weightSeekBar.setMin(10);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });


        continueButton.setOnClickListener(view -> {

            String enteredName = nameEditText.getText().toString().trim();

            String selectedGender = maleRadioButton.isChecked() ? "Male" : "Female";

            int selectedHeight = heightSeekBar.getProgress();

            int selectedWeight = weightSeekBar.getProgress();

            saveData(enteredName, selectedGender, selectedHeight, selectedWeight);

        });

        Button butto1n = findViewById(R.id.button);
        butto1n.setOnClickListener(v -> {
            Intent intent = new Intent(stats.this, HomePage.class); // Ensure 'Stats' class is properly defined
            startActivity(intent);
        });
        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                heightTextView.setText(progress + " cm");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                weightTextView.setText(progress + " kg");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        maleRadioButton.setOnClickListener(v -> {
            if (femaleRadioButton.isChecked()) {
                femaleRadioButton.setChecked(false);
            }
        });

        femaleRadioButton.setOnClickListener(v -> {
            if (maleRadioButton.isChecked()) {
                maleRadioButton.setChecked(false);
            }
        });
    }

    private void saveData(String name, String gender, int height, int weight) {
        System.out.println(name);
        System.out.println(gender);
        System.out.println( height + " cm");
        System.out.println(weight + " kg");
    }
}
