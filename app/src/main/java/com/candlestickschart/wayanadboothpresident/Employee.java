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
import com.candlestickschart.wayanadboothpresident.databinding.ActivityEmployeeBinding;

import java.util.ArrayList;
import java.util.List;

public class Employee extends AppCompatActivity {

    EditText names;
    EditText age;
    EditText mobile;
    Spinner gender;
    Spinner socialType;
    Spinner post;
    SharedPreferences sharedPreferences;
    Button save;
    Button cancel;
    List<String> politicalWorkerData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityEmployeeBinding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_employee);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        names = findViewById(R.id.empname);
        age = findViewById(R.id.age);
        mobile = findViewById(R.id.mobile);
        gender = findViewById(R.id.gender);
        socialType = findViewById(R.id.socialtype);
        post = findViewById(R.id.post);

        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (names.getText().toString().equals("") || age.getText().toString().equals("") || mobile.getText().toString().equals("") || socialType.getSelectedItem().toString().equals("समुदाय - समाज") || gender.getSelectedItem().toString().equals("लिंग") || post.getSelectedItem().toString().equals("पद")) {
                    Toast.makeText(Employee.this,"All Fields are mandatory",Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Employee.this);
                                    politicalWorkerData = pollFirstDataBase.pollFirstDao().checkEmployeeMobile(mobile.getText().toString());
                                    EmployeeData pollFirstData = new EmployeeData(sharedPreferences.getString("user_id",""),sharedPreferences.getString("user_mobile_no",""),sharedPreferences.getString("LOC_ID",""),names.getText().toString(),gender.getSelectedItem().toString(),age.getText().toString(),socialType.getSelectedItem().toString(),mobile.getText().toString(),post.getSelectedItem().toString());
                                    if (politicalWorkerData.size() == 0) {
                                        pollFirstDataBase.pollFirstDao().insertEmployee(pollFirstData);
                                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(Employee.this,"Successfully added",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        finish();
                                    }
                                    else {
                                        pollFirstDataBase.pollFirstDao().updateEmployee(names.getText().toString(),gender.getSelectedItem().toString(),age.getText().toString(),socialType.getSelectedItem().toString(),mobile.getText().toString(),post.getSelectedItem().toString());
                                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(Employee.this,"Successfully added",Toast.LENGTH_SHORT).show();
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
        names.setText(getIntent().getStringExtra("name"));
        age.setText(getIntent().getStringExtra("age"));
        mobile.setText(getIntent().getStringExtra("mobile"));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.emp_post, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        post.setAdapter(adapter);
        String compareValue = getIntent().getStringExtra("post");
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            post.setSelection(spinnerPosition);
        }
        ArrayAdapter<CharSequence> genderadapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        genderadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderadapter);
        String compareGender = getIntent().getStringExtra("gender");
        if (compareGender != null) {
            int spinnerPosition = genderadapter.getPosition(compareGender);
            gender.setSelection(spinnerPosition);
        }

        ArrayAdapter<CharSequence> partyadapter = ArrayAdapter.createFromResource(this, R.array.social_type, android.R.layout.simple_spinner_item);
        partyadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        socialType.setAdapter(partyadapter);
        String compareParty = getIntent().getStringExtra("social");
        if (compareParty != null) {
            int spinnerPosition = partyadapter.getPosition(compareParty);
            socialType.setSelection(spinnerPosition);
        }

    }
}