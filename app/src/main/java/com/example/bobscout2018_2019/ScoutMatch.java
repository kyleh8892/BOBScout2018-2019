package com.example.bobscout2018_2019;

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

public class ScoutMatch extends AppCompatActivity {

    private String matchNum;
    private String teamNum;
    private static long startTime;
    private long lastTime;
    private String data;
    private ToggleButton T_L_H_1,M_L_H_1,B_L_H_1,T_R_H_1,M_R_H_1,B_R_H_1,T_L_C_1,M_L_C_1,B_L_C_1,T_R_C_1,M_R_C_1,B_R_C_1; // Far Rocket
    private ToggleButton T_L_H_2,M_L_H_2,B_L_H_2,T_R_H_2,M_R_H_2,B_R_H_2,T_L_C_2,M_L_C_2,B_L_C_2,T_R_C_2,M_R_C_2,B_R_C_2; // Close Rocket
    private ToggleButton F_L_H,F_M_H,F_R_H,F_E_H,F_L_C,F_M_C,F_R_C,F_E_C; // Far Side Cargo Ship
    private ToggleButton C_L_H,C_M_H,C_R_H,C_E_H,C_L_C,C_M_C,C_R_C,C_E_C; // Close Side Cargo Ship
    private Button C_D_Minus,C_D_Plus,H_D_Minus,H_D_Plus; // Dropped Buttons
    private ArrayList<ToggleButton> buttons;
    private TextView cargoDropped, hatchDropped; // Dropped Counters
    private View prevButton;
    private TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_match);

        SharedPreferences sharedPref = this.getSharedPreferences("BOBScout2018-2019_prefs", Context.MODE_PRIVATE);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        matchNum = extras.getString("MATCH");
        teamNum = extras.getString("TEAM");
        startTime = System.currentTimeMillis();
        data = "";

        timer = findViewById(R.id.timer);
        lastTime = System.currentTimeMillis();

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

        buttons.add(T_L_H_1);
        buttons.add(M_L_H_1);
        buttons.add(B_L_H_1);
        buttons.add(T_L_C_1);
        buttons.add(M_L_C_1);
        buttons.add(B_L_C_1);

        buttons.add(T_R_H_1);
        buttons.add(M_R_H_1);
        buttons.add(B_R_H_1);
        buttons.add(T_R_C_1);
        buttons.add(M_R_C_1);
        buttons.add(B_R_C_1);


        buttons.add(T_L_H_2);
        buttons.add(M_L_H_2);
        buttons.add(B_L_H_2);
        buttons.add(T_L_C_2);
        buttons.add(M_L_C_2);
        buttons.add(B_L_C_2);

        buttons.add(T_R_H_2);
        buttons.add(M_R_H_2);
        buttons.add(B_R_H_2);
        buttons.add(T_R_C_2);
        buttons.add(M_R_C_2);
        buttons.add(B_R_C_2);


        buttons.add(F_L_H);
        buttons.add(F_M_H);
        buttons.add(F_R_H);
        buttons.add(F_E_H);
        buttons.add(F_L_C);
        buttons.add(F_M_C);
        buttons.add(F_R_C);
        buttons.add(F_E_C);

        buttons.add(C_L_H);
        buttons.add(C_M_H);
        buttons.add(C_R_H);
        buttons.add(C_E_H);
        buttons.add(C_L_C);
        buttons.add(C_M_C);
        buttons.add(C_R_C);
        buttons.add(C_E_C);

/*
        final Handler handler=new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                double time = (((double)System.currentTimeMillis() - lastTime)/1000);
                BigDecimal cut = new BigDecimal(time);
                BigDecimal floored = cut.setScale(1, BigDecimal.ROUND_DOWN);

                timer.setText("Time: " + floored.toString());

                ToggleButton b = (ToggleButton) prevButton;
                if (b != null && b.isChecked()) {
                    timer.setTextColor(getResources().getColor(R.color.lime));
                } else {
                    timer.setTextColor(Color.WHITE);
                }

                handler.postDelayed(this,100);
            }
        });
*/
        start();
    }

    @Override
    protected void onResume() {

        data = "";
        prevButton = null;
        super.onResume();
    }

    public void start() {
        //startTime = System.currentTimeMillis();
    }

    public void stop(View v) {
        saveData();

        Intent intent = new Intent(this, ScoutEnd.class);
        Bundle extras = getIntent().getExtras();
        extras.putString("MATCH", matchNum);
        extras.putString("TEAM", teamNum);
        extras.putString("DATA", data);
        extras.putLong("STARTTIME", startTime);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void saveData() {
        Bundle extras = getIntent().getExtras();
        String match = extras.getString("MATCH");
        String team = extras.getString("TEAM");

        for (ToggleButton b : buttons) {
            data += match + "," + team + ",";
            data += b.isChecked() + "," ;
            data += "\n";
        }
    }
}
