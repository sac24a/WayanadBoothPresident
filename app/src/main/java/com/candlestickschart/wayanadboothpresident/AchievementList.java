package com.candlestickschart.wayanadboothpresident;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.candlestickschart.wayanadboothpresident.databinding.ActivityAchievementListBinding;

import java.util.ArrayList;
import java.util.List;

public class AchievementList extends AppCompatActivity {

    ListView listView;
    List<String> impPersonName = new ArrayList<>();
    List<BoothAgent> politicalWorkerData = new ArrayList<>();
    SharedPreferences sharedPreferences;
    Button addNew;
    Button save;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_list);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityAchievementListBinding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_achievement_list);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);
        listView = findViewById(R.id.listView);
        addNew = findViewById(R.id.add);
        save = findViewById(R.id.save);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AchievementList.this, AddAchievement.class);
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
                PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(AchievementList.this);
                impPersonName = pollFirstDataBase.pollFirstDao().getAchievementName();
//                politicalWorkerData = pollFirstDataBase.pollFirstDao().getBoothAgentData(getIntent().getStringExtra("party"));
                if (impPersonName.size()!=0) {
                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            ArrayAdapter<String> itemsAdapter =
                                    new ArrayAdapter<String>(AchievementList.this, android.R.layout.simple_list_item_1, impPersonName);
                            listView.setAdapter(itemsAdapter);
//                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                    Intent intent = new Intent(AchievementList.this, BoothAgentAc.class);
//                                    intent.putExtra("addressto",politicalWorkerData.get(i).Name);
//                                    intent.putExtra("name",politicalWorkerData.get(i).BoothNumber);
//                                    intent.putExtra("relation",getIntent().getStringExtra("party"));
//                                    intent.putExtra("detail",politicalWorkerData.get(i).Social_Type);
//                                    intent.putExtra("assistance",politicalWorkerData.get(i).Age);
//                                    intent.putExtra("address",politicalWorkerData.get(i).Mobile);
//                                    startActivity(intent);
//                                }
//                            });
                        }
                    });

                }
            }
        });
    }
}