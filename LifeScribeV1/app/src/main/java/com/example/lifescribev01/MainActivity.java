package com.example.lifescribev01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Main menu button presses to send user to other pages
    public void familyTreePress(View view){
        startActivity(new Intent(MainActivity.this, FamilyTreeActivity.class));
    }
    public void peoplePress(View view){
        startActivity(new Intent(MainActivity.this, PeopleActivity.class));
    }
    public void storiesPress(View view){
        startActivity(new Intent(MainActivity.this, StoriesActivity.class));
    }
    public void advicePress(View view){
        startActivity(new Intent(MainActivity.this, AdviceActivity.class));
    }
}
