package com.example.lifescribev01.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Person.class, ParentXRef.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ParentXRefDao parentXRefDao();
    public abstract PersonDao personDao();

    public static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL( "CREATE TABLE IF NOT EXISTS `Person` (`person_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `birth_date` TEXT, `death_date` TEXT, `biography` TEXT, `spouse_id` INTEGER NOT NULL)");
            database.execSQL( "CREATE TABLE IF NOT EXISTS `parent_xref` (`child_id` INTEGER NOT NULL, `parent_id` INTEGER NOT NULL, PRIMARY KEY(`child_id`, `parent_id`), FOREIGN KEY(`child_id`) REFERENCES `Person`(`person_id`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`parent_id`) REFERENCES `Person`(`person_id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        }
    };
}