package com.example.lifescribev01;

import android.os.Bundle;

import com.example.lifescribev01.database.AppDatabase;
import com.example.lifescribev01.database.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SelectedPerson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_person);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");

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
    }

}
