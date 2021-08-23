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
import com.candlestickschart.wayanadboothpresident.databinding.ActivityBoothAgentListBinding;
import com.candlestickschart.wayanadboothpresident.databinding.ActivityMainDashBoardBinding;

import java.util.ArrayList;
import java.util.List;

public class BoothAgentList extends AppCompatActivity {

    ListView listView;
    List<String> impPersonName = new ArrayList<>();
    List<BoothAgent> politicalWorkerData = new ArrayList<>();
    SharedPreferences sharedPreferences;
    Button addNew;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booth_agent_list);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityBoothAgentListBinding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_booth_agent_list);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);
        listView = findViewById(R.id.listView);
        addNew = findViewById(R.id.add);
        save = findViewById(R.id.save);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BoothAgentList.this, BoothAgentAc.class);
                intent.putExtra("party",getIntent().getStringExtra("party"));
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
                PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(BoothAgentList.this);
                impPersonName = pollFirstDataBase.pollFirstDao().getBoothAgentName(getIntent().getStringExtra("party"));
                politicalWorkerData = pollFirstDataBase.pollFirstDao().getBoothAgentData(getIntent().getStringExtra("party"));
                if (impPersonName.size()!=0) {
                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            ArrayAdapter<String> itemsAdapter =
                                    new ArrayAdapter<String>(BoothAgentList.this, android.R.layout.simple_list_item_1, impPersonName);
                            listView.setAdapter(itemsAdapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent intent = new Intent(BoothAgentList.this, BoothAgentAc.class);
                                    intent.putExtra("name",politicalWorkerData.get(i).Name);
                                    intent.putExtra("booth",politicalWorkerData.get(i).BoothNumber);
                                    intent.putExtra("party",getIntent().getStringExtra("party"));
                                    intent.putExtra("partyname",politicalWorkerData.get(i).Social_Type);
                                    intent.putExtra("age",politicalWorkerData.get(i).Age);
                                    intent.putExtra("mobile",politicalWorkerData.get(i).Mobile);
                                    intent.putExtra("gender",politicalWorkerData.get(i).Gender);
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