package com.candlestickschart.wayanadboothpresident;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.candlestickschart.wayanadboothpresident.databinding.ActivityBoothAgentBinding;
import com.candlestickschart.wayanadboothpresident.databinding.ActivityReligionBinding;

import java.util.ArrayList;
import java.util.List;

public class ReligionAc extends AppCompatActivity {

    EditText sanstha;
    EditText socialType;
    EditText impPerson;
    EditText mobile;
    SharedPreferences sharedPreferences;
    Button save;
    Button cancel;
    String value = "";
    RadioButton rbtn1;
    RadioButton rbtn2;
    RadioButton rbtn3;
    List<String> politicalWorkerData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_religion);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityReligionBinding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_religion);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        sanstha = findViewById(R.id.sanstha);
        socialType = findViewById(R.id.favparty);
        impPerson = findViewById(R.id.pername);
        mobile = findViewById(R.id.mobile);
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        rbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = rbtn1.getText().toString();
            }
        } );
        rbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = rbtn2.getText().toString();
            }
        } );
        rbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = rbtn3.getText().toString();
            }
        } );

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sanstha.getText().toString().equals("") || socialType.getText().toString().equals("") || impPerson.getText().toString().equals("") || mobile.getText().toString().equals("")) {
                    Toast.makeText(ReligionAc.this,"All Fields are mandatory",Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(ReligionAc.this);
                                    ReligionData pollFirstData = new ReligionData(sharedPreferences.getString("user_id",""),sharedPreferences.getString("user_mobile_no",""),sharedPreferences.getString("LOC_ID",""),sanstha.getText().toString(),value,socialType.getText().toString(),impPerson.getText().toString(),mobile.getText().toString());
                                    politicalWorkerData = pollFirstDataBase.pollFirstDao().checkReligionMobile(mobile.getText().toString());
                                    if (politicalWorkerData.size() == 0) {
                                        pollFirstDataBase.pollFirstDao().insertReligion(pollFirstData);
                                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(ReligionAc.this,"Successfully added",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        finish();
                                    }
                                    else {
                                        pollFirstDataBase.pollFirstDao().updateReligion(sanstha.getText().toString(),value,socialType.getText().toString(),impPerson.getText().toString(),mobile.getText().toString());
                                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(ReligionAc.this,"Successfully added",Toast.LENGTH_SHORT).show();
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
            sanstha.setText(getIntent().getStringExtra("name"));
            impPerson.setText(getIntent().getStringExtra("person"));
            mobile.setText(getIntent().getStringExtra("mobile"));
            socialType.setText(getIntent().getStringExtra("connect"));
            if (getIntent().getStringExtra("impression").equals("High")) {
                rbtn1.setChecked(true);
                value = getIntent().getStringExtra("impression");
            }
            else if (getIntent().getStringExtra("impression").equals("Average")) {
                rbtn2.setChecked(true);
                value = getIntent().getStringExtra("impression");
            }
            else if (getIntent().getStringExtra("impression").equals("Low")) {
                rbtn3.setChecked(true);
                value = getIntent().getStringExtra("impression");
            }
        }catch (NullPointerException e) {
            e.printStackTrace();
        }


    }
}