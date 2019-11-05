package com.example.lifescribev01.database;

import androidx.room.*;

@Entity(tableName = "parent_xref",
        primaryKeys = {"child_id", "parent_id"},
        foreignKeys = {
            @ForeignKey(entity = Person.class,
                        parentColumns = "person_id",
                        childColumns = "child_id",
                        onDelete = ForeignKey.CASCADE),
            @ForeignKey(entity = Person.class,
                        parentColumns = {"person_id"},
                        childColumns = {"parent_id"},
                        onDelete = ForeignKey.CASCADE)
        })
public class ParentXRef {

    public ParentXRef(int child_id, int parent_id)
    {
        childID = child_id;
        parentID = parent_id;
    }
    @ColumnInfo(name = "child_id")
    public int childID;
    @ColumnInfo(name = "parent_id")
    public int parentID;
}
