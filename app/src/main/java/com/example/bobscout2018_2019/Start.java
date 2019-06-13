package com.example.bobscout2018_2019;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Start extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private TextView scouterOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        scouterOutput = findViewById(R.id.currentScouter);

        sharedPref = this.getSharedPreferences("BOBScout2018-2019_prefs", Context.MODE_PRIVATE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        scouterOutput.setText("Currently Scouting" + sharedPref.getString("currentScouter", ""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.matchsetupmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(this, Settings.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    public void signIn(View v) {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }

    public void scoutAuto(View v){

        if(sharedPref.getString("currentScouter", "") != ""){
            Intent intent = new Intent(this, ScoutAuto.class);
            startActivity(intent);
        }else if (sharedPref.getString("currentScouter", "") == ""){
            Intent intent = new Intent(this, SignIn.class);
            startActivity(intent);
        }

    }

}
