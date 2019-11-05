package com.example.lifescribev01.database;

import androidx.room.*;

import java.util.List;

@Dao
public interface ParentXRefDao {
    @Insert
    void insert(ParentXRef pair);

    @Query("SELECT * FROM person INNER JOIN PARENT_XREF ON person.person_id=parent_xref.parent_id WHERE parent_xref.child_id=:childID")
    List<Person> getParentsOfPerson(int childID);

    @Query("SELECT * FROM person INNER JOIN PARENT_XREF ON person.person_id=parent_xref.child_id WHERE parent_xref.parent_id=:parentID")
    List<Person> getChildrenOfPerson(int parentID);
}
