package com.candlestickschart.wayanadboothpresident;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.candlestickschart.wayanadboothpresident.databinding.ActivityAddSpecialLetterBinding;

public class AddSpecialLetter extends AppCompatActivity {

    EditText addressto;
    EditText issuedetails;
    EditText address;
    SharedPreferences sharedPreferences;
    Button save;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_special_letter);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityAddSpecialLetterBinding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_special_letter);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        addressto = findViewById(R.id.addressto);
        issuedetails = findViewById(R.id.issuedetails);
        address = findViewById(R.id.address);
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addressto.getText().toString().equals("") || address.getText().toString().equals("") || issuedetails.getText().toString().equals("")) {
                    Toast.makeText(AddSpecialLetter.this,"All Fields are mandatory",Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(AddSpecialLetter.this);
//                                    politicalWorkerData = pollFirstDataBase.pollFirstDao().checkAddSpecialLetterMobile(mobile.getText().toString());
                                    SpecialLetterData pollFirstData = new SpecialLetterData(sharedPreferences.getString("user_id",""),sharedPreferences.getString("user_mobile_no",""),sharedPreferences.getString("LOC_ID",""),addressto.getText().toString(),issuedetails.getText().toString(),address.getText().toString());
                                    pollFirstDataBase.pollFirstDao().insertSpecialLetter(pollFirstData);
                                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(AddSpecialLetter.this,"Successfully added",Toast.LENGTH_SHORT).show();
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