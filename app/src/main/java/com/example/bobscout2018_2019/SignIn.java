package com.example.bobscout2018_2019;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class SignIn extends AppCompatActivity {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private EditText nameInput;
    public String currentScouter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        sharedPref = this.getSharedPreferences("BOBScout2018-2019_prefs", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        nameInput = findViewById(R.id.signIn);

        currentScouter = sharedPref.getString("currentScouter", "");
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

            }
        });
    }

    public void continueAfterSignIn(View v) {
        currentScouter = nameInput.getText().toString();

        editor.putString("currentScouter", currentScouter);
        editor.apply();

        Intent intent = new Intent(this, Start.class);
        startActivity(intent);
    }

}
