package com.example.lifescribev01.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StoryRelevanceXRefDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(StoryRelevanceXRef pair);

    @Query("SELECT * FROM story INNER JOIN related_people_xref ON story.story_id=related_people_xref.story_id WHERE related_people_xref.person_id=:personID")
    List<Story> getStoriesOfPerson(int personID);

    @Query("SELECT * FROM person INNER JOIN related_people_xref ON person.person_id=related_people_xref.person_id WHERE related_people_xref.story_id=:storyID")
    List<Person> getPeopleOfStory(int storyID);
}
