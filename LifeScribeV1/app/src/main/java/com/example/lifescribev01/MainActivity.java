package com.example.lifescribev01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.example.lifescribev01.database.AppDatabase;

public class MainActivity extends AppCompatActivity {

    static AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "people_database").allowMainThreadQueries().addMigrations(AppDatabase.MIGRATION_1_2, AppDatabase.MIGRATION_2_3, AppDatabase.MIGRATION_3_4, AppDatabase.MIGRATION_4_5).build();

        int checkOne = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO);
        int checkTwo = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int checkThree = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (checkOne == PackageManager.PERMISSION_GRANTED) {
            //Do something
        } else {
            requestPermissions(new String[]{android.Manifest.permission.RECORD_AUDIO},1024);
        }
        if (checkTwo == PackageManager.PERMISSION_GRANTED) {
            //Do something
        } else {
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},1024);
        }
        if (checkThree == PackageManager.PERMISSION_GRANTED) {
            //Do something
        } else {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1024);
        }

    }

    // Main menu button presses to send user to other pages
    public void familyTreePress(View view){
        startActivity(new Intent(MainActivity.this, NewFamilyTree.class));
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

    public static AppDatabase GetDatabase()
    {
        return db;
    }
}
