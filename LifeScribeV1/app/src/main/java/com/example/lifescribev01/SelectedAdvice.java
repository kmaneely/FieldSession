package com.example.lifescribev01;

import android.os.Bundle;

import com.example.lifescribev01.database.AppDatabase;
import com.example.lifescribev01.database.Person;
import com.example.lifescribev01.database.Story;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

public class SelectedAdvice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_advice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");

        AppDatabase appDb = MainActivity.GetDatabase();
        Story s = appDb.storyDao().findByID(id);

        EditText titleField = findViewById(R.id.title);
        titleField.setText(s.title);
        EditText dosField = findViewById(R.id.DOS);
        dosField.setText(s.date);
        EditText storyField = findViewById(R.id.text);
        storyField.setText(s.text);
    }

}
