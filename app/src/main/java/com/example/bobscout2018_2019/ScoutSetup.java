package com.example.bobscout2018_2019;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;

public class ScoutSetup extends AppCompatActivity {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private EditText matchNumBox;
    private EditText teamNumBox;
    private String matchNum;
    private String teamNum;
    private HashMap<String, HashMap<String, String>> schedule;
    private String allianceToScout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_setup);

        sharedPref = this.getSharedPreferences("BOBScout2018-2019_prefs", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        matchNumBox = findViewById(R.id.matchNumBox);
        teamNumBox = findViewById(R.id.teamNumBox);

        matchNumBox.setText(String.valueOf(sharedPref.getInt("match", 1)));
        matchNum = matchNumBox.getText().toString();

        allianceToScout = sharedPref.getString("allianceToScout", "");
        readCSV();
        populateTeam(null);

        matchNumBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                populateTeam(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        allianceToScout = sharedPref.getString("allianceToScout", "");
        readCSV();
        populateTeam(null);
    }

    public void submit(View v) {
        matchNum = matchNumBox.getText().toString();
        teamNum = teamNumBox.getText().toString();
        editor.putInt("match", Integer.parseInt(matchNum) + 1);
        editor.apply();

        File dir = new File(Environment.getExternalStorageDirectory(), "/BOBScout/Matches/");
        File[] files = dir.listFiles();

        if (files == null) {
            begin();
        } else if (matchNum.equals("") || teamNum.equals("")) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("No match/team entered");
            alertDialog.setMessage("You must enter a team and match number!");

            alertDialog.setButton(Dialog.BUTTON_NEUTRAL, "OKIE SRY", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // yell at user
                }
            });
            alertDialog.show();
        } else if (Arrays.asList(files).contains(new File(Environment.getExternalStorageDirectory().toString() + "/BOBScout/Matches/" + matchNum + "_" + teamNum + ".csv"))) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Overwrite match?");
            alertDialog.setMessage("This match has already been scouted. Are you sure you want to redo it?");

            alertDialog.setButton(Dialog.BUTTON_POSITIVE, "Yes, redo", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    begin();
                }
            });

            alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // yell at user
                }
            });
            alertDialog.show();
        } else {
            begin();
        }

    }

    private void begin() {
        Intent intent = new Intent(this, ScoutAuto.class);
        Bundle extras = new Bundle();
        extras.putString("MATCH", matchNum);
        extras.putString("TEAM", teamNum);

        intent.putExtras(extras);
        startActivity(intent);
    }

    public void populateTeam(View v) {
        String match = matchNumBox.getText().toString();
        try {
            teamNum = schedule.get(match).get(allianceToScout);
        } catch (Exception e) {
            teamNum = "";
        }
        teamNumBox.setText(teamNum);
    }

    public void readCSV() {
        schedule = new HashMap<String, HashMap<String, String>>();
        File file = new File(Environment.getExternalStorageDirectory(), "/BOBScout2018-2019/schedule.csv");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            line = br.readLine();

            while(line != null) {
                String[] p = line.split(",");
                schedule.put(p[0], new HashMap<String, String>());
                HashMap<String, String> matchMap = schedule.get(p[0]);
                matchMap.put("red1", p[1]);
                matchMap.put("red2", p[2]);
                matchMap.put("red3", p[3]);
                matchMap.put("blue1", p[4]);
                matchMap.put("blue2", p[5]);
                matchMap.put("blue3", p[6]);

                line = br.readLine();
            }

        } catch (Exception e) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Error reading schedule");
            alertDialog.setMessage("error");
        }
    }
}
