@Database(entities = {People.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PeopleDao peopleDao();
}