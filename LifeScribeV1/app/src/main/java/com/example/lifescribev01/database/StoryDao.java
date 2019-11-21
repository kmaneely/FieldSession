package com.example.lifescribev01.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StoryDao {
    @Query("SELECT * FROM story")
    List<Story> getAll();

    @Query("SELECT * FROM story WHERE title IS :title")
    Story findByName(String title);

    @Query("SELECT * FROM story WHERE story_id IS :id")
    Story findByID(int id);

    @Query("SELECT * FROM story WHERE person_id IS :person_id")
    List<Story> getAllOfPerson(int person_id);

    @Insert
    void insert(Story story);

    @Delete
    void delete(Story story);

    @Query("SELECT * FROM story WHERE type_id IS :type_id")
    List<Story> getAllOfType(int type_id);

    @Query("SELECT COUNT(*) FROM story")
    int getNumberOfStories();

    @Update
    void updateStory(Story story);
}