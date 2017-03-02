package drawapptutorial.com.example.rem;

/**
 * Created by Christian Coreil on 3/1/2017.
 */

public class RecipeStepObj {
    private String stepText;
    private int timeOfStep;
    private String microwaveHeatLevel;
    private int stoveHeatNum;

    public RecipeStepObj(){

    }

    public RecipeStepObj( String stepText, int timeOfStep){
        this.stepText = stepText;
        this.timeOfStep = timeOfStep;
    }

    public RecipeStepObj( String stepText, int timeOfStep, String microwaveHeatLevel){
        this.stepText = stepText;
        this.timeOfStep = timeOfStep;
        this.microwaveHeatLevel = microwaveHeatLevel;
    }

    public RecipeStepObj( String stepText, int timeOfStep, int stoveHeatNum){
        this.stepText = stepText;
        this.timeOfStep = timeOfStep;
        this.stoveHeatNum = stoveHeatNum;
    }

    public void setStepText(String stepText) {
        this.stepText = stepText;
    }
    public String getStepText() {return this.stepText;}

    public void setStepText(int timeOfStep) {
        this.timeOfStep = timeOfStep;
    }
    public int getTimeOfStep() {return this.timeOfStep;}

    public void setMicrowaveHeatLevel(String microwaveHeatLevel) {
        this.microwaveHeatLevel = microwaveHeatLevel;
    }
    public String getMicrowaveHeatLevel() {return this.microwaveHeatLevel;}
}
