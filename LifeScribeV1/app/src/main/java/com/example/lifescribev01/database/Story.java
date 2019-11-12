package com.example.lifescribev01.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Story {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "story_id")
    public int storyID;

    @ColumnInfo (name = "title")
    public String title;

    @ColumnInfo (name = "date")
    public String date;

    @ColumnInfo (name = "person_id")
    public int personID;

    @ColumnInfo (name = "text")
    public String text;

    @ColumnInfo (name = "type_id")
    public int typeID;

    @ColumnInfo (name = "audio_file_path")
    public String audioPathID;
}
