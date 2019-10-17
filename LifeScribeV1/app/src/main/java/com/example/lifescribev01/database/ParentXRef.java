package com.example.lifescribev01.database;

import androidx.room.*;

@Entity(tableName = "parent_xref",
        primaryKeys = {"childID", "parentID"},
        foreignKeys = {
            @ForeignKey(entity = Person.class,
                        parentColumns = "personID",
                        childColumns = "childID"),
            @ForeignKey(entity = Person.class,
                        parentColumns = "personID",
                        childColumns = "parentID")
        })
public class ParentXRef {
    @ColumnInfo(name = "child_id")
    public int childID;
    @ColumnInfo(name = "parent_id")
    public int parentID;
}
