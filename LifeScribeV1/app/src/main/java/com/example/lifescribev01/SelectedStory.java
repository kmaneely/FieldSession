package com.example.lifescribev01;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.lifescribev01.database.AppDatabase;
import com.example.lifescribev01.database.Person;
import com.example.lifescribev01.database.Story;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SelectedStory extends AppCompatActivity {

    private Button play;
    Story s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_story);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        play = findViewById(R.id.play);


        Bundle b = getIntent().getExtras();
        final int id = b.getInt("id");

        AppDatabase appDb = MainActivity.GetDatabase();
        s = appDb.storyDao().findByID(id);
        Person p = appDb.personDao().findByID(s.personID);

        EditText titleField = findViewById(R.id.title);
        titleField.setText(s.title);
        EditText dosField = findViewById(R.id.DOS);
        dosField.setText(s.date);
        EditText personField = findViewById(R.id.person);
        personField.setText(p.name);
        EditText storyField = findViewById(R.id.text);
        storyField.setText(s.text);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(s.audioPathID);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (Exception e) {
                    System.out.println("illegal state");
                }
            }
        });


        Button edit = findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectedStory.this, EditStory.class);
                i.putExtra("id", id);
                startActivity(i);
                finish();
            }
        });
    }

}
