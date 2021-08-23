package com.candlestickschart.wayanadboothpresident;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.candlestickschart.wayanadboothpresident.databinding.ActivityBoothAgentBinding;
import com.candlestickschart.wayanadboothpresident.databinding.ActivityMainDashBoardBinding;

import java.util.ArrayList;
import java.util.List;

public class BoothAgentAc extends AppCompatActivity {

    EditText boothNumber;
    EditText partyName;
    EditText agentName;
    Spinner socialType;
    Spinner gender;
    EditText age;
    EditText mobile;
    SharedPreferences sharedPreferences;
    Button save;
    Button cancel;
    List<String> politicalWorkerData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booth_agent);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityBoothAgentBinding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_booth_agent);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        boothNumber = findViewById(R.id.boothnum);
        partyName = findViewById(R.id.partyname);
        age = findViewById(R.id.age);
        agentName = findViewById(R.id.agentname);
        socialType = findViewById(R.id.socialtype);
        gender = findViewById(R.id.gender);
        mobile = findViewById(R.id.mobile);
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);

        partyName.setText(getIntent().getStringExtra("party"));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boothNumber.getText().toString().equals("") || partyName.getText().toString().equals("") || age.getText().toString().equals("") || agentName.getText().toString().equals("") || socialType.getSelectedItem().toString().equals("समुदाय - समाज") || gender.getSelectedItem().toString().equals("लिंग") || mobile.getText().toString().equals("")) {
                    Toast.makeText(BoothAgentAc.this,"All Fields are mandatory",Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(BoothAgentAc.this);
                                    BoothAgent pollFirstData = new BoothAgent(sharedPreferences.getString("user_id",""),sharedPreferences.getString("user_mobile_no",""),sharedPreferences.getString("LOC_ID",""),boothNumber.getText().toString(),partyName.getText().toString(),agentName.getText().toString(),socialType.getSelectedItem().toString(),gender.getSelectedItem().toString(),age.getText().toString(),mobile.getText().toString());
                                    politicalWorkerData = pollFirstDataBase.pollFirstDao().checkBoothAgentMobile(mobile.getText().toString());
                                    if (politicalWorkerData.size() == 0) {
                                        pollFirstDataBase.pollFirstDao().insertBoothAgent(pollFirstData);
                                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(BoothAgentAc.this,"Successfully added",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        finish();
                                    }
                                    else {
                                        pollFirstDataBase.pollFirstDao().updateBoothAgent(boothNumber.getText().toString(),partyName.getText().toString(),agentName.getText().toString(),gender.getSelectedItem().toString(),age.getText().toString(),mobile.getText().toString(),socialType.getSelectedItem().toString());
                                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(BoothAgentAc.this,"Successfully added",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        finish();
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


            }

        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });

        try {
            boothNumber.setText(getIntent().getStringExtra("booth"));
            agentName.setText(getIntent().getStringExtra("name"));
            age.setText(getIntent().getStringExtra("age"));
            mobile.setText(getIntent().getStringExtra("mobile"));
            partyName.setText(getIntent().getStringExtra("party"));
            ArrayAdapter<CharSequence> genderadapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
            genderadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            gender.setAdapter(genderadapter);
            String compareGender = getIntent().getStringExtra("gender");
            if (compareGender != null) {
                int spinnerPosition = genderadapter.getPosition(compareGender);
                gender.setSelection(spinnerPosition);
            }

            ArrayAdapter<CharSequence> partyadapter = ArrayAdapter.createFromResource(this, R.array.fav_party, android.R.layout.simple_spinner_item);
            partyadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            socialType.setAdapter(partyadapter);
            String compareParty = getIntent().getStringExtra("partyname");
            if (compareParty != null) {
                int spinnerPosition = partyadapter.getPosition(compareParty);
                socialType.setSelection(spinnerPosition);
            }

        }catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}