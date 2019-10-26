package com.example.lifescribev01.database;

import androidx.annotation.NonNull;
import androidx.room.*;

@Entity
public class Person {
    @NonNull
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "person_id")
    public int personID;
    @ColumnInfo (name = "name")
    public String name;
    @ColumnInfo (name = "birth_date")
    public String birthDate;
    @ColumnInfo (name = "death_date")
    public String deathDate;
    @ColumnInfo (name = "biography")
    public String bio;
    @ColumnInfo (name = "spouse_id")
    public int spouseID;
}