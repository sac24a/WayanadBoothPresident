package com.candlestickschart.wayanadboothpresident;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.candlestickschart.wayanadboothpresident.databinding.ActivityBoothAgentBinding;
import com.candlestickschart.wayanadboothpresident.databinding.ActivityScreen5Binding;

import java.util.List;

public class Screen_5 extends AppCompatActivity {

    private Button forward;
    private  ImageButton backward;
    EditText reason;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_5);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityScreen5Binding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_screen_5);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);


        backward = (ImageButton) findViewById(R.id.backward);
        reason = findViewById(R.id.editText);
        forward= findViewById(R.id.forward);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!reason.getText().toString().equals("")) {
                    try {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen_5.this);
                                    pollFirstDataBase.pollFirstDao().updateScreen5(reason.getText().toString(),sharedPreferences.getString("LOC_ID",""));
                                    Intent intent = new Intent(Screen_5.this, MainDashBoard.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(Screen_5.this,"Successfully added",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    Screen_5.this.finish();
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
                    Toast.makeText(Screen_5.this,"All fields are mandatory",Toast.LENGTH_SHORT).show();
                }

            }
        });
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        try {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen_5.this);
                        List<PollFirstData> pollFirstData = pollFirstDataBase.pollFirstDao().getPollfirstData();
                        reason.setText(pollFirstData.get(0).Panchayat_analysis);

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