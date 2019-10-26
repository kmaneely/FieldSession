package com.example.lifescribev01;

import android.content.Intent;
import android.os.Bundle;

import com.example.lifescribev01.database.AppDatabase;
import com.example.lifescribev01.database.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewStory extends AppCompatActivity {
    String name, DOS, person, story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_story);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Person storyDB = new Person();
        final AppDatabase appDb = MainActivity.GetDatabase();


        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText nameField = (EditText) findViewById(R.id.name);
                name = nameField.getText().toString();
                final EditText dobField = (EditText) findViewById(R.id.date);
                DOS = dobField.getText().toString();
                final EditText dodField = (EditText) findViewById(R.id.person);
                person = dodField.getText().toString();
                final EditText bioField = (EditText) findViewById(R.id.story);
                story = bioField.getText().toString();
                storyDB.name = name;
                storyDB.birthDate = DOS;
                storyDB.deathDate = person;
                storyDB.bio = story;
                appDb.personDao().insert(storyDB);

                startActivity(new Intent(NewStory.this, StoriesActivity.class));
                finish();
            }
        });
    }

}
