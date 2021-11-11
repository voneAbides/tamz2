package com.vsb.kru13.sokoban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    HashMap<Character, Integer> symbols = new HashMap<>();
    List<int[]> parsedLevels;

    SokoView sokoview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        symbols.put(' ', 0);
        symbols.put('#', 1);
        symbols.put('$', 2);
        symbols.put('.', 3);
        symbols.put('@', 4);
        symbols.put('+', 4);
        symbols.put('*', 2);

        int levelNum = getIntent().getIntExtra("levelNum", 0);

        sokoview = findViewById(R.id.sokoView);
        parsedLevels = levelsParser("levels.txt");
        if(levelNum == 1){
            int[] level = new int[150];
            Arrays.fill(level, 2);
            sokoview.setLevel(parsedLevels.get(0));


        }
    }

    public ArrayList<int[]> levelsParser(String levelsFile){
        AssetManager assetManager = getAssets();
        InputStream input;
        String levelsString = "";
        ArrayList<int[]> levels = new ArrayList<>();
        int[] levelArray;

        try {
            input = assetManager.open(levelsFile);

            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();

            levelsString = new String(buffer);

//            Log.i("Levels", levelsString);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(levelsString);
        StringBuilder oneLevel = new StringBuilder();

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Log.i("lineLength", String.valueOf(line.length()));
            if(line.contains("Level")){
               if(oneLevel.capacity() != 0 && !line.contains("Level 1")){
                   levelArray = new int[oneLevel.length() + 1];
                   for (int i = 0; i < oneLevel.length(); i++) {
                        levelArray[i] = symbols.get(oneLevel.charAt(i));
                   }
                   levels.add(levelArray);
                   oneLevel.setLength(0);
               }
            }
            else{
                oneLevel.append(line);
            }
        }
        scanner.close();
        Log.i("LoadedLevels", Arrays.toString(levels.get(0)));
        return levels;
    }
}
