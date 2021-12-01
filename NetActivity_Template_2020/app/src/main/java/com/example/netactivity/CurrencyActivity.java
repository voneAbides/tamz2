package com.example.netactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class CurrencyActivity extends AppCompatActivity {

    TextView kodBottomTextView;
    TextView statBottomTextView;
    TextView kurzBottomTextView;

    EditText kurzCzkEditText;
    EditText amountEditText;

    ImageView imageFlagExchangeBottom;

    float kurzCzk = 0;
    float amount = 0;
    float oneCoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        kurzCzk = Float.parseFloat(getIntent().getStringExtra("kurz").replace(",", "."));
        amount = Integer.parseInt(getIntent().getStringExtra("mnozstvi"));

        oneCoin = kurzCzk / amount;

        kodBottomTextView = findViewById(R.id.txtKodExchangeBottom);
        statBottomTextView = findViewById(R.id.txtStatExchangeBottom);
        kurzBottomTextView = findViewById(R.id.txtKurzExchangeBottom);

        kurzCzkEditText = findViewById(R.id.EditTextExchangeTop);
        amountEditText = findViewById(R.id.EditTextExchangeBottom);

        imageFlagExchangeBottom = findViewById(R.id.imageFlagExchangeBottom);

        kodBottomTextView.setText(getIntent().getStringExtra("kod"));
        statBottomTextView.setText(getIntent().getStringExtra("stat"));
        kurzBottomTextView.setText(getIntent().getStringExtra("kurz"));

        kurzCzkEditText.setText(Float.toString(kurzCzk));
        amountEditText.setText(Integer.toString((int) amount));

        imageFlagExchangeBottom.setImageResource(getResources().getIdentifier(
                "flag_" + getIntent().getStringExtra("kod").toLowerCase(Locale.ROOT), "drawable", getPackageName()));



            kurzCzkEditText.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(!kurzCzkEditText.getText().toString().equals("")){
                        kurzCzk = Float.parseFloat(kurzCzkEditText.getText().toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (getCurrentFocus() == kurzCzkEditText) {
                        amountEditText.setText(Float.toString(kurzCzk / oneCoin));
                    }
                }
            });



            amountEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(!amountEditText.getText().toString().equals("")){
                        amount = Float.parseFloat(amountEditText.getText().toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if(getCurrentFocus() == amountEditText){
                        kurzCzkEditText.setText(Float.toString(amount * oneCoin));
                    }
                }
            });




    }

}