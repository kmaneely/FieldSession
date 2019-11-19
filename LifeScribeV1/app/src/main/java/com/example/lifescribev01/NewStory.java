package com.example.lifescribev01;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;

import com.example.lifescribev01.database.AppDatabase;
import com.example.lifescribev01.database.Person;
import com.example.lifescribev01.database.Story;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewStory extends AppCompatActivity {
    String name, DOS, person, story;
    Button play, record, stop;
    private MediaRecorder myAudioRecorder;
    String outputFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_story);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Story storyDB = new Story();
        final Person personDB = new Person();
        final AppDatabase appDb = MainActivity.GetDatabase();

        final Spinner personDrop= findViewById(R.id.person);
        List<Person> dbPeople = appDb.personDao().getAll();
        List<String> pList = new ArrayList<>();
        for (Person p: dbPeople) {
            pList.add(p.name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pList);
        personDrop.setAdapter(adapter);

        //recorder

        record = findViewById(R.id.record);
        stop = findViewById(R.id.stop);
        play = findViewById(R.id.play);
        stop.setEnabled(false);
        play.setEnabled(false);

        myAudioRecorder = new MediaRecorder();

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";

        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);


        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                } catch (IllegalStateException ise) {
                    System.out.println("illegal state");
                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
                record.setEnabled(false);
                stop.setEnabled(true);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;
                record.setEnabled(true);
                stop.setEnabled(false);
                play.setEnabled(true);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(outputFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (Exception e) {
                    // make something
                }
            }
        });

        //end recorder

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText nameField = findViewById(R.id.name);
                name = nameField.getText().toString();
                final EditText dosField = findViewById(R.id.date);
                DOS = dosField.getText().toString();
                final Spinner personField = findViewById(R.id.person);
                int personID = personField.getSelectedItemPosition() + 1;
                final EditText bioField = findViewById(R.id.story);
                story = bioField.getText().toString();
                storyDB.title = name;
                storyDB.date = DOS;
                storyDB.personID = personID;
                storyDB.text = story;
                storyDB.typeID = 1;
                storyDB.audioPathID = outputFile;
                appDb.storyDao().insert(storyDB);

                startActivity(new Intent(NewStory.this, StoriesActivity.class));
                finish();
            }
        });
    }

}
