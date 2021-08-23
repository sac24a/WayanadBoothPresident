package com.candlestickschart.wayanadboothpresident;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button english;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        english = findViewById(R.id.english);


        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LocalityOptions.class);
                startActivity(intent);
            }
        });

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(MainActivity.this);
                    Log.d("Instered Data", "run: "+pollFirstDataBase.pollFirstDao().getPollfirstData().size());
                }
                catch (Exception e ) {

                }
            }
        });

    }
}