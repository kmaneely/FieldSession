package com.example.lifescribev01;

import android.content.Intent;
import android.os.Bundle;

import com.example.lifescribev01.database.AppDatabase;
import com.example.lifescribev01.database.ParentXRef;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AppDatabase appDb = MainActivity.GetDatabase();

        Person person = new Person();
        person.name = "bob";
        appDb.personDao().insert(person);
        System.out.println(appDb.personDao().findByID(1).name);
        System.out.println(appDb.personDao().findByID(1).personID);
        appDb.personDao().findByID(1).name = "Jim";
        System.out.println(appDb.personDao().findByID(1).name);
        System.out.println(appDb.personDao().findByID(1).personID);



        RecyclerView peopleView = findViewById(R.id.peopleList);
        List<Person> dbPeople = appDb.personDao().getAll();
        List<String> tList = new ArrayList<>();
        for (Person p: dbPeople) {
            tList.add(p.name);
        }
        final ListAdapter adapter = new ListAdapter(tList);
        peopleList = findViewById(R.id.peopleList);
        peopleList.setAdapter(adapter);
        peopleList.setLayoutManager(new LinearLayoutManager(this));

        peopleView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, peopleView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        Intent i = new Intent(PeopleActivity.this, SelectedPerson.class);
                        i.putExtra("id", position + 1);
                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

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
