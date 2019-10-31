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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class StoriesActivity extends AppCompatActivity {
    private RecyclerView storyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppDatabase appDb = MainActivity.GetDatabase();

        RecyclerView storyView = findViewById(R.id.storyList);
        List<Story> dbStory = appDb.storyDao().getAllOfType(1);
        List<String> tList = new ArrayList<>();
        for (Story s: dbStory) {
            tList.add(s.title);
        }
        final ListAdapter adapter = new ListAdapter(tList);
        storyList = findViewById(R.id.storyList);
        storyList.setAdapter(adapter);
        storyList.setLayoutManager(new LinearLayoutManager(this));

        storyView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, storyView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        Intent i = new Intent(StoriesActivity.this, SelectedStory.class);
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
                startActivity(new Intent(StoriesActivity.this, NewStory.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
