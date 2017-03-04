package drawapptutorial.com.example.rem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Christian Coreil on 3/1/2017.
 */

public class MainDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "recipeBook";

    private static final String TABLE_RECIPES = "recipes";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "recipe_name";
    private static final String KEY_DESC = "recipe_description";
    private static final String KEY_TOTAL_TIME = "recipe_total_time";
    private static final String KEY_TAGS = "recipe_tags";


    public MainDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASK_TABLE = "CREATE TABLE " + TABLE_RECIPES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_DESC + " TEXT,"
                + KEY_TOTAL_TIME + " NUMBER,"
                + KEY_TAGS + " TEXT" + ")";
        db.execSQL(CREATE_TASK_TABLE);
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
// Creating tables again
        onCreate(db);
    }

    public void addRecipe(RecipeObj recipe) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, recipe.getName());
        values.put(KEY_DESC, recipe.getDescription());
        values.put(KEY_TOTAL_TIME, recipe.getTotalTime());
        values.put(KEY_TAGS, recipe.getTagsAsString());

// Inserting Row
        db.insert(TABLE_RECIPES, null, values);
        db.close(); // Closing database connection
    }

    public List<RecipeObj> getRecipeList(Cursor cursor){
        List<RecipeObj> recipeList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                RecipeObj recipe = new RecipeObj();
                recipe.setId(Integer.parseInt(cursor.getString(0)));
                recipe.setName(cursor.getString(1));
                recipe.setDescription(cursor.getString(2));
                recipe.setTotalTime(Integer.parseInt(cursor.getString(3)));
                recipe.setTags(Arrays.asList(cursor.getString(4).split(",")));

                recipeList.add(recipe);
            } while (cursor.moveToNext());
        }

        return recipeList;
    }

    public List<RecipeObj> getAllRecpies() {

        String selectQuery = "SELECT * FROM " + TABLE_RECIPES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return getRecipeList(cursor);
    }

    public List<RecipeObj> getRecipesAlphabetized(){
        List<RecipeObj> taskList = new ArrayList<RecipeObj>();
        String selectQuery = "SELECT * FROM " + TABLE_RECIPES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_RECIPES, null, null, null, null, null, "recipe_name");

        return getRecipeList(cursor);
    }

    public int getRecipeCount() {
        String countQuery = "SELECT * FROM " + TABLE_RECIPES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

// return count
        return cursor.getCount();
    }

    public int updateRecipe(RecipeObj recipe) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, recipe.getName());
        values.put(KEY_DESC, recipe.getDescription());
        values.put(KEY_TOTAL_TIME, recipe.getTotalTime());
        values.put(KEY_TAGS, recipe.getTagsAsString());

// updating row
        return db.update(TABLE_RECIPES, values, KEY_ID + " = ?",
                new String[]{String.valueOf(recipe.getId())});
    }

    // Deleting a shop
    public void deleteRecipe(RecipeObj recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECIPES, KEY_ID + " = ?",
                new String[] { String.valueOf(recipe.getId()) });
        db.close();
    }


}
