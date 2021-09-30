package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.number.Precision;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    EditText editTextName;
    EditText editTextWeight;
    EditText editTextHeight;

    TextView textViewResult;

    Button buttonBMI;

    ImageView imageViewSmile;
    ImageView imageViewSad;

    double maxBMI = 24.9;
    double minBMI = 18.5;

    DecimalFormat dec = new DecimalFormat("#0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);

        textViewResult = findViewById(R.id.textViewResult);

        buttonBMI = findViewById(R.id.buttonBMI);

        imageViewSmile = findViewById(R.id.imageViewSmile);
        imageViewSad = findViewById(R.id.imageViewSad);

        imageViewSmile.setImageAlpha(10);
        imageViewSad.setImageAlpha(10);

        buttonBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double BMI = 0;

                try {
                    BMI = calculateBMI(Double.parseDouble(editTextWeight.getText().toString()),
                            Double.parseDouble(editTextHeight.getText().toString()));

                    if(BMI < maxBMI && BMI > minBMI){

                        imageViewSad.setImageAlpha(10);
                        imageViewSmile.setImageAlpha(255);
                        textViewResult.setText(editTextName.getText().toString() + " má BMI: "
                                + dec.format(BMI));

                    }
                    else if(BMI > maxBMI){
                        imageViewSmile.setImageAlpha(10);
                        imageViewSad.setImageAlpha(255);
                        textViewResult.setText(editTextName.getText().toString() + " má BMI: "
                                + dec.format(BMI));
                    }
                    else{
                        imageViewSmile.setImageAlpha(10);
                        imageViewSad.setImageAlpha(255);
                        textViewResult.setText(editTextName.getText().toString() + " má BMI: "
                                + dec.format(BMI));
                    }

                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Zadejte platnou váhu a výšku", Toast.LENGTH_SHORT).show();

                }


            }
        });




    }

    public double calculateBMI(double weight, double height){
        double BMI = (weight / height / height) * 10000;

        return BMI;
    }
}