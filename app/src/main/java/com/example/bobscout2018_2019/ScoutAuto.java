package com.example.bobscout2018_2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ScoutAuto extends AppCompatActivity {

    private ToggleButton placeHatchCS;
    private ToggleButton placeCargoCS;
    private ToggleButton placeHatchRS;
    private ToggleButton placeCargoRS;
    private ToggleButton autoCross;
    private ToggleButton pickHatch;
    private ToggleButton pickCargo;
    private TextView currentlyScouting;
    private RadioGroup autoStartPosotion;
    private RadioGroup autoStartLevel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_auto);

        placeCargoCS = findViewById(R.id.placeCargoCS);
        placeCargoRS = findViewById(R.id.placeCargoRS);
        placeHatchCS = findViewById(R.id.placeHatchCS);
        placeHatchRS = findViewById(R.id.placeHatchRS);

        pickCargo = findViewById(R.id.pickCargo);
        pickHatch = findViewById(R.id.pickHatch);

        autoCross = findViewById(R.id.autoCross);

        currentlyScouting = findViewById(R.id.currentlyScouting);

        autoStartLevel = findViewById(R.id.autoStartHeight);
        autoStartPosotion = findViewById(R.id.autoStartPos);

        Bundle extras = getIntent().getExtras();
        currentlyScouting.setText(Html.fromHtml("You are scouting Team "
                                                    + "<font color=\"#009dff\">" + extras.get("TEAM") + "</font>"
                                                    + " (Match " + extras.get("MATCH") + ")"));
    }

    public void startTeleop(View v){
        Intent intent = new Intent(this, ScoutMatch.class);
        Bundle extras = getIntent().getExtras();

        try{
            RadioButton startLevel = autoStartLevel.findViewById(autoStartLevel.getCheckedRadioButtonId());
            extras.putString("AUTO_START_LEVEL", startLevel.getText().toString().substring(startLevel.toString().length()));

            RadioButton startPosition = autoStartPosotion.findViewById(autoStartPosotion.getCheckedRadioButtonId());
            extras.putString("AUTO_START_POSITION", startPosition.getText().toString().substring(startPosition.toString().length()));
        }catch (NullPointerException e){
            extras.putString("AUTO_START_LEVEL", "null");
            extras.putString("AUTO_START_POSITION", "null");
        }

        extras.putBoolean("AUTO_CROSS", autoCross.isChecked());
        extras.putBoolean("AUTO_PLACE_CARGO_CS", placeCargoCS.isChecked());
        extras.putBoolean("AUTO_PLACE_CARGO_RS", placeCargoRS.isChecked());
        extras.putBoolean("AUTO_PLACE_HATCH_CS", placeHatchCS.isChecked());
        extras.putBoolean("AUTO_PLACE_HATCH_RS", placeHatchRS.isChecked());
        extras.putBoolean("AUTO_PICK_CARGO", pickCargo.isChecked());
        extras.putBoolean("AUTO_PICK_HATCH", pickHatch.isChecked());
        intent.putExtras(extras);

        startActivity(intent);
    }
}
