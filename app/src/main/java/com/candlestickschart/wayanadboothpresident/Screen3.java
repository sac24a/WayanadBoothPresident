package com.candlestickschart.wayanadboothpresident;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.candlestickschart.wayanadboothpresident.databinding.ActivityScreen3Binding;
import com.candlestickschart.wayanadboothpresident.databinding.ActivityScreen3BindingImpl;

import java.util.ArrayList;
import java.util.List;

public class Screen3 extends AppCompatActivity {
    RadioButton rbtn1;
    RadioButton rbtn2;
    RadioButton rbtn3;
    RadioButton rbtn4;
    RadioButton rbtn5;
    RadioButton rbtn6;
    RadioButton agrirbtn;
    RadioButton industryrbtn;
    RadioButton servicesrbtn;
    EditText mainCrop;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String string1 = "";
    String string2 = "";
    String string3 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        ActivityScreen3Binding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_screen3);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        Log.d("TAG", "onCreate: "+sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""));
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        rbtn4 = findViewById(R.id.rbtn4);
        rbtn5 = findViewById(R.id.rbtn5);
        rbtn6 = findViewById(R.id.rbtn6);
        agrirbtn = findViewById(R.id.rbtn7);
        industryrbtn = findViewById(R.id.rbtn8);
        servicesrbtn = findViewById(R.id.rbtn9);
        mainCrop = findViewById(R.id.maincrop);
        rbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                string1 = rbtn1.getText().toString();
            }
        } );
        rbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                string1 = rbtn2.getText().toString();
            }
        } );
        rbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                string1 = rbtn3.getText().toString();
            }
        } );
        rbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                string2 = rbtn4.getText().toString();
            }
        } );
        rbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                string2 = rbtn5.getText().toString();
            }
        } );
        rbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                string2 = rbtn6.getText().toString();
            }
        } );
        agrirbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                string3 = agrirbtn.getText().toString();
            }
        } );
        industryrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                string3 = industryrbtn.getText().toString();
            }
        } );
        servicesrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                string3 = servicesrbtn.getText().toString();
            }
        } );
    }
    public void backPressed(View view){
        finish();
    }
    public void forwardPressed(View view){
        if (!string1.equals("") && !string2.equals("") && !string3.equals("") && !mainCrop.getText().toString().equals("")) {

            try {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen3.this);
                            pollFirstDataBase.pollFirstDao().updateScreen3(string1,string2,string3,mainCrop.getText().toString(),sharedPreferences.getString("LOC_ID",""));
                            AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(Screen3.this, MainDashBoard.class);
                                    startActivity(intent);
                                }
                            });

                        }
                        catch (Exception e ) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            catch (NullPointerException e) {

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen3.this);
                        List<PollFirstData> pollFirstData = pollFirstDataBase.pollFirstDao().getPollfirstData();
                        if (pollFirstData.get(0).Locality_type.equals("Urban")) {
                            rbtn1.setChecked(true);
                            string1 = "Urban";
                        }
                        else if (pollFirstData.get(0).Locality_type.equals("Small Town")) {
                            rbtn2.setChecked(true);
                            string1 = "Small Town";
                        }
                        else if (pollFirstData.get(0).Locality_type.equals("Rural")) {
                            rbtn3.setChecked(true);
                            string1 = "Rural";
                        }
                        if (pollFirstData.get(0).Employment.equals("High")) {
                            rbtn4.setChecked(true);
                            string2 = "High";
                        }
                        else if (pollFirstData.get(0).Employment.equals("Medium")) {
                            rbtn5.setChecked(true);
                            string2 = "Medium";
                        }
                        else if (pollFirstData.get(0).Employment.equals("Low")) {
                            rbtn6.setChecked(true);
                            string2 = "Low";
                        }
                        if (pollFirstData.get(0).Economic.equals("Agriculture")) {
                            agrirbtn.setChecked(true);
                            string3 = "Agriculture";
                        }
                        else if (pollFirstData.get(0).Economic.equals("Industry")) {
                            industryrbtn.setChecked(true);
                            string3 = "Industry";
                        }
                        else if (pollFirstData.get(0).Economic.equals("Services")) {
                            servicesrbtn.setChecked(true);
                            string3 = "Services";
                        }
                        mainCrop.setText(pollFirstData.get(0).MainCrop);

                    }
                    catch (Exception e ) {

                    }
                }
            });
        }
        catch (NullPointerException e) {

        }
    }
}