package com.candlestickschart.wayanadboothpresident;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.candlestickschart.wayanadboothpresident.databinding.ActivityBoothAgentBinding;
import com.candlestickschart.wayanadboothpresident.databinding.ActivityBoothAgentScreenBinding;

import java.util.ArrayList;
import java.util.List;

public class BoothAgentScreen extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button save;

    SharedPreferences sharedPreferences;
    List<String> socialData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booth_agent_screen);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityBoothAgentScreenBinding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_booth_agent_screen);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValue("UDF","UDF");

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValue("LDF","LDF");

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValue("BJP","BJP");

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValue("OTHERS","OTHERS");

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValue("अन्य दल","अन्य दल");

            }
        });

    }
    private void checkValue(String party,String searchType){
        Intent intent = new Intent(BoothAgentScreen.this, BoothAgentList.class);
        intent.putExtra("party",party);
        startActivity(intent);
//        AppExecutors.getInstance().diskIO().execute(new Runnable() {
//            @Override
//            public void run() {
//                PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(BoothAgentScreen.this);
//                socialData = pollFirstDataBase.pollFirstDao().checkBoothAgent(searchType);
//                if (socialData.size() == 0) {
//                    Intent intent = new Intent(BoothAgentScreen.this, BoothAgentAc.class);
//                    intent.putExtra("party",party);
//                    startActivity(intent);
//                }
//                else {
//                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(BoothAgentScreen.this,"You have saved this data",Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(BoothAgentScreen.this);
                List<String>spData = pollFirstDataBase.pollFirstDao().checkBoothAgent("UDF");
                List<String>bspData = pollFirstDataBase.pollFirstDao().checkBoothAgent("LDF");
                List<String>bjpData = pollFirstDataBase.pollFirstDao().checkBoothAgent("BJP");
                List<String>incData = pollFirstDataBase.pollFirstDao().checkBoothAgent("OTHERS");
//                List<String>anyData = pollFirstDataBase.pollFirstDao().checkBoothAgent("अन्य दल");
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (spData.size()!=0) {
                            button1.setBackgroundResource(R.drawable.green);
                        }
                        else {
                            button1.setBackgroundResource(R.drawable.newsample);
                        }
                        if (bspData.size()!=0) {
                            button2.setBackgroundResource(R.drawable.green);
                        }else {
                            button2.setBackgroundResource(R.drawable.newsample);
                        }
                        if (bjpData.size()!=0) {
                            button3.setBackgroundResource(R.drawable.green);
                        }
                        else {
                            button3.setBackgroundResource(R.drawable.newsample);
                        }
//                        if (anyData.size()!=0) {
//                            button5.setBackgroundResource(R.drawable.green);
//                        }
//                        else {
//                            button5.setBackgroundResource(R.drawable.newsample);
//                        }
                        if (incData.size()!=0) {
                            button4.setBackgroundResource(R.drawable.green);
                        }
                        else {
                            button4.setBackgroundResource(R.drawable.newsample);
                        }
                    }
                });

            }
        });
    }
}