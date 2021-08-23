package com.candlestickschart.wayanadboothpresident;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.candlestickschart.wayanadboothpresident.databinding.ActivityMainDashBoardBinding;
import com.candlestickschart.wayanadboothpresident.databinding.ActivityScreen4Binding;

import java.util.List;

public class Screen_4 extends AppCompatActivity {
    EditText reason;
    Spinner spinner;
    EditText facts;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_4);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityScreen4Binding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_screen_4);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        reason = findViewById(R.id.reason);
        facts = findViewById(R.id.facts);
        spinner = findViewById(R.id.spinner);


    }
    public void backPressed(View view){
        finish();
    }
    public void forwardPressed(View view){
        if (reason.getText().length() != 0 && facts.getText().length() !=0 && !spinner.getSelectedItem().toString().equals("कौन सी पार्टी आगे है")) {
            try {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen_4.this);
                            pollFirstDataBase.pollFirstDao().updateScreen4(spinner.getSelectedItem().toString(),reason.getText().toString(),facts.getText().toString(),sharedPreferences.getString("LOC_ID","1"));
                            Intent intent = new Intent(Screen_4.this, Screen_4_1.class);
                            startActivity(intent);


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
            Toast.makeText(Screen_4.this,"All fields are mandatory",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(Screen_4.this);
                        List<PollFirstData> pollFirstData = pollFirstDataBase.pollFirstDao().getPollfirstData();
                        ArrayAdapter<CharSequence> partyadapter = ArrayAdapter.createFromResource(Screen_4.this, R.array.fav_party, android.R.layout.simple_spinner_item);
                        partyadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(partyadapter);
                        String compareGender = pollFirstData.get(0).Party_leading;
                        if (compareGender != null) {
                            int spinnerPosition = partyadapter.getPosition(compareGender);
                            spinner.setSelection(spinnerPosition);
                        }
                        reason.setText(pollFirstData.get(0).Reason_leading);
                        facts.setText(pollFirstData.get(0).Local_issue);

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