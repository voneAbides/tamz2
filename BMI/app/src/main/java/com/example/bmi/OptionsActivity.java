package com.example.bmi;

import static com.example.bmi.MainActivity.RESULT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionsActivity extends AppCompatActivity {

    Button buttonOptionOne;
    Button buttonOptionTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        buttonOptionOne = findViewById(R.id.buttonOptionOne);
        buttonOptionTwo = findViewById(R.id.buttonOptionTwo);

        buttonOptionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(RESULT, 0);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        buttonOptionTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(RESULT, 1);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });


    }
}