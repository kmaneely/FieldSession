package com.example.lifescribev01;

import android.content.Intent;
import android.os.Bundle;

import com.example.lifescribev01.database.AppDatabase;
import com.example.lifescribev01.database.ParentXRef;
import com.example.lifescribev01.database.Person;
import com.example.lifescribev01.database.SiblingXRef;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class NewFamilyTree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_family_tree);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        final AppDatabase appDb = MainActivity.GetDatabase();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Spinner relatives= findViewById(R.id.relative);
        List<Person> dbPeople = appDb.personDao().getAll();
        List<String> pList = new ArrayList<>();
        for (Person pe: dbPeople) {
            pList.add(pe.name);
        }

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Spinner personField = findViewById(R.id.relative);
                int personID = personField.getSelectedItemPosition() + 1;

                Intent i = new Intent(NewFamilyTree.this, SelectedPerson.class);
                i.putExtra("id", personID);
                startActivity(i);
            }
        });
    }

}
