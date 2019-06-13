package com.example.bobscout2018_2019;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class SignIn extends AppCompatActivity {

    private EditText nameInput;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    String currentScouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signIn);

        nameInput = findViewById(R.id.nameInput);

        sharedPref = this.getSharedPreferences("BOBScout2018-2019_prefs", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        currentScouter = sharedPref.getString("currentScouter ", "");

        nameInput.setText(currentScouter);

        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentScouter = nameInput.getText().toString();
                editor.putString("currentScouter", currentScouter);
            }
        });
    }
}
