package drawapptutorial.com.example.rem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian Coreil on 3/4/2017.
 */

public class StepDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "stepList";

    private static final String TABLE_STEPS = "steps";

    private static final String KEY_ID = "id";
    private static final String KEY_RECIPE_ID = "recipe_id";
    private static final String KEY_STEP_NUMBER = "recipe_step_number";
    private static final String KEY_DESC = "step_description";
    private static final String KEY_TIMER_TIME = "step_timer_time";
    private static final String KEY_HEAT= "step_heat";


    public StepDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASK_TABLE = "CREATE TABLE " + TABLE_STEPS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_RECIPE_ID + " NUMBER,"
                + KEY_STEP_NUMBER + " NUMBER,"
                + KEY_DESC + " TEXT,"
                + KEY_TIMER_TIME + " NUMBER,"
                + KEY_HEAT + " TEXT" + ")";
        db.execSQL(CREATE_TASK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STEPS);
// Creating tables again
        onCreate(db);
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STEPS, null, null);
    }

    public void addStep(RecipeStepObj step) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RECIPE_ID, step.getRecipeId());
        values.put(KEY_STEP_NUMBER, step.getStepOrderNumber());
        values.put(KEY_DESC, step.getStepDesc());
        values.put(KEY_TIMER_TIME, step.getTimeOfStep());
        values.put(KEY_HEAT, step.getHeatLevel());

// Inserting Row
        db.insert(TABLE_STEPS, null, values);
        db.close(); // Closing database connection
    }

    public List<RecipeStepObj> getStepList(Cursor cursor){
        List<RecipeStepObj> recipeStepList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                RecipeStepObj stepObj = new RecipeStepObj();
                stepObj.setId(Integer.parseInt(cursor.getString(0)));
                stepObj.setRecipeId(Integer.parseInt(cursor.getString(1)));
                stepObj.setStepOrderNumber(Integer.parseInt(cursor.getString(2)));
                stepObj.setStepDesc(cursor.getString(3));
                stepObj.setTimeOfStep(Integer.parseInt(cursor.getString(4)));
                stepObj.setHeatLevel(cursor.getString(5));
                recipeStepList.add(stepObj);
            } while (cursor.moveToNext());
        }

        return recipeStepList;
    }

    public List<RecipeStepObj> getAllRecipes() {

        String selectQuery = "SELECT * FROM " + TABLE_STEPS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return getStepList(cursor);
    }

    public List<RecipeStepObj> getRecipeStepsFromRecipeId(int recipe_id){
        List<RecipeStepObj> taskList = new ArrayList<RecipeStepObj>();
        String selectQuery = "SELECT * FROM " + TABLE_STEPS + " WHERE "+ KEY_RECIPE_ID + " LIKE '%"+ recipe_id+"%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        return getStepList(cursor);
    }

    public int getStepCount() {
        String countQuery = "SELECT * FROM " + TABLE_STEPS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

// return count
        return cursor.getCount();
    }

    public int updateStep(RecipeStepObj step) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RECIPE_ID, step.getRecipeId());
        values.put(KEY_STEP_NUMBER, step.getStepOrderNumber());
        values.put(KEY_DESC, step.getStepDesc());
        values.put(KEY_TIMER_TIME, step.getTimeOfStep());
        values.put(KEY_HEAT, step.getHeatLevel());

// updating row
        return db.update(TABLE_STEPS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(step.getId())});
    }

    // Deleting a shop
    public void deleteSteps(RecipeStepObj step) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STEPS, KEY_ID + " = ?",
                new String[] { String.valueOf(step.getId()) });
        db.close();
    }

}
