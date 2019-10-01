@Dao
public interface PeopleDao {
    @Query("SELECT * FROM user")
    List<People> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<People> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    People findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}