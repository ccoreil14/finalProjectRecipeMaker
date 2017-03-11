package drawapptutorial.com.example.rem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class traditionalRecipe extends AppCompatActivity {
    TextView recipeNameText;
    TextView tagsText;
    TextView prepTimeText;
    TextView ingredientsText;
    ListView stepListView;
    List<RecipeStepObj> stepsToShow;
    ArrayAdapter<RecipeStepObj> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe_traditoinal);
        String chosenRecipeID = getIntent().getStringExtra("recipeID");

        RecipeObj recipeToShow = MainActivity.mainDB.getRecipe(Integer.parseInt(chosenRecipeID));

        recipeNameText = (TextView) findViewById(R.id.recipeNameText);
        recipeNameText.setText(recipeToShow.getName());
        prepTimeText = (TextView) findViewById(R.id.prepTimeText);
        prepTimeText.setText("Total Prep Time: " + recipeToShow.getTotalTime());
        stepListView = (ListView) findViewById(R.id.stepListView);

        ingredientsText = (TextView) findViewById(R.id.ingredientsText);
        List<String> ingredientsToShow = recipeToShow.getIngredients();
        String ingredientFormat = "";
        for(int i = 0;i < ingredientsToShow.size(); i++){
            ingredientFormat += ingredientsToShow.get(i) + "\n";
        }
        ingredientsText.setText("Ingredients required:\n" + ingredientFormat);

        tagsText = (TextView) findViewById(R.id.tagsText);
        List<String> tagsToShow = recipeToShow.getTags();
        String tagsFormat = "";
        for(int i = 0;i < tagsToShow.size(); i++){
            tagsFormat += tagsToShow.get(i) + " ";
        }
        tagsText.setText("Tags: " + tagsFormat);


        stepsToShow = MainActivity.stepDB.getRecipeStepsFromRecipeId(Integer.parseInt(chosenRecipeID));
        adapter = new ArrayAdapter<RecipeStepObj>(this,R.layout.activity_listview,stepsToShow);
        stepListView.setAdapter(adapter);
        }
}
