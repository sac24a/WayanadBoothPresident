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
import com.candlestickschart.wayanadboothpresident.databinding.ActivitySocialMediaBinding;

import java.util.ArrayList;
import java.util.List;

public class SocialMediaAc extends AppCompatActivity {

    EditText names;
    EditText age;
    EditText mobile;
    Spinner gender;
    Spinner socialType;

    SharedPreferences sharedPreferences;
    Button save;
    Button cancel;
    EditText rbtn1;
    EditText rbtn2;
    EditText rbtn3;
    EditText rbtn4;
    List<String> politicalWorkerData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivitySocialMediaBinding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_social_media);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        names = findViewById(R.id.names);
        age = findViewById(R.id.age);
        mobile = findViewById(R.id.mobile);
        gender = findViewById(R.id.gender);
        socialType = findViewById(R.id.samaj);

        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        rbtn4 = findViewById(R.id.rbtn4);
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (names.getText().toString().equals("") || age.getText().toString().equals("") || mobile.getText().toString().equals("") || socialType.getSelectedItem().toString().equals("समुदाय - समाज") || gender.getSelectedItem().toString().equals("लिंग")) {
                    Toast.makeText(SocialMediaAc.this,"All Fields are mandatory",Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(SocialMediaAc.this);
                                    politicalWorkerData = pollFirstDataBase.pollFirstDao().checkSocialMediaMobile(mobile.getText().toString());
                                    SocialMediaData pollFirstData = new SocialMediaData(sharedPreferences.getString("user_id",""),sharedPreferences.getString("user_mobile_no",""),sharedPreferences.getString("LOC_ID",""),names.getText().toString(),gender.getSelectedItem().toString(),age.getText().toString(),socialType.getSelectedItem().toString(),mobile.getText().toString(),rbtn1.getText().toString(),rbtn2.getText().toString(),rbtn3.getText().toString(),rbtn4.getText().toString());
                                    if (politicalWorkerData.size() == 0) {
                                        pollFirstDataBase.pollFirstDao().insertSocialMedia(pollFirstData);
                                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(SocialMediaAc.this,"Successfully added",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        finish();
                                    }
                                    else {
                                        pollFirstDataBase.pollFirstDao().updateSocialMedia(names.getText().toString(),gender.getSelectedItem().toString(),age.getText().toString(),socialType.getSelectedItem().toString(),mobile.getText().toString(),"",rbtn1.getText().toString(),rbtn2.getText().toString(),rbtn4.getText().toString(),rbtn3.getText().toString());
                                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(SocialMediaAc.this,"Successfully added",Toast.LENGTH_SHORT).show();
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
            names.setText(getIntent().getStringExtra("name"));
            age.setText(getIntent().getStringExtra("age"));
            mobile.setText(getIntent().getStringExtra("mobile"));
            rbtn1.setText(getIntent().getStringExtra("whatsapp"));
            rbtn2.setText(getIntent().getStringExtra("facebook"));
            rbtn3.setText(getIntent().getStringExtra("insta"));
            rbtn4.setText(getIntent().getStringExtra("twitter"));
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.social_type, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            socialType.setAdapter(adapter);
            String compareValue = getIntent().getStringExtra("social");
            if (compareValue != null) {
                int spinnerPosition = adapter.getPosition(compareValue);
                socialType.setSelection(spinnerPosition);
            }
            ArrayAdapter<CharSequence> genderadapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
            genderadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            gender.setAdapter(genderadapter);
            String compareGender = getIntent().getStringExtra("gender");
            if (compareGender != null) {
                int spinnerPosition = genderadapter.getPosition(compareGender);
                gender.setSelection(spinnerPosition);
            }

        }catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}