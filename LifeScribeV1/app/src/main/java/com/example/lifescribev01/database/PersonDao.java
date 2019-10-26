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

<<<<<<< HEAD
    @Query("SELECT * FROM person WHERE personID IS :id")
=======
    @Query("SELECT * FROM person WHERE person_id IS :id")

>>>>>>> 5a885c90c84896285e04c1bc32054b891ddb92d2
    Person findByID(int id);

    @Insert
    void insert(Person people);

    @Delete
    void delete(Person people);
}