package com.candlestickschart.wayanadboothpresident;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InfoUpdate extends AppCompatActivity {

    Button festival;
    Button wedding;
    Button birth;
    Button death;
    Button achievement;
    Button letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_update);
        festival = findViewById(R.id.festival);
        wedding = findViewById(R.id.wedding);
        birth = findViewById(R.id.birth);
        death = findViewById(R.id.death);
        achievement = findViewById(R.id.achievement);
        letter = findViewById(R.id.letter);

        festival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoUpdate.this,FestivalList.class);
                startActivity(intent);
            }
        });
        wedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoUpdate.this,WeddingList.class);
                startActivity(intent);
            }
        });
        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoUpdate.this,ChildBirthList.class);
                startActivity(intent);
            }
        });
        death.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoUpdate.this,DeathList.class);
                startActivity(intent);
            }
        });
        achievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoUpdate.this,AchievementList.class);
                startActivity(intent);
            }
        });
        letter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoUpdate.this,SpecialLetterList.class);
                startActivity(intent);
            }
        });
    }
}