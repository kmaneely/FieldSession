package com.example.lifescribev01.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {People.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PeopleDao peopleDao();
}