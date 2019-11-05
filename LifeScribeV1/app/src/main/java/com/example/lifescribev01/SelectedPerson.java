package com.example.lifescribev01;

import android.content.Intent;
import android.os.Bundle;

import com.example.lifescribev01.database.AppDatabase;
import com.example.lifescribev01.database.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SelectedPerson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_person);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();
        final int id = b.getInt("id");

        AppDatabase appDb = MainActivity.GetDatabase();
        Person p = appDb.personDao().findByID(id);

        EditText nameField = findViewById(R.id.name);
        nameField.setText(p.name);
        EditText dobField = findViewById(R.id.DOB);
        dobField.setText(p.birthDate);
        EditText dodField = findViewById(R.id.DOD);
        dodField.setText(p.deathDate);
        EditText bioField = findViewById(R.id.bio);
        bioField.setText(p.bio);

        final Spinner relatives= findViewById(R.id.relative);
        List<Person> dbPeople = appDb.personDao().getAll();
        List<String> pList = new ArrayList<>();
        for (Person pe: dbPeople) {
            pList.add(pe.name);
        }
        ArrayAdapter<String> relativeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pList);
        relatives.setAdapter(relativeAdapter);

        List<String> rList = new ArrayList<String>(){{
            add("Parent");
            add("Child");
            add("Sibling");
        }};
        final Spinner relationships= findViewById(R.id.relationship);
        ArrayAdapter<String> relationshipAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, rList);
        relationships.setAdapter(relationshipAdapter);


        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Spinner personField = findViewById(R.id.relative);
                final Spinner relationshipField = findViewById(R.id.relationship);
                int personID = personField.getSelectedItemPosition() + 1;
                Pair peoplePair;
                String rel = relationshipField.getSelectedItem().toString();
                if(rel == "Parent"){
                    peoplePair = new Pair(id, personID);
                }

            }
        });
    }

}
