package com.example.pokemontrackerform;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.table);

        //editTexts
        EditText editTextNationalNumber = findViewById(R.id.editTextNationalNumber);
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextSpecies = findViewById(R.id.editTextSpecies);
        EditText editTextHeight = findViewById(R.id.editTextHeight);
        EditText editTextWeight = findViewById(R.id.editTextWeight);
        EditText editTextHP = findViewById(R.id.editTextHP);
        EditText editTextAttack = findViewById(R.id.editTextAttack);
        EditText editTextDefense = findViewById(R.id.editTextDefense);
        RadioGroup genderGroup = findViewById(R.id.genderGroup);
        Spinner spinnerLevel = findViewById(R.id.spinnerLevel);



        //spinner set up
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.level_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(adapter);
        spinnerLevel.setSelection(0);

        //reset button
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

                // reset spinner
                spinnerLevel.setSelection(0);
            }
        });

        // submit button
        Button saveButton = findViewById(R.id.buttonSave);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = true; //are inputs valid
                StringBuilder errors = new StringBuilder(); //keeps track of errors

                //make sure the name is between 3 and 12 alphabetical characters
                String name = editTextName.getText().toString().trim();
                if (name.isEmpty() || !name.matches("[a-zA-Z]{3,12}")) {
                    editTextName.setError("3-12 letters only");
                    valid = false;
                    errors.append("Name must be 3-12 letters\n");

                }

                //make sure the gender is chosen
                int selectedGenderId = genderGroup.getCheckedRadioButtonId();
                if (selectedGenderId == -1) {
                    valid = false;
                    errors.append("Select a gender\n");
                }

                //make sure HP is between 1 and 362
                try {
                    int hp = Integer.parseInt(editTextHP.getText().toString().trim());
                    if (hp < 1 || hp > 362) {
                        editTextHP.setError("HP must be between 1-362");
                        valid = false;
                        errors.append("HP is out of range\n");
                    }
                } catch (NumberFormatException e) {
                    editTextHP.setError("enter a number");
                    valid = false;
                    errors.append("HP invalid\n");
                }

                //make sure attack must be between 0 and 526
                try {
                    int attack = Integer.parseInt(editTextAttack.getText().toString().trim());
                    if (attack < 0 || attack > 526) {
                        editTextAttack.setError("Attack must be 0-526");
                        valid = false;
                        errors.append("Attack out of range\n");
                    }
                } catch (NumberFormatException e) {
                    editTextAttack.setError("Enter a number");
                    valid = false;
                    errors.append("Attack invalid\n");
                }

                //make sure defense must be between 10 and 614
                try {
                    int defense = Integer.parseInt(editTextDefense.getText().toString().trim());
                    if (defense < 10 || defense > 614) {
                        editTextDefense.setError("Defense must be 10-614");
                        valid = false;
                        errors.append("Defense out of range\n");
                    }
                } catch (NumberFormatException e) {
                    editTextDefense.setError("Enter a number");
                    valid = false;
                    errors.append("Defense invalid\n");
                }

                //make sure height is between 0.2 and 169.99
                try {
                    double height = Double.parseDouble(editTextHeight.getText().toString().trim());
                    if (height < 0.2 || height > 169.99) {
                        editTextHeight.setError("Height must be 0.2-169.99 m");
                        valid = false;
                        errors.append("Height out of range\n");
                    } else {
                        editTextHeight.setText(String.format("%.2f", height));
                    }
                } catch (NumberFormatException e) {
                    editTextHeight.setError("Enter a number");
                    valid = false;
                    errors.append("Height invalid\n");
                }

                //make sure weight is between 0.1 and 992.7
                try {
                    double weight = Double.parseDouble(editTextWeight.getText().toString().trim());
                    if (weight < 0.1 || weight > 992.7) {
                        editTextWeight.setError("Weight must be 0.1-992.7 kg");
                        valid = false;
                        errors.append("Weight out of range\n");
                    } else {
                        editTextWeight.setText(String.format("%.2f", weight));
                    }
                } catch (NumberFormatException e) {
                    editTextWeight.setError("Enter a number");
                    valid = false;
                    errors.append("Weight invalid\n");
                }

                //make sure species contains only letters and spaces
                String speciesInput = editTextSpecies.getText().toString().trim();
                if (!speciesInput.matches("[a-zA-Z ]+")) {
                    editTextSpecies.setError("Only letters and spaces allowed");
                    valid = false;
                    errors.append("Species invalid\n");
                }

                //notify the user by way of a Toast about the errors that occurred
                if (valid) {
                    Toast.makeText(MainActivity.this, "Information stored!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, errors.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}



