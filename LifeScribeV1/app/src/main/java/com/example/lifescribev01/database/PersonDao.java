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

    @Query("SELECT * FROM person WHERE person_id IN (:userIds)")
    List<Person> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM person WHERE name IS :name")
    Person findByName(String name);

    @Query("SELECT * FROM person WHERE person_id IS :id")
    Person findByID(int id);

    @Insert
    void insert(Person people);

    @Delete
    void delete(Person people);
}