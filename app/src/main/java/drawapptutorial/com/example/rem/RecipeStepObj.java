package drawapptutorial.com.example.rem;

import java.util.List;

/**
 * Created by Christian Coreil on 3/1/2017.
 */

public class RecipeStepObj {
    private int id;
    private int recipeId;
    private String stepDesc;
    private int timeOfStep;
    private String heatLevel;
    private int stepOrderNumber;

    public RecipeStepObj(){

    }

    public RecipeStepObj(int id, int recipeId, String stepText, int timeOfStep, String heatLevel, int stepOrderNumber){
        this.id = id;
        this.recipeId = recipeId;
        this.stepDesc = stepText;
        this.timeOfStep = timeOfStep;
        this.heatLevel = heatLevel;
        this.stepOrderNumber = stepOrderNumber;
    }

    public RecipeStepObj(int recipeId,String stepText, int timeOfStep, String heatLevel, int stepOrderNumber){
        this.recipeId = recipeId;
        this.stepDesc = stepText;
        this.timeOfStep = timeOfStep;
        this.heatLevel = heatLevel;
        this.stepOrderNumber = stepOrderNumber;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {return this.id;}

    public void setRecipeId(int id) {
        this.recipeId = id;
    }
    public int getRecipeId() {return this.recipeId;}

    public void setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc;
    }
    public String getStepDesc() {return this.stepDesc;}

    public void setHeatLevel(String heatLevel) {
        this.heatLevel = heatLevel;
    }
    public String getHeatLevel() {return this.heatLevel;}

   public void setTimeOfStep(int timeOfStep) {
        this.timeOfStep = timeOfStep;
    }
    public int getTimeOfStep() {return this.timeOfStep;}

    public void setStepOrderNumber(int stepOrderNumber){
        this.stepOrderNumber = stepOrderNumber;
    }

    public int getStepOrderNumber(){
        return this.stepOrderNumber;
    }

    public String toString(){
        String result = "";
        result += "Step " + getStepOrderNumber() + ":\n\t" + getStepDesc();
        return result;
    }
}
