package com.example.bmi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.number.Precision;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    public static final String BMI_VALUES = "BMIValues";
    public static final String NAME = "name";
    public static final String WEIGHT = "weight";
    public static final String HEIGHT = "height";
    public static final String OPTIONS = "options";
    public static final int SECOND_ACTIVITY = 1;

    EditText editTextName;
    EditText editTextWeight;
    EditText editTextHeight;

    TextView textViewResult;
    TextView textViewBottom;

    Button buttonBMI;

    ImageView imageViewSmile;
    ImageView imageViewSad;

    double maxBMI = 24.9;
    double minBMI = 18.5;

    DecimalFormat dec = new DecimalFormat("#0.00");

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(BMI_VALUES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editTextName = findViewById(R.id.editTextName);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);

        textViewResult = findViewById(R.id.textViewResult);
        textViewBottom = findViewById(R.id.textViewBottom);

        buttonBMI = findViewById(R.id.buttonBMI);

        imageViewSmile = findViewById(R.id.imageViewSmile);
        imageViewSad = findViewById(R.id.imageViewSad);

        imageViewSmile.setImageAlpha(10);
        imageViewSad.setImageAlpha(10);

        loadData();
        loadOptions();

        buttonBMI.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
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

                        textViewBottom.setText("Kategorie: normální");
                        textViewBottom.setTextColor(Color.GREEN);

                    }
                    else if(BMI > maxBMI){
                        imageViewSmile.setImageAlpha(10);
                        imageViewSad.setImageAlpha(255);
                        textViewResult.setText(editTextName.getText().toString() + " má BMI: "
                                + dec.format(BMI));

                        textViewBottom.setText("Kategorie: Nadváha");
                        textViewBottom.setTextColor(Color.RED);
                    }
                    else{
                        imageViewSmile.setImageAlpha(10);
                        imageViewSad.setImageAlpha(255);
                        textViewResult.setText(editTextName.getText().toString() + " má BMI: "
                                + dec.format(BMI));

                        textViewBottom.setText("Kategorie: podváha");
                        textViewBottom.setTextColor(Color.RED);
                    }

                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Zadejte platnou váhu a výšku",
                                    Toast.LENGTH_SHORT).show();

                }
                saveData();
            }
        });
    }

    public double calculateBMI(double weight, double height){
        double BMI = (weight / height / height) * 10000;

        return BMI;
    }

    public void createIntent(Context packageContext, Class<?> cls ){
        Intent intent = new Intent(packageContext, cls);

        startActivity(intent);

    }

    public void saveData(){
        editor.putString(NAME, editTextName.getText().toString());
        editor.putString(WEIGHT, editTextWeight.getText().toString());
        editor.putString(HEIGHT, editTextHeight.getText().toString());

        editor.apply();
    }

    public void loadOptions(){
        if(sharedPreferences.getInt(OPTIONS, -1) == 0){
            imageViewSmile.setImageResource(R.drawable.smile);
            imageViewSad.setImageResource(R.drawable.sad);
        }
        else if(sharedPreferences.getInt(OPTIONS, -1) == 1){
            imageViewSad.setImageResource(R.drawable.sad2);
            imageViewSmile.setImageResource(R.drawable.smile2);
        }
    }

    public void loadData(){
        editTextName.setText(sharedPreferences.getString(NAME, ""));
        editTextWeight.setText(sharedPreferences.getString(WEIGHT, ""));
        editTextHeight.setText(sharedPreferences.getString(HEIGHT, ""));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.info:
                createIntent(this, InfoActivity.class);
                return true;
            case R.id.history:
                return true;
            case R.id.options:

                int LAUNCH_SECOND_ACTIVITY = 1;
                Intent i = new Intent(this, OptionsActivity.class);
                startActivityForResult(i, LAUNCH_SECOND_ACTIVITY);
                return true;
            case R.id.clear:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SECOND_ACTIVITY){
            if(resultCode == Activity.RESULT_OK){
                int result = data.getIntExtra("result", -1);

                if(result == 0){
                    imageViewSmile.setImageResource(R.drawable.smile);
                    imageViewSad.setImageResource(R.drawable.sad);

                }
                else if(result == 1){
                    imageViewSad.setImageResource(R.drawable.sad2);
                    imageViewSmile.setImageResource(R.drawable.smile2);
                }

                editor.putInt(OPTIONS, result);
                editor.apply();
            }
        }
        if(resultCode == Activity.RESULT_CANCELED){

        }

    }
}