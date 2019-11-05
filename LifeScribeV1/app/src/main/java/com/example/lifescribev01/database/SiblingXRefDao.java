package com.example.lifescribev01.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SiblingXRefDao {
    @Insert
    void insert(SiblingXRef pair);

    @Query("SELECT * FROM person INNER JOIN SIBLING_XREF ON person.person_id = sibling_xref.sibling_2_id WHERE sibling_xref.sibling_1_id=:siblingID")
    List<Person> getSiblingOfPerson(int siblingID);
}
