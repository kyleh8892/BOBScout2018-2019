package com.example.bobscout2018_2019;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SubmitData extends AppCompatActivity {

    private TextView dataOutput;
    private boolean autoCross;

    private boolean placeCargoCS;
    private boolean placeCargoRS;
    private boolean placeHatchCS;
    private boolean placeHatchRS;
    private boolean pickCargo;
    private boolean pickHatch;

    int hatchDropped;
    int cargoDropped;

    private boolean discard;
    private boolean unusual;
    private boolean tipped;
    private boolean damDrive;
    private boolean damIntake;
    private boolean damLift;
    private boolean def;
    private boolean push;
    private int climb;

    private String matchNum;
    private String teamNum;
    private String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_data);

        dataOutput = findViewById(R.id.dataOutput);
        dataOutput.setMovementMethod(new ScrollingMovementMethod());

        Bundle extras = getIntent().getExtras();

        // Match Setup
        matchNum = extras.getString("MATCH");
        teamNum = extras.getString("TEAM");

        // Auto
        String autoStartLevel = extras.getString("AUTO_START_LEVEL");
        String autoStartPosition = extras.getString("AUTO_START_POSITION");
        autoCross = extras.getBoolean("AUTO_CROSS");

        placeCargoCS = extras.getBoolean("AUTO_PLACE_CARGO_CS");
        placeCargoRS = extras.getBoolean("AUTO_PLACE_CARGO_RS");
        placeHatchCS = extras.getBoolean("AUTO_PLACE_HATCH_CS");
        placeHatchRS = extras.getBoolean("AUTO_PLACE_HATCH_RS");
        pickCargo = extras.getBoolean("AUTO_PICK_CARGO");
        pickHatch = extras.getBoolean("AUTO_PICK_HATCH");

        String auto = autoStartLevel + "," + autoStartPosition + "," + boolToInt(autoCross) + "," + boolToInt(placeCargoCS) + "," + boolToInt(placeCargoRS) + "," + boolToInt(placeHatchCS) + "," + boolToInt(placeHatchRS) + "," + boolToInt(pickCargo) + "," + boolToInt(pickHatch) + ",";

        // Teleop
        hatchDropped = extras.getInt("HATCHESDROPPED");
        cargoDropped = extras.getInt("CARGODROPPED");
        String teleop = extras.getString("DATA") + hatchDropped + ",\n" + cargoDropped + ",\n";

        // End
        discard = extras.getBoolean("DISCARD");
        unusual = extras.getBoolean("UNUSUAL");
        tipped = extras.getBoolean("TIPPED");
        damDrive = extras.getBoolean("DAMDRIVE");
        damIntake = extras.getBoolean("DAMINTAKE");
        damLift = extras.getBoolean("DAMLIFT");
        def = extras.getBoolean("DEF");
        push = extras.getBoolean("PUSH");
        climb = extras.getInt("CLIMBLEVEL");
        String end = boolToInt(discard) + "," + boolToInt(unusual) + "," + boolToInt(tipped) + "," + boolToInt(damDrive) + "," + boolToInt(damIntake) + "," +
                boolToInt(damLift) + "," + boolToInt(def) + "," + boolToInt(push) + "," + climb;

        output = teleop + matchNum + "," + teamNum + "," + "," + "," + auto + end;


        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File dir = new File(Environment.getExternalStorageDirectory(), "/BOBScout2018-2019/Matches/");
            dir.mkdirs();
            File file = new File(dir, matchNum + "_" + teamNum + ".csv");

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, false);
                fileOutputStream.write(output.getBytes());
                fileOutputStream.close();
                Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG).show();
                Log.e("BOBScout", e.getMessage());
            }

        }
        //match, team, autoLevel, autoPosition, ,autoCross, autoPlaceCargoCS, autoPlaceCargoRS, autoPlaceHatchCS, autoPlaceHatchRS, pickCargo, pickHatch, hatchDropped, cargoDropped discard, unusual, tipped, damDrive, damIntake, damLift, climb, def, push, climb
        dataOutput.setText(output);
    }

    public void back(View v) {
        Intent newMatch = new Intent(this, ScoutSetup.class);
        startActivity(newMatch);
    }

    @Override
    public void onBackPressed() {
        back(null);
    }

    private int boolToInt(boolean b) {
        if(b) {
            return 1;
        } else {
            return 0;
        }
    }
}
