package com.vsb.kru13.sokoban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_activity);
    }

    public void startLevel_1(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("levelNum", 1);
        startActivity(intent);
    }

    public void startLevel_2(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("levelNum", 2);
        startActivity(intent);

    }
}