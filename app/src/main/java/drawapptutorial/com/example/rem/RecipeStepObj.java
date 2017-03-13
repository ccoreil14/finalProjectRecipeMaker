package drawapptutorial.com.example.rem;

import android.util.Log;

import java.util.List;

/**
 * Created by Christian Coreil on 3/1/2017.
 */

public class RecipeStepObj {
    private int id;
    private long recipeId;
    private String stepDesc;
    private int timeOfStep;
    private String heatLevel;
    private int stepOrderNumber;
    private String attType;

    public RecipeStepObj(){

    }

    public RecipeStepObj(int id, int recipeId, String stepText, int timeOfStep, String heatLevel, int stepOrderNumber, String attType){
        this.id = id;
        this.recipeId = recipeId;
        this.stepDesc = stepText;
        this.timeOfStep = timeOfStep;
        this.heatLevel = heatLevel;
        this.stepOrderNumber = stepOrderNumber;
        this.attType = attType;
    }

    public RecipeStepObj(int recipeId,String stepText, int timeOfStep, String heatLevel, int stepOrderNumber, String attType){
        this.recipeId = recipeId;
        this.stepDesc = stepText;
        this.timeOfStep = timeOfStep;
        this.heatLevel = heatLevel;
        this.stepOrderNumber = stepOrderNumber;
        this.attType = attType;
    }

    public RecipeStepObj(int recipeId,String stepText, int timeOfStep, String heatLevel, int stepOrderNumber){
        this.recipeId = recipeId;
        this.stepDesc = stepText;
        this.timeOfStep = timeOfStep;
        this.heatLevel = heatLevel;
        this.stepOrderNumber = stepOrderNumber;
        //this.attType = attType;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {return this.id;}

    public void setRecipeId(long id) {
        this.recipeId = id;
    }
    public long getRecipeId() {return this.recipeId;}

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

    public void setStepOrderNumber(int stepOrderNumber){ this.stepOrderNumber = stepOrderNumber; }
    public int getStepOrderNumber(){
        return this.stepOrderNumber;
    }

    public void setAttType(String attType){ this.attType = attType;}
    public String getAttType() { return this.attType; }

    public String toString(){
        String result = "";
        switch(getAttType()){
            case "Details":
                result += "Step " + getStepOrderNumber() + ":\n\t" + getStepDesc();
                break;

            case "Timer":
                result += "Step " + getStepOrderNumber() + ":\n\t" + getStepDesc() + "\n\tThis task should take " + getTimeOfStep() + " mins";
                break;

            case "Oven":
                result += "Step " + getStepOrderNumber() + ":\n\t" + getStepDesc() + "\n\tPreheat oven to " + getHeatLevel() + "°F" + " and leave in oven for " + getTimeOfStep() + " mins";
                break;

            case "Microwave":
                result += "Step " + getStepOrderNumber() + ":\n\t" + getStepDesc() + "\n\tPut in microwave at " + getHeatLevel() + " for " + getTimeOfStep() + " mins";
                break;

            default:
                Log.d("Error", "WHAT?!");
                break;
        }

        return result;
    }
}
