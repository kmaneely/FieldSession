package com.example.lifescribev01;

import android.content.Intent;
import android.os.Bundle;

import com.example.lifescribev01.database.AppDatabase;
import com.example.lifescribev01.database.Person;
import com.example.lifescribev01.database.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.*;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewPeople extends AppCompatActivity {
    String name, DOB, DOD, bio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_people);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Person testPerson = new Person();
        final AppDatabase appDb = MainActivity.GetDatabase();

        Button fab = findViewById(R.id.submit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText nameField = (EditText) findViewById(R.id.name);
                name = nameField.getText().toString();
                final EditText dobField = (EditText) findViewById(R.id.DOB);
                DOB = dobField.getText().toString();
                final EditText dodField = (EditText) findViewById(R.id.DOD);
                DOD = dodField.getText().toString();
                final EditText bioField = (EditText) findViewById(R.id.bio);
                bio = bioField.getText().toString();
                testPerson.name = name;
                testPerson.birthDate = DOB;
                testPerson.deathDate = DOD;
                testPerson.bio = bio;
                appDb.personDao().insert(testPerson);




                startActivity(new Intent(NewPeople.this, PeopleActivity.class));
                finish();
            }
        });
    }
}
