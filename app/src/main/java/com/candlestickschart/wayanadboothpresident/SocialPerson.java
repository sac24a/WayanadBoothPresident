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
import com.candlestickschart.wayanadboothpresident.databinding.ActivitySocialPersonBinding;

import java.util.ArrayList;
import java.util.List;

public class SocialPerson extends AppCompatActivity {

    Spinner community;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    Spinner spinner;
    Spinner spinner2;
    SharedPreferences sharedPreferences;
    Button save;
    Button cancel;
    List<String> politicalWorkerData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_person);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivitySocialPersonBinding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_social_person);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        community = findViewById(R.id.community);
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);
        editText2 = findViewById(R.id.Personname);
        editText3 = findViewById(R.id.age);
        editText4 = findViewById(R.id.mobile);
        spinner = findViewById(R.id.favparty);
        spinner2 = findViewById(R.id.gender);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText2.getText().toString().equals("") || editText3.getText().toString().equals("") || editText4.getText().toString().equals("") || community.getSelectedItem().toString().equals("Community") || spinner2.getSelectedItem().toString().equals("Gender") || spinner.getSelectedItem().toString().equals("Party Leaning")) {
                    Toast.makeText(SocialPerson.this,"All Fields are mandatory",Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(SocialPerson.this);
                                    politicalWorkerData = pollFirstDataBase.pollFirstDao().checkSocialPersonMobile(editText4.getText().toString());
                                    SocialData pollFirstData = new SocialData(sharedPreferences.getString("user_id",""),sharedPreferences.getString("user_mobile_no",""),sharedPreferences.getString("LOC_ID",""),community.getSelectedItem().toString(),editText2.getText().toString(),spinner2.getSelectedItem().toString(),editText3.getText().toString(),editText4.getText().toString(),spinner.getSelectedItem().toString());
                                    if (politicalWorkerData.size() == 0) {
                                        pollFirstDataBase.pollFirstDao().insertSocialImpPersion(pollFirstData);
                                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(SocialPerson.this,"Successfully added",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        finish();
                                    }
                                    else {
                                        pollFirstDataBase.pollFirstDao().updateSocialPerson(community.getSelectedItem().toString(),editText2.getText().toString(),spinner2.getSelectedItem().toString(),editText3.getText().toString(),editText4.getText().toString(),spinner.getSelectedItem().toString());
                                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(SocialPerson.this,"Successfully added",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        finish();
                                    }

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

        editText2.setText(getIntent().getStringExtra("name"));
        editText3.setText(getIntent().getStringExtra("age"));
        editText4.setText(getIntent().getStringExtra("mobile"));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.social_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        community.setAdapter(adapter);
        String compareValue = getIntent().getStringExtra("community");
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            community.setSelection(spinnerPosition);
        }
        ArrayAdapter<CharSequence> genderadapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        genderadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(genderadapter);
        String compareGender = getIntent().getStringExtra("gender");
        if (compareGender != null) {
            int spinnerPosition = genderadapter.getPosition(compareGender);
            spinner2.setSelection(spinnerPosition);
        }

        ArrayAdapter<CharSequence> partyadapter = ArrayAdapter.createFromResource(this, R.array.fav_party, android.R.layout.simple_spinner_item);
        partyadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(partyadapter);
        String compareParty = getIntent().getStringExtra("party");
        if (compareParty != null) {
            int spinnerPosition = partyadapter.getPosition(compareParty);
            spinner.setSelection(spinnerPosition);
        }
    }
}