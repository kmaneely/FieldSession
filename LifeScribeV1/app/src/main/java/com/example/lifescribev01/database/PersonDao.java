package com.example.lifescribev01.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM person")
    List<Person> getAll();

    @Query("SELECT * FROM person WHERE personID IN (:userIds)")
    List<Person> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM person WHERE :name")
    Person findByName(String name);

    @Query("SELECT * FROM person WHERE :id")
    Person findByID(int id);

    @Insert
    void insert(Person people);

    @Delete
    void delete(Person people);
}