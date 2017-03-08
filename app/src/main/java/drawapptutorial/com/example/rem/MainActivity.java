package drawapptutorial.com.example.rem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static MainDB mainDB;
    public static StepDB stepDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainDB = new MainDB(this);
        stepDB = new StepDB(this);
        addHardCodedItemsToDatabase();
//        addRecipe = (Button) findViewById(R.id.addRecipe);

    }

    public void addNew(View v){
        Intent i = new Intent(this,AddRecipeForm.class);
        startActivity(i);
        //comment for christian
    }

    public void convCalc(View v){
        //Go to ConverterActivity activity
        Intent i = new Intent(this, ConverterActivity.class);
        startActivity(i);
    }

    public void addHardCodedItemsToDatabase(){
// String name, String description, int totalTime, List<String> tags, List<String> ingredients

        String name = "Oven Pizza";
        String desc = "This is a pizza that is cook in the oven.";
        int totalTime = 40;
        List<String> tags = new ArrayList<String>();
        tags.add("italian");
        tags.add("bread");
        tags.add("beginner");
        List<String> ingredients = new ArrayList<String>();
        ingredients.add("Pizza Dough");
        ingredients.add("Tomato Sauce");
        ingredients.add("Shredded Cheese");
        ingredients.add("Toppings");
        RecipeObj recipe1 = new RecipeObj(name, desc, totalTime, tags, ingredients);
        mainDB.addRecipe(recipe1);



        int step1RecipeId = mainDB.getLastRecipeId();
        String step1Desc = "Take the dough out the package";
        int step1Minutes = 10;
        String step1Heat = "None";
        int step1StepNum = 1;

        RecipeStepObj step1Rep1 = new RecipeStepObj(step1RecipeId, step1Desc, step1Minutes, step1Heat, step1StepNum);
        stepDB.addStep(step1Rep1);

        int step2RecipeId = mainDB.getLastRecipeId();
        String step2Desc = "Put stuff on";
        int step2Minutes = 5;
        String step2Heat = "None";
        int step2StepNum = 2;

        RecipeStepObj step2Rep1 = new RecipeStepObj(step2RecipeId, step2Desc, step2Minutes, step2Heat, step2StepNum);
        stepDB.addStep(step2Rep1);


        int step3RecipeId = mainDB.getLastRecipeId();
        String step3Desc = "Cook it then enjoy";
        int step3Minutes = 30;
        String step3Heat = "400 degrees";
        int step3StepNum = 3;

        RecipeStepObj step3Rep1 = new RecipeStepObj(step3RecipeId, step3Desc, step3Minutes, step3Heat, step3StepNum);
        stepDB.addStep(step3Rep1);


        String name2 = "Awesome Pizza";
        String desc2 = "This is a pizza that is cook in the oven.";
        int totalTime2 = 40;
        List<String> tags2 = new ArrayList<String>();
        tags2.add("Awesome");
        tags2.add("bread");
        tags2.add("beginner");
        List<String> ingredients2 = new ArrayList<String>();
        ingredients2.add("Pizza Dough");
        ingredients2.add("Tomato Sauce");
        ingredients2.add("Shredded Cheese");
        ingredients2.add("Toppings");
        RecipeObj recipe2 = new RecipeObj(name2, desc2, totalTime2, tags2, ingredients2);
        mainDB.addRecipe(recipe2);



        int step4RecipeId = mainDB.getLastRecipeId();
        String step4Desc = "Take the dough out the package";
        int step4Minutes = 10;
        String step4Heat = "None";
        int step4StepNum = 1;

        RecipeStepObj step4Rep2 = new RecipeStepObj(step4RecipeId, step4Desc, step4Minutes, step4Heat, step4StepNum);
        stepDB.addStep(step4Rep2);

        int step5RecipeId = mainDB.getLastRecipeId();
        String step5Desc = "Put stuff on";
        int step5Minutes = 5;
        String step5Heat = "None";
        int step5StepNum = 2;

        RecipeStepObj step5Rep2 = new RecipeStepObj(step5RecipeId, step5Desc, step5Minutes, step5Heat, step5StepNum);
        stepDB.addStep(step5Rep2);


        int step6RecipeId = mainDB.getLastRecipeId();
        String step6Desc = "Cook it then enjoy";
        int step6Minutes = 30;
        String step6Heat = "400 degrees";
        int step6StepNum = 3;

        RecipeStepObj step6Rep2 = new RecipeStepObj(step6RecipeId, step6Desc, step6Minutes, step6Heat, step6StepNum);
        stepDB.addStep(step6Rep2);


    }



}
