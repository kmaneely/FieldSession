package com.example.lifescribev01;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.example.lifescribev01.database.AppDatabase;
import com.example.lifescribev01.database.Person;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;

public class EditPerson extends AppCompatActivity {
    String name, DOB, DOD, bio;
    TextView textTargetUri; //Gallery access
    ImageView targetImage; //Gallery access
    Person selectedPerson = new Person();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        final int id = b.getInt("id");


        final AppDatabase appDb = MainActivity.GetDatabase();
        selectedPerson = appDb.personDao().findByID(id);
        ImageView image = findViewById(R.id.targetimage);

        final EditText nameField = findViewById(R.id.name);
        nameField.setText(selectedPerson.name);
        final EditText dobField = findViewById(R.id.DOB);
        dobField.setText(selectedPerson.birthDate);
        final EditText dodField = findViewById(R.id.DOD);
        dodField.setText(selectedPerson.deathDate);
        final EditText bioField = findViewById(R.id.bio);
        bioField.setText(selectedPerson.bio);

        if(appDb.personDao().findByID(id).imageURI != null){
            Uri targetUri = Uri.parse(appDb.personDao().findByID(id).imageURI);
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                image.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            //default image
        }

        //Gallery access code
        Button buttonLoadImage = findViewById(R.id.loadimage);
        textTargetUri = findViewById(R.id.targeturi);
        targetImage = findViewById(R.id.targetimage);

        buttonLoadImage.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String[] types = {"image/jpeg", "image/png"};
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        .setType("image/*")
                        .putExtra(Intent.EXTRA_MIME_TYPES, types);
                startActivityForResult(intent, 0);
            }});
        //Gallery access code

        Button fab = findViewById(R.id.submit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText nameField = findViewById(R.id.name);
                name = nameField.getText().toString();
                final EditText dobField = findViewById(R.id.DOB);
                DOB = dobField.getText().toString();
                final EditText dodField = findViewById(R.id.DOD);
                DOD = dodField.getText().toString();
                final EditText bioField = findViewById(R.id.bio);
                bio = bioField.getText().toString();
                selectedPerson.name = name;
                selectedPerson.birthDate = DOB;
                selectedPerson.deathDate = DOD;
                selectedPerson.bio = bio;
                appDb.personDao().updatePerson(selectedPerson);


                startActivity(new Intent(EditPerson.this, PeopleActivity.class));
                finish();

            }
        });
    }
    //Gallery access code
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Uri targetUri = data.getData();
            textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                targetImage.setImageBitmap(bitmap);
                selectedPerson.imageURI = targetUri.toString();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    //Gallery access code
}
