package drawapptutorial.com.example.rem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class AddRecipeForm extends AppCompatActivity implements View.OnClickListener {

    Button addRecipeBtn;
    Button addStepBtn;
    EditText addRecipeName;
    EditText addRecipeDescription;
    EditText addRecipeTotalTime;
    EditText tagEditText;
    EditText ingredientEditText;
    ListView tagListView;
    ListView ingredientsListView;
    ListView stepListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);



        addRecipeBtn = (Button) findViewById(R.id.addRecipeBtn);
        addRecipeBtn.setOnClickListener(this);

        addStepBtn = (Button) findViewById(R.id.addStepBtn);
        addStepBtn.setOnClickListener(this);


        addRecipeName = (EditText) findViewById(R.id.addRecipeName);
        addRecipeDescription = (EditText) findViewById(R.id.addRecipeDescription);
        addRecipeTotalTime = (EditText) findViewById(R.id.addRecipeTotalTime);
        tagEditText = (EditText) findViewById(R.id.tagEditText);
        ingredientEditText = (EditText) findViewById(R.id.ingredientEditText);
        tagListView = (ListView) findViewById(R.id.tagListView);
        ingredientsListView = (ListView) findViewById(R.id.ingredientsListView);
        stepListView = (ListView) findViewById(R.id.stepListView);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addRecipeBtn){
            //do stuff
            finish();
        }
    }

    public void addRecipe(){

    }

    public void addStep(){

    }

    public void addTag(){
//        tagListView tagEditText.getText().toString();
    }

    public void addIngredient(){

    }
}
