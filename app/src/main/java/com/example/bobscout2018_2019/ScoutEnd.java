package com.example.bobscout2018_2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

public class ScoutEnd extends AppCompatActivity {

    private ToggleButton messedUp;
    private ToggleButton unusualMatch;
    private ToggleButton robotTipped;
    private ToggleButton damagedLift;
    private ToggleButton damagedDrivetrain;
    private ToggleButton damagedIntake;
    private ToggleButton playedDefense;
    private ToggleButton pushBot;

    private RadioGroup levelFloor;
    private RadioGroup levelOne;
    private RadioGroup levelTwo;
    private RadioGroup levelThree;
    private RadioButton floor;
    private RadioButton oneLeft;
    private RadioButton oneCenter;
    private RadioButton oneRight;
    private RadioButton twoLeft;
    private RadioButton twoRight;
    private RadioButton threeCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_end);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        messedUp = findViewById(R.id.messedUp);
        unusualMatch = findViewById(R.id.unusualMatch);
        robotTipped = findViewById(R.id.robotTipped);
        damagedDrivetrain = findViewById(R.id.damagedDrivetrain);
        damagedIntake = findViewById(R.id.damagedIntake);
        damagedLift = findViewById(R.id.damagedLift);
        playedDefense = findViewById(R.id.playedDefense);
        pushBot = findViewById(R.id.pushBot);

        levelFloor = findViewById(R.id.levelFloor);
        levelOne = findViewById(R.id.levelOne);
        levelTwo = findViewById(R.id.levelTwo);
        levelThree = findViewById(R.id.levelThree);
        floor = findViewById(R.id.floor);
        oneLeft = findViewById(R.id.oneLeft);
        oneCenter = findViewById(R.id.oneCenter);
        oneRight = findViewById(R.id.oneRight);
        twoLeft = findViewById(R.id.twoLeft);
        twoRight = findViewById(R.id.twoRight);
        threeCenter = findViewById(R.id.threeCenter);

    }

    public void submit(View v) {
        Intent intent = new Intent(this, SubmitData.class);
        Bundle extras = getIntent().getExtras();
        String data = extras.getString("DATA");
        String match = extras.getString("MATCH");
        String team = extras.getString("TEAM");

        int climb = 0;

        if(levelOne.getCheckedRadioButtonId() == oneLeft.getId() || levelOne.getCheckedRadioButtonId() == oneCenter.getId() || levelOne.getCheckedRadioButtonId() == oneRight.getId()){
            climb = 1;
        } else if(levelTwo.getCheckedRadioButtonId() == twoLeft.getId() || levelTwo.getCheckedRadioButtonId() == twoRight.getId()) {
            climb = 2;
        } else if(levelThree.getCheckedRadioButtonId() == threeCenter.getId()) {
            climb = 3;
        } else if(levelFloor.getCheckedRadioButtonId() == floor.getId()) {
            climb = 0;
        }

        extras.putBoolean("DISCARD", messedUp.isChecked());
        extras.putBoolean("UNUSUAL", unusualMatch.isChecked());
        extras.putBoolean("TIPPED", robotTipped.isChecked());
        extras.putBoolean("DAMDRIVE", damagedDrivetrain.isChecked());
        extras.putBoolean("DAMINTAKE", damagedIntake.isChecked());
        extras.putBoolean("DAMLIFT", damagedLift.isChecked());
        extras.putBoolean("DEF", playedDefense.isChecked());
        extras.putBoolean("PUSH", pushBot.isChecked());
        extras.putInt("CLIMBLEVEL", climb);


        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void reset(View v){
        messedUp.setChecked(false);
        unusualMatch.setChecked(false);
        robotTipped.setChecked(false);
        damagedLift.setChecked(false);
        damagedDrivetrain.setChecked(false);
        damagedIntake.setChecked(false);
        playedDefense.setChecked(false);
        pushBot.setChecked(false);
        levelOne.clearCheck();
        levelTwo.clearCheck();
        levelThree.clearCheck();
        levelFloor.clearCheck();
    }

    public void climbRadioLogic(View v){
        if (levelOne.getCheckedRadioButtonId() == v.getId()) {
            levelTwo.clearCheck();
            levelThree.clearCheck();
            levelFloor.clearCheck();
        } else if(levelTwo.getCheckedRadioButtonId() == v.getId()) {
            levelOne.clearCheck();
            levelThree.clearCheck();
            levelFloor.clearCheck();
        } else if(levelThree.getCheckedRadioButtonId() == v.getId()) {
            levelOne.clearCheck();
            levelTwo.clearCheck();
            levelFloor.clearCheck();
        }else if(levelFloor.getCheckedRadioButtonId() == v.getId()) {
            levelOne.clearCheck();
            levelTwo.clearCheck();
            levelThree.clearCheck();
        }
    }
}
