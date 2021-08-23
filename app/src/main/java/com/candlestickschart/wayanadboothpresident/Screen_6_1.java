package com.candlestickschart.wayanadboothpresident;

import android.content.Intent;
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
import com.candlestickschart.wayanadboothpresident.databinding.ActivityScreen61Binding;

import java.util.List;

public class Screen_6_1 extends AppCompatActivity {

    Button save;
    RadioButton rbtn1;
    RadioButton rbtn2;
    RadioButton rbtn3;
    EditText editText;
    EditText editText1;
    SharedPreferences sharedPreferences;
    String emp_opps= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_6_1);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityScreen61Binding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_screen_6_1);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        save=  findViewById(R.id.save);
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText1);
        rbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emp_opps = rbtn1.getText().toString();
            }
        } );
        rbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emp_opps = rbtn2.getText().toString();
            }
        } );
        rbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emp_opps = rbtn3.getText().toString();
            }
        } );

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!emp_opps.equals("") && !editText.getText().toString().equals("") && !editText1.getText().toString().equals("") ) {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen_6_1.this);
                                    pollFirstDataBase.pollFirstDao().updateScreen6_1(emp_opps,editText.getText().toString(),editText1.getText().toString(),sharedPreferences.getString("LOC_ID",""));
                                    Intent intent = new Intent(Screen_6_1.this, MainDashBoard.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(Screen_6_1.this,"Successfully added",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    Screen_6_1.this.finish();

                                }
                                catch (Exception e ) {

                                }
                            }
                        });
                    }
                    catch (NullPointerException e) {

                    }
                }
                else {
                    Toast.makeText(Screen_6_1.this,"All fields are mandatory",Toast.LENGTH_SHORT).show();
                }

            }
        });

        try {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen_6_1.this);
                        List<PollFirstData> pollFirstData = pollFirstDataBase.pollFirstDao().getPollfirstData();
                        if (pollFirstData.get(0).Emp_opps.equals("High")) {
                            rbtn1.setChecked(true);
                            emp_opps = pollFirstData.get(0).Emp_opps;
                        }
                        else if (pollFirstData.get(0).Emp_opps.equals("Medium")) {
                            rbtn2.setChecked(true);
                            emp_opps = pollFirstData.get(0).Emp_opps;
                        }
                        else if (pollFirstData.get(0).Emp_opps.equals("Low")) {
                            rbtn3.setChecked(true);
                            emp_opps = pollFirstData.get(0).Emp_opps;
                        }
                        editText.setText(pollFirstData.get(0).Main_probs);
                        editText1.setText(pollFirstData.get(0).Dev_needs);

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