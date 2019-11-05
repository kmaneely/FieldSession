package com.example.lifescribev01.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "sibling_xref",
        primaryKeys = {"sibling_1_id", "sibling_2_id"},
        foreignKeys = {
                @ForeignKey(entity = Person.class,
                        parentColumns = "person_id",
                        childColumns = "sibling_1_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Person.class,
                        parentColumns = {"person_id"},
                        childColumns = {"sibling_2_id"},
                        onDelete = ForeignKey.CASCADE)
        })
public class SiblingXRef {
    @ColumnInfo(name = "sibling_1_id")
    public int sibling1ID;
    @ColumnInfo(name = "sibling_2_id")
    public int sibling2ID;
}