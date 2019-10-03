package com.example.lifescribev01.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class People {
    @PrimaryKey
    public int uid;
    @ColumnInfo (name = "first_name")
    public String firstName;
    @ColumnInfo (name = "last_name")
    public String lastName;
    @ColumnInfo (name = "birth_date")
    public Date birthDate;
    @ColumnInfo (name = "death_date")
    public Date deathDate;
    @ColumnInfo (name = "biography")
    public String bio;
}