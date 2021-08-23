package com.candlestickschart.wayanadboothpresident;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.candlestickschart.wayanadboothpresident.databinding.ActivityBoothAgentBinding;
import com.candlestickschart.wayanadboothpresident.databinding.ActivityScreen41Binding;
import com.candlestickschart.wayanadboothpresident.databinding.ActivityScreen4Binding;

import java.util.List;

public class Screen_4_1 extends AppCompatActivity {

    private Button forward;

    RadioButton rbtn1;
    RadioButton rbtn2;
    RadioButton rbtn3;
    RadioButton rbtn4;
    RadioButton rbtn5;
    RadioButton rbtn6;
    RadioButton rbtn7;
    RadioButton rbtn8;
    RadioButton rbtn9;
    RadioButton rbtn10;
    RadioButton rbtn11;
    RadioButton rbtn12;

    SharedPreferences sharedPreferences;

    String udf = "";
    String ldf = "";
    String bjp = "";

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_4_1);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityScreen41Binding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_screen_4_1);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        forward=findViewById(R.id.btn);
        editText = findViewById(R.id.editText);
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        rbtn4 = findViewById(R.id.rbtn4);
        rbtn5 = findViewById(R.id.rbtn5);
        rbtn6 = findViewById(R.id.rbtn6);
        rbtn7 = findViewById(R.id.rbtn7);
        rbtn8 = findViewById(R.id.rbtn8);
        rbtn9 = findViewById(R.id.rbtn9);
        rbtn10 = findViewById(R.id.rbtn10);
        rbtn11 = findViewById(R.id.rbtn11);
        rbtn12 = findViewById(R.id.rbtn12);

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!udf.equals("") && !bjp.equals("") && !ldf.equals("") && !editText.getText().toString().equals("")) {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen_4_1.this);
                                    pollFirstDataBase.pollFirstDao().updateScreen4_1(udf,ldf,bjp,editText.getText().toString(),sharedPreferences.getString("LOC_ID","1"));
                                    Intent intent = new Intent(Screen_4_1.this, MainDashBoard.class);
                                    startActivity(intent);
                                    finish();

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
                    Toast.makeText(Screen_4_1.this,"All fields are mandatory",Toast.LENGTH_SHORT).show();
                }

            }
        });

        rbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                udf = rbtn1.getText().toString();
            }
        } );
        rbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                udf = rbtn2.getText().toString();
            }
        } );
        rbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                udf = rbtn3.getText().toString();
            }
        } );
        rbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ldf = rbtn4.getText().toString();
            }
        } );
        rbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ldf = rbtn5.getText().toString();

            }
        } );
        rbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ldf = rbtn6.getText().toString();
            }
        } );

        rbtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bjp = rbtn7.getText().toString();
            }
        } );
        rbtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bjp = rbtn8.getText().toString();
            }
        } );
        rbtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bjp = rbtn9.getText().toString();
            }
        } );

        try {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen_4_1.this);
                        List<PollFirstData> pollFirstData = pollFirstDataBase.pollFirstDao().getPollfirstData();
                        if (pollFirstData.get(0).Cond_UDF.equals("Strong")) {
                            rbtn1.setChecked(true);
                            udf = pollFirstData.get(0).Cond_UDF;
                        }
                        else if (pollFirstData.get(0).Cond_UDF.equals("Average")) {
                            rbtn2.setChecked(true);
                            udf = pollFirstData.get(0).Cond_UDF;
                        }
                        else if (pollFirstData.get(0).Cond_UDF.equals("Weak")) {
                            rbtn3.setChecked(true);
                            udf = pollFirstData.get(0).Cond_UDF;
                        }
                        if (pollFirstData.get(0).Cond_LDF.equals("Strong")) {
                            rbtn4.setChecked(true);
                            ldf = pollFirstData.get(0).Cond_LDF;
                        }
                        else if (pollFirstData.get(0).Cond_LDF.equals("Average")) {
                            rbtn5.setChecked(true);
                            ldf = pollFirstData.get(0).Cond_LDF;
                        }
                        else if (pollFirstData.get(0).Cond_LDF.equals("Weak")) {
                            rbtn6.setChecked(true);
                            ldf = pollFirstData.get(0).Cond_LDF;
                        }
                        if (pollFirstData.get(0).Cond_BJP.equals("Strong")) {
                            rbtn7.setChecked(true);
                            bjp = pollFirstData.get(0).Cond_BJP;
                        }
                        else if (pollFirstData.get(0).Cond_BJP.equals("Average")) {
                            rbtn8.setChecked(true);
                            bjp = pollFirstData.get(0).Cond_BJP;
                        }
                        else if (pollFirstData.get(0).Cond_BJP.equals("Weak")) {
                            rbtn9.setChecked(true);
                            bjp = pollFirstData.get(0).Cond_BJP;
                        }
                        editText.setText(pollFirstData.get(0).Cond_OTH);

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