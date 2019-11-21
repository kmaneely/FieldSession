package com.example.lifescribev01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.example.lifescribev01.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] appPermissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO};
    private static final int PERMISSIONS_REQUEST_CODE = 1240;

    static AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "people_database").allowMainThreadQueries().addMigrations(AppDatabase.MIGRATION_1_2, AppDatabase.MIGRATION_2_3, AppDatabase.MIGRATION_3_4, AppDatabase.MIGRATION_4_5).build();

        if (checkAndRequestPermissions()) {
            new MainActivity();
        }


       //record.setEnabled(true);
        //stop.setEnabled(false);
        //play.setEnabled(true);

    }

    // Main menu button presses to send user to other pages
    public void familyTreePress(View view){
        if(db.personDao().getNumberOfPeople() > 0) {
            startActivity(new Intent(MainActivity.this, NewFamilyTree.class));
        }
    }
    public void peoplePress(View view){
            startActivity(new Intent(MainActivity.this, PeopleActivity.class));
    }
    public void storiesPress(View view){
        if(db.personDao().getNumberOfPeople() > 0) {
            startActivity(new Intent(MainActivity.this, StoriesActivity.class));
        }
    }
    public void advicePress(View view){
        if(db.personDao().getNumberOfPeople() > 0) {
            startActivity(new Intent(MainActivity.this, AdviceActivity.class));
        }
    }

    public static AppDatabase GetDatabase()
    {
        return db;
    }


    public boolean checkAndRequestPermissions(){
        List<String> listPermissionsNeeded = new ArrayList<>();
        for(String perm:appPermissions){
            if(ContextCompat.checkSelfPermission(this,perm) != PackageManager.PERMISSION_GRANTED){
                listPermissionsNeeded.add(perm);
            }
        }
        if(!listPermissionsNeeded.isEmpty()){
            ActivityCompat.requestPermissions(this,listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),PERMISSIONS_REQUEST_CODE);
            return false;
        }
        return true;
    }
}
