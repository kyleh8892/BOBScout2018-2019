package com.example.bobscout2018_2019;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ScoutMatch extends Activity {

    private String matchNum;
    private String teamNum;
    private static long startTime;
    private long lastTime;
    private String data;
    private ToggleButton T_L_H_1,M_L_H_1,B_L_H_1,T_R_H_1,M_R_H_1,B_R_H_1,T_L_C_1,M_L_C_1,B_L_C_1,T_R_C_1,M_R_C_1,B_R_C_1; // Far Rocket
    private ToggleButton T_L_H_2,M_L_H_2,B_L_H_2,T_R_H_2,M_R_H_2,B_R_H_2,T_L_C_2,M_L_C_2,B_L_C_2,T_R_C_2,M_R_C_2,B_R_C_2; // Close Rocket
    private ToggleButton F_L_H,F_M_H,F_R_H,F_E_H,F_L_C,F_M_C,F_R_C,F_E_C; // Far Side Cargo Ship
    private ToggleButton C_L_H,C_M_H,C_R_H,C_E_H,C_L_C,C_M_C,C_R_C,C_E_C; // Close Side Cargo Ship
    private ArrayList<ToggleButton> cargoButtons = new ArrayList<>();
    private ArrayList<ToggleButton> hatchButtons = new ArrayList<>();
    private TextView cargoDropped, hatchDropped; // Dropped Counters
    private TextView timer;
    private int hatchDroppedNumber, cargoDroppedNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_match);

        timer = findViewById(R.id.timer);
        lastTime = System.currentTimeMillis();
        startTime = System.currentTimeMillis();

        SharedPreferences sharedPref = this.getSharedPreferences("BOBScout2018-2019_prefs", Context.MODE_PRIVATE);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        matchNum = extras.getString("MATCH");
        teamNum = extras.getString("TEAM");

        data = "";

        hatchDroppedNumber = 0;
        cargoDroppedNumber = 0;

        T_L_H_1 = findViewById(R.id.T_L_H_1);
        M_L_H_1 = findViewById(R.id.M_L_H_1);
        B_L_H_1 = findViewById(R.id.B_L_H_1);
        T_L_C_1 = findViewById(R.id.T_L_C_1);
        M_L_C_1 = findViewById(R.id.M_L_C_1);
        B_L_C_1 = findViewById(R.id.B_L_C_1);

        T_R_H_1 = findViewById(R.id.T_R_H_1);
        M_R_H_1 = findViewById(R.id.M_R_H_1);
        B_R_H_1 = findViewById(R.id.B_R_H_1);
        T_R_C_1 = findViewById(R.id.T_R_C_1);
        M_R_C_1 = findViewById(R.id.M_R_C_1);
        B_R_C_1 = findViewById(R.id.B_R_C_1);


        T_L_H_2 = findViewById(R.id.T_L_H_2);
        M_L_H_2 = findViewById(R.id.M_L_H_2);
        B_L_H_2 = findViewById(R.id.B_L_H_2);
        T_L_C_2 = findViewById(R.id.T_L_C_2);
        M_L_C_2 = findViewById(R.id.M_L_C_2);
        B_L_C_2 = findViewById(R.id.B_L_C_2);

        T_R_H_2 = findViewById(R.id.T_R_H_2);
        M_R_H_2 = findViewById(R.id.M_R_H_2);
        B_R_H_2 = findViewById(R.id.B_R_H_2);
        T_R_C_2 = findViewById(R.id.T_R_C_2);
        M_R_C_2 = findViewById(R.id.M_R_C_2);
        B_R_C_2 = findViewById(R.id.B_R_C_2);


        F_L_H = findViewById(R.id.F_L_H);
        F_M_H = findViewById(R.id.F_M_H);
        F_R_H = findViewById(R.id.F_R_H);
        F_E_H = findViewById(R.id.F_E_H);
        F_L_C = findViewById(R.id.F_L_C);
        F_M_C = findViewById(R.id.F_M_C);
        F_R_C = findViewById(R.id.F_R_C);
        F_E_C = findViewById(R.id.F_E_C);

        C_L_H = findViewById(R.id.C_L_H);
        C_M_H = findViewById(R.id.C_M_H);
        C_R_H = findViewById(R.id.C_R_H);
        C_E_H = findViewById(R.id.C_E_H);
        C_L_C = findViewById(R.id.C_L_C);
        C_M_C = findViewById(R.id.C_M_C);
        C_R_C = findViewById(R.id.C_R_C);
        C_E_C = findViewById(R.id.C_E_C);

        cargoDropped = findViewById(R.id.Cargo_Dropped_Count);
        hatchDropped = findViewById(R.id.Hatches_Dropped_Count);

        hatchButtons.add(T_L_H_1);
        hatchButtons.add(M_L_H_1);
        hatchButtons.add(B_L_H_1);
        cargoButtons.add(T_L_C_1);
        cargoButtons.add(M_L_C_1);
        cargoButtons.add(B_L_C_1);

        hatchButtons.add(T_R_H_1);
        hatchButtons.add(M_R_H_1);
        hatchButtons.add(B_R_H_1);
        cargoButtons.add(T_R_C_1);
        cargoButtons.add(M_R_C_1);
        cargoButtons.add(B_R_C_1);


        hatchButtons.add(T_L_H_2);
        hatchButtons.add(M_L_H_2);
        hatchButtons.add(B_L_H_2);
        cargoButtons.add(T_L_C_2);
        cargoButtons.add(M_L_C_2);
        cargoButtons.add(B_L_C_2);

        hatchButtons.add(T_R_H_2);
        hatchButtons.add(M_R_H_2);
        hatchButtons.add(B_R_H_2);
        cargoButtons.add(T_R_C_2);
        cargoButtons.add(M_R_C_2);
        cargoButtons.add(B_R_C_2);


        hatchButtons.add(F_L_H);
        hatchButtons.add(F_M_H);
        hatchButtons.add(F_R_H);
        hatchButtons.add(F_E_H);
        cargoButtons.add(F_L_C);
        cargoButtons.add(F_M_C);
        cargoButtons.add(F_R_C);
        cargoButtons.add(F_E_C);

        hatchButtons.add(C_L_H);
        hatchButtons.add(C_M_H);
        hatchButtons.add(C_R_H);
        hatchButtons.add(C_E_H);
        cargoButtons.add(C_L_C);
        cargoButtons.add(C_M_C);
        cargoButtons.add(C_R_C);
        cargoButtons.add(C_E_C);


        final Handler handler=new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                double time = (((double)System.currentTimeMillis() - lastTime)/1000);
                BigDecimal cut = new BigDecimal(time);
                BigDecimal floored = cut.setScale(1, BigDecimal.ROUND_DOWN);

                timer.setText("Time: " + floored.toString());
                timer.setTextColor(Color.WHITE);

                hatchDropped.setText(""+hatchDroppedNumber);
                cargoDropped.setText(""+cargoDroppedNumber);

                handler.postDelayed(this,100);
            }
        });

        start();
    }

    @Override
    protected void onResume() {
        data = "";
        super.onResume();
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop(View v) {
        saveData();

        Intent intent = new Intent(this, ScoutEnd.class);
        Bundle extras = getIntent().getExtras();
        extras.putString("MATCH", matchNum);
        extras.putString("TEAM", teamNum);
        extras.putString("DATA", data);
        extras.putLong("STARTTIME", startTime);
        extras.putInt("HATCHESDROPPED", hatchDroppedNumber);
        extras.putInt("CARGODROPPED", cargoDroppedNumber);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void saveData() {
        Bundle extras = getIntent().getExtras();
        String match = extras.getString("MATCH");
        String team = extras.getString("TEAM");

        int totalHatches = 0;
        int totalCargo = 0;

        int totalCargoHeight1 = 0;
        int totalCargoHeight2 = 0;
        int totalCargoHeight3 = 0;

        int totalHatchHeight1 = 0;
        int totalHatchHeight2 = 0;
        int totalHatchHeight3 = 0;

        for (ToggleButton b : hatchButtons) {
            data += match + "," + team + ",";
            data += getID(b.toString()) + ",";
            data += b.isChecked() + "," ;
            data += "\n";
            if(b.isChecked()) {
                totalHatches++;
            }

            String button = getID(b.toString());
            if (button.length() < 6 && b.isChecked()){
                totalHatchHeight1++;
            }else if (button.length() > 6 && b.isChecked()) {
                if (button.contains("T")) {
                    totalHatchHeight3++;
                } else if (button.contains("M")) {
                    totalHatchHeight2++;
                } else if (button.contains("B")) {
                    totalHatchHeight1++;
                }
            }
        }

        for (ToggleButton b : cargoButtons) {
            data += match + "," + team + ",";
            data += getID(b.toString()) + ","; // use this to find the id of the button
            data += b.isChecked() + "," ;
            data += "\n";
            if(b.isChecked()) {
                totalCargo++;
            }

            String button = getID(b.toString());
            if (button.length() < 6 && b.isChecked()){
                totalCargoHeight1++;
            }else if (button.length() > 6 && b.isChecked()) {
                if (button.contains("T")) {
                    totalCargoHeight3++;
                } else if (button.contains("M")) {
                    totalCargoHeight2++;
                } else if (button.contains("B")) {
                    totalCargoHeight1++;
                }
            }
        }

        data += "Total: hatches cargo" + "," + totalHatches + "," + totalCargo + ",\n";

        data += "Total dropped: hatches cargo" + "," + hatchDroppedNumber + "," + cargoDroppedNumber + ",\n";

        data += "Hatch height scores: one two three" + "," + totalHatchHeight1 + "," + totalHatchHeight2 + "," + totalHatchHeight3 + ",\n";

        data += "Cargo height scores: one two three" + "," + totalCargoHeight1 + "," + totalCargoHeight2 + "," + totalCargoHeight3 + ",\n";



    }

    public void increaseHatchDropped(View v){
        hatchDroppedNumber++;
    }
    public void decreaseHatchDropped(View v){
        if(hatchDroppedNumber > 0 ){hatchDroppedNumber--;}
    }
    public void increaseCargoDropped(View v){
        cargoDroppedNumber++;
    }
    public void decreaseCargoDropped(View v){
        if (cargoDroppedNumber > 0 ){cargoDroppedNumber--;}
    }

    public String getID (String fullID){
        String ID = "";
        if(fullID.contains("_1") || fullID.contains("_2")){
            ID = fullID.substring(fullID.indexOf("}") - 7, fullID.indexOf("}"));
        }else{
            ID = fullID.substring(fullID.indexOf("}") - 5, fullID.indexOf("}"));
        }
        return ID;
    }

}
