package com.example.tamz2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    Button activity3Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextLogin);
        activity3Button = findViewById(R.id.activity3Button);

        activity3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String login = editText.getText().toString();
                Toast.makeText(MainActivity.this, login, Toast.LENGTH_LONG).show();
                Log.d("loginClick",login);

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("login", login);

                startActivity(intent);
            }
        });
    }


    public void nextClick(View view) {

        String login = editText.getText().toString();
        Toast.makeText(this, login, Toast.LENGTH_LONG).show();
        Log.d("loginClick",login);

        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("login", login);

        startActivity(intent);


    }
}