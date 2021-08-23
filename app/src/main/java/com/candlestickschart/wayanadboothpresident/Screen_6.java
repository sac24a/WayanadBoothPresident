package com.candlestickschart.wayanadboothpresident;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.candlestickschart.wayanadboothpresident.databinding.ActivityBoothAgentBinding;
import com.candlestickschart.wayanadboothpresident.databinding.ActivityScreen6Binding;

import java.util.List;

public class Screen_6 extends AppCompatActivity {
    RadioButton rbtn1;
    RadioButton rbtn2;
    RadioButton rbtn3;
    RadioButton rbtn4;
    RadioButton rbtn5;
    RadioButton rbtn6;
    RadioButton rbtn7;
    RadioButton rbtn8;
    RadioButton rbtn9;
    RadioButton rbtn10;
    RadioButton rbtn11;
    RadioButton rbtn12;
    RadioButton rbtn13;
    RadioButton rbtn14;
    RadioButton rbtn15;

    String bijli = "";
    String road = "";
    String hospital = "";
    String school ="";
    String water = "";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_6);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityScreen6Binding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_screen_6);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        rbtn4 = findViewById(R.id.rbtn4);
        rbtn5 = findViewById(R.id.rbtn5);
        rbtn6 = findViewById(R.id.rbtn6);
        rbtn7 = findViewById(R.id.rbtn7);
        rbtn8 = findViewById(R.id.rbtn8);
        rbtn9 = findViewById(R.id.rbtn9);
        rbtn10 = findViewById(R.id.rbtn10);
        rbtn11 = findViewById(R.id.rbtn11);
        rbtn12 = findViewById(R.id.rbtn12);
        rbtn13 = findViewById(R.id.rbtn13);
        rbtn14 = findViewById(R.id.rbtn14);
        rbtn15 = findViewById(R.id.rbtn15);
        rbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bijli = rbtn1.getText().toString();
            }
        } );
        rbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bijli = rbtn2.getText().toString();
            }
        } );
        rbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bijli = rbtn3.getText().toString();
            }
        } );
        rbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                road = rbtn4.getText().toString();
            }
        } );
        rbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                road = rbtn5.getText().toString();

            }
        } );
        rbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                road = rbtn6.getText().toString();
            }
        } );

        rbtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                water = rbtn7.getText().toString();
            }
        } );
        rbtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                water = rbtn8.getText().toString();
            }
        } );
        rbtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                water = rbtn9.getText().toString();
            }
        } );
        rbtn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hospital = rbtn10.getText().toString();
            }
        } );
        rbtn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hospital = rbtn11.getText().toString();
            }
        } );
        rbtn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hospital = rbtn12.getText().toString();
            }
        } );
        rbtn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                school = rbtn13.getText().toString();
            }
        } );
        rbtn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                school = rbtn14.getText().toString();
            }
        } );
        rbtn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                school = rbtn15.getText().toString();
            }
        } );

        try {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen_6.this);
                        List<PollFirstData> pollFirstData = pollFirstDataBase.pollFirstDao().getPollfirstData();
                        if (pollFirstData.get(0).Elec_hrs.equals("22hrs+")) {
                            rbtn1.setChecked(true);
                            bijli = pollFirstData.get(0).Elec_hrs;
                        }
                        else if (pollFirstData.get(0).Elec_hrs.equals("12-22hrs")) {
                            rbtn2.setChecked(true);
                            bijli = pollFirstData.get(0).Elec_hrs;
                        }
                        else if (pollFirstData.get(0).Elec_hrs.equals("> 22 hrs")) {
                            rbtn3.setChecked(true);
                            bijli = pollFirstData.get(0).Elec_hrs;
                        }
                        if (pollFirstData.get(0).Roads.equals("Good")) {
                            rbtn4.setChecked(true);
                            road = pollFirstData.get(0).Roads;
                        }
                        else if (pollFirstData.get(0).Roads.equals("Average")) {
                            rbtn5.setChecked(true);
                            road = pollFirstData.get(0).Roads;
                        }
                        else if (pollFirstData.get(0).Roads.equals("Poor")) {
                            rbtn6.setChecked(true);
                            road = pollFirstData.get(0).Roads;
                        }
                        if (pollFirstData.get(0).Water.equals("Good")) {
                            rbtn7.setChecked(true);
                            water = pollFirstData.get(0).Water;
                        }
                        else if (pollFirstData.get(0).Water.equals("Average")) {
                            rbtn8.setChecked(true);
                            water = pollFirstData.get(0).Water;
                        }
                        else if (pollFirstData.get(0).Water.equals("Poor")) {
                            rbtn9.setChecked(true);
                            water = pollFirstData.get(0).Water;
                        }
                        if (pollFirstData.get(0).Hospital.equals("In Area")) {
                            rbtn10.setChecked(true);
                            hospital = pollFirstData.get(0).Hospital;
                        }
                        else if (pollFirstData.get(0).Hospital.equals(">5km")) {
                            rbtn11.setChecked(true);
                            hospital = pollFirstData.get(0).Hospital;
                        }
                        else if (pollFirstData.get(0).Hospital.equals("<5km>")) {
                            rbtn12.setChecked(true);
                            hospital = pollFirstData.get(0).Hospital;
                        }
                        if (pollFirstData.get(0).School.equals("Good")) {
                            rbtn13.setChecked(true);
                            school = pollFirstData.get(0).School;
                        }
                        else if (pollFirstData.get(0).School.equals("Average")) {
                            rbtn14.setChecked(true);
                            school = pollFirstData.get(0).School;
                        }
                        else if (pollFirstData.get(0).School.equals("Poor")) {
                            rbtn15.setChecked(true);
                            school = pollFirstData.get(0).School;
                        }


                    }
                    catch (Exception e ) {

                    }
                }
            });
        }
        catch (NullPointerException e) {

        }
    }

    public void backPressed(View view) {
        finish();
    }
    public void forwardPressed(View view) {
        if (!bijli.equals("") && !road.equals("") && !water.equals("") && !hospital.equals("") && !school.equals("")) {
            try {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen_6.this);
                            pollFirstDataBase.pollFirstDao().updateScreen6(bijli,road,water,hospital,school,sharedPreferences.getString("LOC_ID",""));
                            Intent intent = new Intent(Screen_6.this, Screen_6_1.class);
                            startActivity(intent);

                        }
                        catch (Exception e) {

                        }
                    }
                });
            }
            catch (NullPointerException e) {

            }
        }
        else {
            Toast.makeText(Screen_6.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
        }
    }

}