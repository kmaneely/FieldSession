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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

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

        final Spinner personDrop= findViewById(R.id.person);
        List<Person> dbPeople = appDb.personDao().getAll();
        List<String> pList = new ArrayList<>();
        for (Person p: dbPeople) {
            pList.add(p.name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pList);
        personDrop.setAdapter(adapter);

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            final EditText nameField = findViewById(R.id.name);
            name = nameField.getText().toString();
            final EditText doaField = findViewById(R.id.date);
            DOA = doaField.getText().toString();
            final Spinner personField = findViewById(R.id.person);
            int personID = personField.getSelectedItemPosition() + 1;
            final EditText bioField = findViewById(R.id.advice);
            advice = bioField.getText().toString();
            storyDB.title = name;
            storyDB.date = DOA;
            storyDB.personID = personID;
            storyDB.text = advice;
            storyDB.typeID = 2;
            appDb.storyDao().insert(storyDB);

            startActivity(new Intent(NewAdvice.this, AdviceActivity.class));
            finish();
            }
        });
    }

}
