package com.example.lifescribev01;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

        Button fab = findViewById(R.id.submit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText nameField = (EditText) findViewById(R.id.name);
                name = nameField.getText().toString();
                final EditText dobField = (EditText) findViewById(R.id.DOB);
                DOB = dobField.getText().toString();
                final EditText dodField = (EditText) findViewById(R.id.DOD);
                DOD = dobField.getText().toString();
                final EditText bioField = (EditText) findViewById(R.id.bio);
                bio = dobField.getText().toString();
                //startActivity(new Intent(NewPeople.this, PeopleActivity.class));
                finish();
            }
        });
    }
}
