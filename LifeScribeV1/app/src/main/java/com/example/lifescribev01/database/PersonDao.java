package com.example.lifescribev01.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("UPDATE person SET spouse_id=:new_spouse_id WHERE person_id = :id")
    void updateSpouseID(int id, int new_spouse_id);

    @Insert
    void insert(Person people);

    @Delete
    void delete(Person people);

    @Update
    void updatePerson(Person people);
}
