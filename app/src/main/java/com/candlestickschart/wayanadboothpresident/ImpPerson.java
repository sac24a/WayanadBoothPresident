package com.candlestickschart.wayanadboothpresident;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.candlestickschart.wayanadboothpresident.databinding.ActivityBoothAgentBinding;
import com.candlestickschart.wayanadboothpresident.databinding.ActivityImpPersonBinding;

import java.util.ArrayList;
import java.util.List;

public class ImpPerson extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    Spinner spinner;
    Spinner spinner2;
    Spinner spinner3;
    RadioButton rbtn1;
    RadioButton rbtn2;
    RadioButton rbtn3;
    SharedPreferences sharedPreferences;
    Button save;
    Button cancel;
    String value = "";
    List<String> politicalWorkerData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imp_person);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityImpPersonBinding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_imp_person);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        editText = findViewById(R.id.whyImp);
        editText2 = findViewById(R.id.editText);
        editText3 = findViewById(R.id.age);
        editText4 = findViewById(R.id.mobile);
        spinner = findViewById(R.id.samaj);
        spinner2 = findViewById(R.id.gender);
        spinner3 = findViewById(R.id.favparty);
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);

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
                if (editText.getText().toString().equals("") || editText2.getText().toString().equals("") || editText3.getText().toString().equals("") || editText4.getText().toString().equals("") || spinner.getSelectedItem().toString().equals("समुदाय - समाज") || spinner2.getSelectedItem().toString().equals("लिंग") || spinner3.getSelectedItem().toString().equals("किस पार्टी की तरफ़ झुकाव है")) {
                    Toast.makeText(ImpPerson.this,"All Fields are mandatory",Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(ImpPerson.this);
                                    politicalWorkerData = pollFirstDataBase.pollFirstDao().checkImpPersonMobile(editText4.getText().toString());
                                    ImpPersonData pollFirstData = new ImpPersonData(sharedPreferences.getString("user_id",""),sharedPreferences.getString("user_mobile_no",""),sharedPreferences.getString("LOC_ID",""),editText2.getText().toString(),spinner2.getSelectedItem().toString(),editText3.getText().toString(),spinner.getSelectedItem().toString(),editText4.getText().toString(),editText.getText().toString(),spinner3.getSelectedItem().toString(),value);

                                    if (politicalWorkerData.size() == 0) {
                                        pollFirstDataBase.pollFirstDao().insertImpPerson(pollFirstData);
                                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(ImpPerson.this,"Successfully added",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        finish();
                                    }
                                    else {
                                        pollFirstDataBase.pollFirstDao().updateImpWorker(spinner3.getSelectedItem().toString(),editText2.getText().toString(),spinner2.getSelectedItem().toString(),editText3.getText().toString(),editText4.getText().toString(),spinner.getSelectedItem().toString(),value,editText.getText().toString());
                                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(ImpPerson.this,"Successfully added",Toast.LENGTH_SHORT).show();
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
            editText2.setText(getIntent().getStringExtra("name"));
            editText3.setText(getIntent().getStringExtra("age"));
            editText4.setText(getIntent().getStringExtra("mobile"));
            editText.setText(getIntent().getStringExtra("reason"));
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.social_type, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            String compareValue = getIntent().getStringExtra("social");
            if (compareValue != null) {
                int spinnerPosition = adapter.getPosition(compareValue);
                spinner.setSelection(spinnerPosition);
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
            spinner3.setAdapter(partyadapter);
            String compareParty = getIntent().getStringExtra("partyname");
            if (compareParty != null) {
                int spinnerPosition = partyadapter.getPosition(compareParty);
                spinner3.setSelection(spinnerPosition);
            }

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