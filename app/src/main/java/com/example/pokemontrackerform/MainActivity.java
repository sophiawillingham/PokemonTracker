package com.example.pokemontrackerform;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //allow the text to be edited
        EditText editTextNationalNumber = findViewById(R.id.editTextNationalNumber);
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextSpecies = findViewById(R.id.editTextSpecies);
        EditText editTextHeight = findViewById(R.id.editTextHeight);
        EditText editTextWeight = findViewById(R.id.editTextWeight);
        EditText editTextHP = findViewById(R.id.editTextHP);
        EditText editTextAttack = findViewById(R.id.editTextAttack);
        EditText editTextDefense = findViewById(R.id.editTextDefense);

        //set the reset button
        Button buttonReset = findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNationalNumber.setText("896");
                editTextName.setText("Glastrier");
                editTextSpecies.setText("Wild Horse");
                editTextHeight.setText("2.2");
                editTextWeight.setText("800.0");
                editTextHP.setText("0");
                editTextAttack.setText("0");
                editTextDefense.setText("0");
            }
        });

        //set up the spinner
        Spinner spinnerLevel = findViewById(R.id.spinnerLevel);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.level_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(adapter);

        // make sure the species allow only letters and spaces
        String speciesInput = editTextSpecies.getText().toString().trim();
        if (!speciesInput.matches("[a-zA-Z ]+")) {
            editTextSpecies.setError("Only letters and spaces allowed");
        }

        //limit height to decimal
        String heightStr = editTextHeight.getText().toString().trim();
        if (!heightStr.isEmpty()) {
            double height = Double.parseDouble(heightStr);
            editTextHeight.setText(String.format("%.2f", height));
        } else {
            editTextHeight.setError("Enter a number");
        }

        //limit weight to decimal
        String weightStr = editTextWeight.getText().toString().trim();
        if (!weightStr.isEmpty()) {
            double weight = Double.parseDouble(weightStr);
            editTextWeight.setText(String.format("%.2f", weight));
        } else {
            editTextWeight.setError("Enter a number");
        }
    }
}


