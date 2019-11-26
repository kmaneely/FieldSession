package com.example.lifescribev01;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.lifescribev01.database.AppDatabase;
import com.example.lifescribev01.database.Person;
import com.example.lifescribev01.database.Story;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SelectedAdvice extends AppCompatActivity {

    private Button play;
    Story s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_advice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        play = findViewById(R.id.play);

        //Get the advice ID from the one selected
        Bundle b = getIntent().getExtras();
        final int id = b.getInt("id");

        //Pulls the needed advice and people from the database
        AppDatabase appDb = MainActivity.GetDatabase();
        s = appDb.storyDao().findByID(id);
        Person p = appDb.personDao().findByID(s.personID);

        //Fills out all of the fields in the selected advice
        EditText titleField = findViewById(R.id.title);
        titleField.setText(s.title);
        EditText doaField = findViewById(R.id.DOS);
        doaField.setText(s.date);
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
                Intent i = new Intent(SelectedAdvice.this, EditAdvice.class);
                i.putExtra("id", id);
                startActivity(i);
                finish();
            }
        });
    }

}
