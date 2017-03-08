package drawapptutorial.com.example.rem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeForm extends AppCompatActivity implements View.OnClickListener {

    Button addRecipeBtn;
    Button addStepBtn;
    Button addTagBtn;
    Button addIngredientBtn;
    EditText addRecipeName;
    EditText addRecipeDescription;
    EditText addRecipeTotalTime;
    EditText tagEditText;
    EditText ingredientEditText;
    ListView tagListView;
    List<String> tags;
    ListView ingredientsListView;
    List<String> ingredients;
    ListView stepListView;
    private ArrayAdapter<String> tagAdapter;
    private ArrayAdapter<String> ingredientAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        addRecipeBtn = (Button) findViewById(R.id.addRecipeBtn);
        addRecipeBtn.setOnClickListener(this);

        addStepBtn = (Button) findViewById(R.id.addStepBtn);
        addStepBtn.setOnClickListener(this);

        addTagBtn = (Button) findViewById(R.id.addTagBtn);
        addTagBtn.setOnClickListener(this);

        addIngredientBtn = (Button) findViewById(R.id.addIngredientBtn);
        addIngredientBtn.setOnClickListener(this);


        addRecipeName = (EditText) findViewById(R.id.addRecipeName);
        addRecipeDescription = (EditText) findViewById(R.id.addRecipeDescription);
        addRecipeTotalTime = (EditText) findViewById(R.id.addRecipeTotalTime);
        tagEditText = (EditText) findViewById(R.id.tagEditText);
        ingredientEditText = (EditText) findViewById(R.id.ingredientEditText);
        tagListView = (ListView) findViewById(R.id.tagListView);
        ingredientsListView = (ListView) findViewById(R.id.ingredientsListView);
        stepListView = (ListView) findViewById(R.id.stepListView);

        tags = new ArrayList<String>();
        ingredients = new ArrayList<String>();

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addRecipeBtn){
            addRecipe();
            finish();
        }else if(v.getId() == R.id.addTagBtn){
            addTag();
        }else if(v.getId() == R.id.addIngredientBtn){
            addIngredient();
        }
    }

    public void addRecipe(){
        String name = addRecipeName.getText().toString();
        String desc = addRecipeDescription.getText().toString();
        int totalTime = Integer.parseInt(addRecipeTotalTime.getText().toString());
        List<String> tagList = getListFromListview(tagListView);
        List<String> ingredientList = getListFromListview(ingredientsListView);

        RecipeObj newRecipe = new RecipeObj(name, desc, totalTime, tagList, ingredientList);
        MainActivity.mainDB.addRecipe(newRecipe);
    }

    public void addStep(){

    }

    public void addTag(){
        String yo = tagEditText.getText().toString();
        tags.add(yo);
        tagEditText.setText("");
        tagAdapter = new ArrayAdapter<String>(this,R.layout.activity_listview,tags);
        tagListView.setAdapter(tagAdapter);
    }

    public void addIngredient(){
        ingredients.add(ingredientEditText.getText().toString());
        ingredientEditText.setText("");
        ingredientAdapter = new ArrayAdapter<String>(this,R.layout.activity_listview,ingredients);
        ingredientsListView.setAdapter(ingredientAdapter);
    }

    public List<String> getListFromListview(ListView listView){
        List<String> list = new ArrayList<String>();
        int size = listView.getAdapter().getCount();
        for(int i = 0; i < size; i++){
            String po = (String) listView.getAdapter().getItem(i);
            list.add(po);
        }

        return list;
    }
}
