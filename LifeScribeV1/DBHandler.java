public class DBHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "void";
    private static final String TABLE_People = "people";
    private static final Integer KEY_Id = "id";
    private static final String KEY_Name = "name";
    private static final Date KEY_Death = "death";
    private static final Date KEY_Birth = "birth";
    private static final String KEY_Bio = "bio";
    private static final String KEY_Photo = "photo";
    public DbHandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_People + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_Name + " TEXT,"
                + KEY_Death + " DATE,"
                + KEY_Birth + " DATE"
                + KEY_Bio + " TEXT"
                + KEY_Photo + "text" + ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        // Create tables again
        onCreate(db);
    }
}