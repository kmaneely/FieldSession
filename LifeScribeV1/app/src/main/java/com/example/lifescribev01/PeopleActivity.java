package com.example.lifescribev01;

import android.content.Intent;
import android.os.Bundle;

import com.example.lifescribev01.database.AppDatabase;
import com.example.lifescribev01.database.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class PeopleActivity extends AppCompatActivity {
    private RecyclerView peopleList;
    private RecyclerView.Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppDatabase appDb = MainActivity.GetDatabase();

        RecyclerView tesList = (RecyclerView) findViewById(R.id.peopleList);
        List<Person> dbPeople = appDb.personDao().getAll();
        List<Integer> tList = new ArrayList<Integer>();
        for (Person p: dbPeople) {
            tList.add(p.personID);
        }
        ListAdapter adapter = new ListAdapter(tList);
        peopleList = (RecyclerView) findViewById(R.id.peopleList);
        peopleList.setAdapter(adapter);
        peopleList.setLayoutManager(new LinearLayoutManager(this));

        /*EditText selectedPerson = (EditText) findViewById(R.id.);
        selectedPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hi");
            }

        }); */

        tesList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, tesList, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        startActivity(new Intent(PeopleActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        startActivity(new Intent(PeopleActivity.this, MainActivity.class));
                    }
                })
        );

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PeopleActivity.this, NewPeople.class));
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
