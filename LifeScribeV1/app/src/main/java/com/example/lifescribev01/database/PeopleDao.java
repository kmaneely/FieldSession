package com.example.lifescribev01.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PeopleDao {
    @Query("SELECT * FROM people")
    List<People> getAll();

    @Query("SELECT * FROM people WHERE uid IN (:userIds)")
    List<People> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM people WHERE name LIKE :name LIMIT 1")
    People findByName(String name);

    @Insert
    void insertAll(People people);

    @Delete
    void delete(People people);
}