package drawapptutorial.com.example.rem;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button addRecipeBtn;
    private Button addStepBtn;
    private Button addTagBtn;
    private Button addIngredientBtn;
    private EditText addRecipeName;
    private EditText addRecipeDescription;
    private EditText addRecipeTotalTime;
    private EditText tagEditText;
    private EditText ingredientEditText;
    private ListView tagListView;
    private List<String> tags;
    private ListView ingredientsListView;
    private List<String> ingredients;
    private ListView stepListView;
    private Spinner attributeType;
    private ArrayAdapter<String> tagAdapter;
    private ArrayAdapter<String> ingredientAdapter;
    private ArrayAdapter<RecipeStepObj> stepAdapter;


//  for Dialog Related Stuff

    private Dialog stepDescription;
    private Dialog stepTimer;
    private Dialog stepOven;
    private Dialog stepMicrowave;

    private Button addStepBtnDialog;
    private EditText addStepDescDialog;
    private EditText addStepTimerDialog;
    private EditText addStepHeatOvenDialog;
    private Spinner addStepHeatMicrowaveDialog;
    //  for Dialog Related Stuff

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

        attributeType = (Spinner) findViewById(R.id.attributeType);

        tags = new ArrayList<String>();
        ingredients = new ArrayList<String>();

        stepDescription = new Dialog(this);
        stepDescription.setContentView(R.layout.add_desc_layout);
        stepTimer = new Dialog(this);
        stepTimer.setContentView(R.layout.add_timer_layout);
        stepOven = new Dialog(this);
        stepOven.setContentView(R.layout.add_oven_layout);
        stepMicrowave = new Dialog(this);
        stepMicrowave.setContentView(R.layout.add_microwave_layout);
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
        }else if(v.getId() == R.id.addStepBtn){
            int count = stepListView.getChildCount();
            addStep(count+1);
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

    public void addStep(final int stepOrderNum){
        final String attTyp = attributeType.getSelectedItem().toString();

        switch (attTyp) {
            case "Details":
                stepDescription.show();
                addStepBtnDialog= (Button) stepDescription.findViewById(R.id.addStepBtnYo);
                addStepDescDialog = (EditText) stepDescription.findViewById(R.id.addStepDesc);
                break;

            case "Timer":
                stepTimer.show();
                addStepBtnDialog= (Button) stepTimer.findViewById(R.id.addStepBtnYo);
                addStepDescDialog = (EditText) stepTimer.findViewById(R.id.addStepDesc);
                addStepTimerDialog = (EditText) stepTimer.findViewById(R.id.addTimerNum);
                break;

            case "Oven":
                stepOven.show();
                addStepBtnDialog= (Button) stepOven.findViewById(R.id.addStepBtnYo);
                addStepDescDialog= (EditText) stepOven.findViewById(R.id.addStepDesc);
                addStepTimerDialog = (EditText) stepOven.findViewById(R.id.addTimerNum);
                addStepHeatOvenDialog = (EditText) stepOven.findViewById(R.id.ovenHeat);
                break;

            case "Microwave":
                stepMicrowave.show();
                addStepBtnDialog= (Button) stepMicrowave.findViewById(R.id.addStepBtnYo);
                addStepDescDialog = (EditText) stepMicrowave.findViewById(R.id.addStepDesc);
                addStepTimerDialog = (EditText) stepMicrowave.findViewById(R.id.addTimerNum);
                addStepHeatMicrowaveDialog = (Spinner) stepMicrowave.findViewById(R.id.heatSpinner);
                break;

            default:
                Log.d("Error", "WHAT?!");
                break;
        }

        addStepBtnDialog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addStepToDB(attTyp,stepOrderNum);
            }
        });

    }

    public void addStepToDB(String attType, int stepOrderNum){
        String stepDesc = "";
        int timerNum = 0;
        String heat = "";

        switch (attType) {
            case "Details":
                stepDesc = addStepDescDialog.getText().toString();
                stepDescription.cancel();
                break;

            case "Timer":
                stepDesc = addStepDescDialog.getText().toString();
                timerNum = Integer.parseInt(addStepDescDialog.getText().toString());
                stepTimer.cancel();
                break;

            case "Oven":
                stepDesc = addStepDescDialog.getText().toString();
                timerNum = Integer.parseInt(addStepDescDialog.getText().toString());
                heat = addStepHeatOvenDialog.getText().toString();
                stepOven.cancel();
                break;

            case "Microwave":
                stepDesc = addStepDescDialog.getText().toString();
                timerNum = Integer.parseInt(addStepDescDialog.getText().toString());
                heat = addStepHeatMicrowaveDialog.getSelectedItem().toString();
                stepMicrowave.cancel();
                break;

            default:
                Log.d("Error", "WHAT?!");
                break;
        }

        int recipeId = MainActivity.mainDB.getRecipeCount() + 1;
        RecipeStepObj step = new RecipeStepObj( recipeId , stepDesc, timerNum, heat, stepOrderNum);
        MainActivity.stepDB.addStep(step);

//        List<RecipeStepObj> steps = new ArrayList<RecipeStepObj>();
//        stepAdapter = new Adapter<RecipeStepObj>(this,R.layout.activity_listview,steps);
//        stepsToListView();
    }

    public void stepsToListView(){

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
