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
import com.candlestickschart.wayanadboothpresident.databinding.ActivityEmployeScreenBinding;

import java.util.ArrayList;
import java.util.List;

public class EmployeScreen extends AppCompatActivity {

    ListView listView;
    List<String> socialMediaName = new ArrayList<>();
    List<EmployeeData> politicalWorkerData = new ArrayList<>();

    SharedPreferences sharedPreferences;
    Button addNew;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe_screen);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityEmployeScreenBinding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_employe_screen);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        listView = findViewById(R.id.listView);

        addNew = findViewById(R.id.add);
        save = findViewById(R.id.save);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeScreen.this, Employee.class);
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
                PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(EmployeScreen.this);
                socialMediaName = pollFirstDataBase.pollFirstDao().getEmployeeName();
                politicalWorkerData = pollFirstDataBase.pollFirstDao().getEmployee();
                if (socialMediaName.size()!=0) {
                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            ArrayAdapter<String> itemsAdapter =
                                    new ArrayAdapter<String>(EmployeScreen.this, android.R.layout.simple_list_item_1, socialMediaName);
                            listView.setAdapter(itemsAdapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent intent = new Intent(EmployeScreen.this, Employee.class);
                                    intent.putExtra("post",politicalWorkerData.get(i).Position);
                                    intent.putExtra("name",politicalWorkerData.get(i).Name);
                                    intent.putExtra("gender",politicalWorkerData.get(i).Gender);
                                    intent.putExtra("age",politicalWorkerData.get(i).Age);
                                    intent.putExtra("social",politicalWorkerData.get(i).Social_Type);
                                    intent.putExtra("mobile",politicalWorkerData.get(i).Mobile);
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