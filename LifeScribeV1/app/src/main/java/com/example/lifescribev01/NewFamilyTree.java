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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class NewFamilyTree extends AppCompatActivity {

    private RecyclerView peopleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_family_tree);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AppDatabase appDb = MainActivity.GetDatabase();

        RecyclerView peopleView = findViewById(R.id.peopleList);
        List<Person> dbPeople = appDb.personDao().getAll();
        List<String> tList = new ArrayList<>();
        for (Person p: dbPeople) {
            tList.add(p.name);
        }
        final PersonListAdapter adapter = new PersonListAdapter(dbPeople);
        peopleList = findViewById(R.id.peopleList);
        peopleList.setAdapter(adapter);
        peopleList.setLayoutManager(new LinearLayoutManager(this));

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

        peopleView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, peopleView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        Intent i = new Intent(NewFamilyTree.this, FamilyTreeActivity.class);
                        List<Person> filteredList = adapter.getFilterList();
                        i.putExtra("id", filteredList.get(position).personID);
                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }

}
