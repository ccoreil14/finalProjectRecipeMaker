package drawapptutorial.com.example.rem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Christian Coreil on 3/1/2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "recipeBook";

    private static final String TABLE_TASKS= "recipes";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "recipe_name";
    private static final String KEY_DESC = "recipe_description";
    private static final String KEY_TOTAL_TIME = "recipe_total_time";
    private static final String KEY_TAGS = "recipe_tags";
    private static final String KEY_STEP_ARRAY = "recipe_step_array";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASK_TABLE = "CREATE TABLE " + TABLE_TASKS+ "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_DESC + " TEXT,"
                + KEY_TOTAL_TIME + " NUMBER,"
                + KEY_TAGS + " TEXT,"
                + KEY_STEP_ARRAY + " TEXT" + ")";
        db.execSQL(CREATE_TASK_TABLE);
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
