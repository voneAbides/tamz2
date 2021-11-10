package com.vsb.kru13.sokoban;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    SokoView sokoview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int levelNum = getIntent().getIntExtra("levelNum", 0);

        sokoview = findViewById(R.id.sokoView);
        if(levelNum == 1){
            int[] level = new int[100];
            Arrays.fill(level, 0);
            sokoview.setLevel(level);
        }
    }
}
