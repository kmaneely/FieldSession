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
}