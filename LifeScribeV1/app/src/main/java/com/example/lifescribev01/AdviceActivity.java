package com.example.lifescribev01;

import android.content.Intent;
import android.os.Bundle;

import com.example.lifescribev01.database.AppDatabase;
import com.example.lifescribev01.database.Story;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class AdviceActivity extends AppCompatActivity {
    private RecyclerView adviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppDatabase appDb = MainActivity.GetDatabase();

        RecyclerView adviceView = findViewById(R.id.adviceList);
        List<Story> dbAdvice = appDb.storyDao().getAllOfType(2);
        List<String> tList = new ArrayList<>();
        for (Story s: dbAdvice) {
            tList.add(s.title);
        }
        final StoryListAdapter adapter = new StoryListAdapter(dbAdvice);
        adviceList = findViewById(R.id.adviceList);
        adviceList.setAdapter(adapter);
        adviceList.setLayoutManager(new LinearLayoutManager(this));

        SearchView searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                adapter.getFilter().filter(text);
                return true;
            }
        });

        adviceView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, adviceView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        Intent i = new Intent(AdviceActivity.this, SelectedAdvice.class);
                        List<Story> filteredList = adapter.getFilterList();
                        i.putExtra("id", filteredList.get(position).storyID);
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
                startActivity(new Intent(AdviceActivity.this, NewAdvice.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
