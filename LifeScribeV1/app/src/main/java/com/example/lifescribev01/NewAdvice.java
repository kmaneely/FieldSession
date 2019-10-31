package com.example.lifescribev01;

import android.content.Intent;
import android.os.Bundle;

import com.example.lifescribev01.database.AppDatabase;
import com.example.lifescribev01.database.Person;
import com.example.lifescribev01.database.Story;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewAdvice extends AppCompatActivity {
    String name, DOA, person, advice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_advice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Story storyDB = new Story();
        final AppDatabase appDb = MainActivity.GetDatabase();


        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText nameField = findViewById(R.id.name);
                name = nameField.getText().toString();
                final EditText dobField = findViewById(R.id.date);
                DOA = dobField.getText().toString();
                final EditText dodField = findViewById(R.id.person);
                person = dodField.getText().toString();
                final EditText bioField = findViewById(R.id.advice);
                advice = bioField.getText().toString();
                storyDB.title = name;
                storyDB.date = DOA;
                storyDB.text = advice;
                appDb.storyDao().insert(storyDB);

                startActivity(new Intent(NewAdvice.this, AdviceActivity.class));
                finish();
            }
        });
    }

}
