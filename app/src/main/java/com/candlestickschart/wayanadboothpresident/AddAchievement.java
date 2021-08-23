package com.candlestickschart.wayanadboothpresident;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.candlestickschart.wayanadboothpresident.databinding.ActivityAddAchievementBinding;

import java.util.ArrayList;
import java.util.List;

public class AddAchievement extends AppCompatActivity {

    EditText addressto;
    EditText achievername;
    EditText relation;
    EditText address;
    EditText assistance;
    EditText details;
    SharedPreferences sharedPreferences;
    Button save;
    Button cancel;
    List<String> politicalWorkerData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_achievement);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityAddAchievementBinding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_achievement);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        addressto = findViewById(R.id.addressto);
        achievername = findViewById(R.id.achievername);
        relation = findViewById(R.id.relationachiever);
        address = findViewById(R.id.address);
        details = findViewById(R.id.achievementdetails);
        assistance = findViewById(R.id.assistance);

        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (achievername.getText().toString().equals("") || address.getText().toString().equals("") || addressto.getText().toString().equals("") || relation.getText().toString().equals("") || details.getText().toString().equals("") || assistance.getText().toString().equals("")) {
                    Toast.makeText(AddAchievement.this,"All Fields are mandatory",Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(AddAchievement.this);
//                                    politicalWorkerData = pollFirstDataBase.pollFirstDao().checkAddAchievementMobile(mobile.getText().toString());
                                    AchievementData pollFirstData = new AchievementData(sharedPreferences.getString("user_id",""),sharedPreferences.getString("user_mobile_no",""),sharedPreferences.getString("LOC_ID",""),addressto.getText().toString(),achievername.getText().toString(),relation.getText().toString(),details.getText().toString(),assistance.getText().toString(),address.getText().toString());
                                    pollFirstDataBase.pollFirstDao().insertAchievement(pollFirstData);
                                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(AddAchievement.this,"Successfully added",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    finish();
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
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
    }
}