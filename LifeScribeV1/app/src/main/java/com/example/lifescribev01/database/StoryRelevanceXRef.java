package com.example.lifescribev01.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "related_people_xref",
        primaryKeys = {"person_id", "story_id"},
        foreignKeys = {
                @ForeignKey(entity = Person.class,
                        parentColumns = "person_id",
                        childColumns = "person_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Story.class,
                        parentColumns = {"story_id"},
                        childColumns = {"story_id"},
                        onDelete = ForeignKey.CASCADE)
        })
public class StoryRelevanceXRef {

    public StoryRelevanceXRef() { }

    public StoryRelevanceXRef(int person_id, int story_id)
    {
        personID = person_id;
        storyID = story_id;
    }
    @ColumnInfo(name = "person_id")
    public int personID;
    @ColumnInfo(name = "story_id")
    public int storyID;

}
