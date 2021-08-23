package com.candlestickschart.wayanadboothpresident;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.candlestickschart.wayanadboothpresident.databinding.ActivityBoothAgentBinding;
import com.candlestickschart.wayanadboothpresident.databinding.ActivityReligionScreenBinding;

import java.util.ArrayList;
import java.util.List;

public class ReligionScreen extends AppCompatActivity {

    ListView listView;
    List<String> religionName = new ArrayList<>();
    List<ReligionData> politicalWorkerData = new ArrayList<>();

    SharedPreferences sharedPreferences;
    Button addNew;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_religion_screen);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityReligionScreenBinding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_religion_screen);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        listView = findViewById(R.id.listView);
        addNew = findViewById(R.id.add);
        save = findViewById(R.id.save);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReligionScreen.this, ReligionAc.class);
                startActivity(intent);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(ReligionScreen.this);
                religionName = pollFirstDataBase.pollFirstDao().getReligionName();
                politicalWorkerData = pollFirstDataBase.pollFirstDao().getReligion();
                if (religionName.size()!=0) {
                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            ArrayAdapter<String> itemsAdapter =
                                    new ArrayAdapter<String>(ReligionScreen.this, android.R.layout.simple_list_item_1, religionName);
                            listView.setAdapter(itemsAdapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent intent = new Intent(ReligionScreen.this, ReligionAc.class);
                                    intent.putExtra("name",politicalWorkerData.get(i).Name);
                                    intent.putExtra("mobile",politicalWorkerData.get(i).Mobile);
                                    intent.putExtra("impression",politicalWorkerData.get(i).Impression);
                                    intent.putExtra("connect",politicalWorkerData.get(i).Connect);
                                    intent.putExtra("person",politicalWorkerData.get(i).Person);
                                    startActivity(intent);
                                }
                            });
                        }
                    });

                }
            }
        });
    }
}