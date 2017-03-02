package drawapptutorial.com.example.rem;

/**
 * Created by Christian Coreil on 3/1/2017.
 */

public class RecipeObj {
    private int id;
    private String name;
    private String description;
    private int totalTime;
    private String[] tags;
    private RecipeStepObj[] stepArray;

    public RecipeObj(){

    }

    public RecipeObj(int id, String name, String description, int totalTime, String[] tags, RecipeStepObj[] stepArray){
        this.id = id;
        this.name = name;
        this.description = description;
        this.totalTime = totalTime;
        this.tags = tags;
        this.stepArray = stepArray;
    }

    public RecipeObj(String name, String description, int totalTime, String[] tags, RecipeStepObj[] stepArray){
        this.name = name;
        this.description = description;
        this.totalTime = totalTime;
        this.tags = tags;
        this.stepArray = stepArray;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {return id;}

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {return name;}

    public void setDescription(String desc) {
        this.description = desc;
    }
    public String getDescription() {return description;}

    public void setTotalTime(int totalTime) {this.totalTime = totalTime; }
    public int getTotalTime() {return totalTime;}

    public void setTags(String[] tags) {this.tags = tags; }
    public String[] getTags() {return this.tags;}

    public void setDesc(RecipeStepObj[] stepArray) {
        this.stepArray = stepArray;
    }
    public RecipeStepObj[] getStepArray() {return this.stepArray;}


}
