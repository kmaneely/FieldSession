package com.example.lifescribev01;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

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

        RecyclerView tesList = (RecyclerView) findViewById(R.id.peopleList);
        List<String> tList = new ArrayList<String>();
        tList.add("this");
        tList.add("some");
        tList.add("bs");
        ListAdapter adapter = new ListAdapter(tList);
        peopleList = (RecyclerView) findViewById(R.id.peopleList);
        peopleList.setAdapter(adapter);
        peopleList.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PeopleActivity.this, NewPeople.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
