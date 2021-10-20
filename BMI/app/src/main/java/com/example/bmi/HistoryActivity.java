package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.bmi.MainActivity.BMI_VALUES;
import static com.example.bmi.MainActivity.HISTORY;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class HistoryActivity extends AppCompatActivity {

    TextView textViewHistory;

    SharedPreferences sharedPreferences;

    Set<String> emptySet = new HashSet<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        textViewHistory = findViewById(R.id.textViewHistory);

        sharedPreferences = getSharedPreferences(BMI_VALUES, Context.MODE_PRIVATE);

        Set<String> history = sharedPreferences.getStringSet(HISTORY, emptySet);

        String makeRows = "";

        for (String row : history
        ) {
            makeRows = makeRows + row + "\n";
        }

        textViewHistory.setText(makeRows);



    }
}