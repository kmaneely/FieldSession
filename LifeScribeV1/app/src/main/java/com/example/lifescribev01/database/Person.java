package com.example.lifescribev01.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class People {
    @PrimaryKey (autoGenerate = true)
    public int uid;
    @ColumnInfo (name = "name")
    public String name;
    @ColumnInfo (name = "birth_date")
    public String birthDate;
    @ColumnInfo (name = "death_date")
    public String deathDate;
    @ColumnInfo (name = "biography")
    public String bio;
}