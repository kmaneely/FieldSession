package com.example.lifescribev01.database;

import androidx.room.*;

import java.util.List;

public interface ParentXRefDao {
    @Insert
    void insert(ParentXRef pair);

    @Query("SELECT * FROM person INNER JOIN PARENT_XREF ON person.personID=parent_xref.parent_id WHERE parent_xref.child_id=:childID")
    List<Person> getParentsOfPerson(Person childID);
}
