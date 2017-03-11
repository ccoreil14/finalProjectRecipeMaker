package drawapptutorial.com.example.rem;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static MainDB mainDB;
    public static StepDB stepDB;
    private List<RecipeObj> recipes;
    private ListView recipeList;
    private ArrayAdapter<RecipeObj> adapter;
    private RecipeObj chosenRecipe;
    private EditText searchBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainDB = new MainDB(this);
        stepDB = new StepDB(this);

//        addRecipe = (Button) findViewById(R.id.addRecipe);


        recipes = mainDB.getAllRecipies();
        recipeList = (ListView) findViewById(R.id.recipeList);
        adapter = new ArrayAdapter<RecipeObj>(this,R.layout.activity_listview,recipes);
        recipeList.setAdapter(adapter);

        recipeList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chosenRecipe = recipes.get(position);
                Intent i = new Intent(MainActivity.this, traditionalRecipe.class);
                i.putExtra("recipeID","" + chosenRecipe.getId());
                startActivity(i);
            }
        });

        searchBar = (EditText) findViewById(R.id.searchBar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                recipes = mainDB.getRecipesByName(searchBar.getText().toString());
                recipeList = (ListView) findViewById(R.id.recipeList);
                adapter = new ArrayAdapter<RecipeObj>( MainActivity.this ,R.layout.activity_listview,recipes);
                recipeList.setAdapter(adapter);

                recipeList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        chosenRecipe = recipes.get(position);
                        Intent i = new Intent(MainActivity.this, traditionalRecipe.class);
                        i.putExtra("recipeID","" + chosenRecipe.getId());
                        startActivity(i);
                    }
                });
            }


            @Override
            public void afterTextChanged(Editable s) {


            }
        });
    }

    @Override
    public void onResume(){
        recipes = mainDB.getAllRecipies();
        adapter = new ArrayAdapter<RecipeObj>(this,R.layout.activity_listview,recipes);
        recipeList.setAdapter(adapter);
        super.onResume();
    }

    public void addNew(View v){
        Intent i = new Intent(this,AddRecipeActivity.class);
        startActivity(i);
        //comment for christian
    }

    public void convCalc(View v){
        //Go to ConverterActivity activity
        Intent i = new Intent(this, ConverterActivity.class);
        startActivity(i);
    }

    public void viewRecipeTraditional(View v){
        Intent i = new Intent(MainActivity.this,traditionalRecipe.class);
        startActivity(i);
    }

    public JSONObject recipeToJson(RecipeObj obj) {
        JSONObject result = new JSONObject();

        try {
            result.put("name", obj.getName());
            result.put("description", obj.getDescription());
            result.put("totalTime", obj.getTotalTime());

            JSONArray tagsJson = new JSONArray();
            for (String tag : obj.getTags()) {
                tagsJson.put(tag);
            }

            JSONArray ingredientsJson = new JSONArray();
            for (String ingredient : obj.getIngredients()) {
                ingredientsJson.put(ingredient);
            }

            result.put("tags", tagsJson);
            result.put("ingredients", ingredientsJson);

            JSONArray stepsJson = new JSONArray();
            for (RecipeStepObj step : stepDB.getRecipeStepsFromRecipeId(obj.getId())) {
                JSONObject stepJson = new JSONObject();
                stepJson.put("desc", step.getStepDesc());
                stepJson.put("time", step.getTimeOfStep());
                stepJson.put("heatLevel", step.getHeatLevel());
                stepJson.put("orderNumber", step.getStepOrderNumber());

                stepsJson.put(stepJson);
            }

            result.put("steps", stepsJson);
        }
        catch (JSONException exception) {
            Log.e("RecipeObj", exception.getMessage());
        }

        return result;
    }

    public void exportRecipe(RecipeObj obj, String filename) {
        JSONObject recipeJson = recipeToJson(obj);

        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(directory, filename + ".json");
        FileWriter writer = null;

        Log.i("MainActivity", file.getAbsolutePath());

        try {
            directory.mkdirs();
            writer = new FileWriter(file);
            writer.append(recipeJson.toString());
            writer.close();
        }
        catch (IOException exception) {
            Log.e("RecipeObj", exception.getMessage());
        }
        finally {
            try {
                writer.close();
            }
            catch (IOException | NullPointerException e) { }
        }
    }

    private void loadImportedRecipe(String string) {
        try {
            JSONObject recipeJson = new JSONObject(string);

            String name = recipeJson.optString("name");
            String description = recipeJson.optString("description");
            int totalTime = recipeJson.optInt("totalTime");

            JSONArray tagsArrayJson = recipeJson.optJSONArray("tags");
            List<String> tagsArray = new ArrayList<>();
            if (tagsArrayJson != null) {
                for (int i = 0; i < tagsArrayJson.length(); i++) {
                    tagsArray.add(tagsArrayJson.getString(i));
                }
            }

            JSONArray ingredientsArrayJson = recipeJson.optJSONArray("ingredients");
            List<String> ingredientsArray = new ArrayList<>();
            if (ingredientsArrayJson != null) {
                for (int i = 0; i < ingredientsArrayJson.length(); i++) {
                    ingredientsArray.add(ingredientsArrayJson.getString(i));
                }
            }

            long recipeId = mainDB.addRecipe(new RecipeObj(name, description, totalTime, tagsArray, ingredientsArray));
            loadImportedRecipeSteps(recipeId, recipeJson.getJSONArray("steps"));
        }
        catch (JSONException e) {
            Log.e("RecipeObj", "Invalid format when importing recipe");
        }
    }

    private void loadImportedRecipeSteps(long recipeId, JSONArray stepsJson) {
        for (int i = 0; i < stepsJson.length(); i++) {
            try {
                JSONObject stepJson = stepsJson.getJSONObject(i);
                String desc = stepJson.optString("desc");
                int time = stepJson.optInt("time");
                String heatLevel = stepJson.optString("heatLevel");
                int orderNumber = stepJson.optInt("orderNumber");

                RecipeStepObj step = new RecipeStepObj((int)recipeId, desc, time, heatLevel, orderNumber);
                stepDB.addStep(step);
            }
            catch (JSONException e) {
                Log.e("RecipeObj", "Invalid format when importing recipeStep");
            }
        }
    }

    public void importRecipe(File file) {
        BufferedReader reader = null;
        StringBuilder text = new StringBuilder();

        try {
            reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            reader.close();
            loadImportedRecipe(text.toString());
        }
        catch (IOException exception) {
            Log.e("RecipeObj", exception.getMessage());
        }
        finally {
            try {
                reader.close();
            }
            catch (IOException | NullPointerException e) {
            }
        }
    }


}
