package com.example.tamz2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.textView);

        Intent intent = getIntent();
        String messageLogin = intent.getStringExtra("login");

        textView.setText(messageLogin);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setSubtitle("user: " + messageLogin);
    }
}