package com.candlestickschart.wayanadboothpresident;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.candlestickschart.wayanadboothpresident.databinding.ActivityAddWeddingBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddWedding extends AppCompatActivity {

    EditText addressto;
    EditText namebride;
    EditText namebridegroom;
    EditText address;
    Button dow;
    EditText relbride;
    SharedPreferences sharedPreferences;
    Button save;
    Button cancel;
    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wedding);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityAddWeddingBinding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_wedding);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        addressto = findViewById(R.id.addressto);
        namebride = findViewById(R.id.namebride);
        namebridegroom = findViewById(R.id.namebridegroom);
        address = findViewById(R.id.address);
        dow = findViewById(R.id.dow);
        relbride = findViewById(R.id.relbride);
        myCalendar = Calendar.getInstance();
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (namebride.getText().toString().equals("") || address.getText().toString().equals("") || addressto.getText().toString().equals("") || namebridegroom.getText().toString().equals("") || dow.getText().toString().equals("") || relbride.getText().toString().equals("")) {
                    Toast.makeText(AddWedding.this,"All Fields are mandatory",Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(AddWedding.this);
//                                    politicalWorkerData = pollFirstDataBase.pollFirstDao().checkAddWeddingMobile(mobile.getText().toString());
                                    WeddingData pollFirstData = new WeddingData(sharedPreferences.getString("user_id",""),sharedPreferences.getString("user_mobile_no",""),sharedPreferences.getString("LOC_ID",""),namebride.getText().toString(),namebridegroom.getText().toString(),dow.getText().toString(),addressto.getText().toString(),relbride.getText().toString(),address.getText().toString());
                                    pollFirstDataBase.pollFirstDao().insertWedding(pollFirstData);
                                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(AddWedding.this,"Successfully added",Toast.LENGTH_SHORT).show();
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
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat format=new SimpleDateFormat ("yyyy");
                int nowyear=Integer.parseInt (format.format (new Date()));
                int age = nowyear-year;
                Log.d("TAG", "onDateSet: "+age);
                updateLabel(age);

            }

        };
        dow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddWedding.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void updateLabel(int age) {
        String myFormat = "MMM dd, yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//        if (type.equals("dob")) {
//            dob.setText(sdf.format(myCalendar.getTime()));
//        }
//        else {
        dow.setText(sdf.format(myCalendar.getTime()));


    }
}