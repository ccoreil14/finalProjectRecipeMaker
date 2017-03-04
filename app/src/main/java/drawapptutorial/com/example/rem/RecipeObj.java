package drawapptutorial.com.example.rem;

import android.util.Log;

/**
 * Created by Christian Coreil on 3/1/2017.
 */

public class RecipeObj {
    private int id;
    private String name;
    private String description;
    private int totalTime;
    private String[] tags;

    public RecipeObj(){

    }

    public RecipeObj(int id, String name, String description, int totalTime, String[] tags){
        this.id = id;
        this.name = name;
        this.description = description;
        this.totalTime = totalTime;
        this.tags = tags;
    }

    public RecipeObj(String name, String description, int totalTime, String[] tags){
        this.name = name;
        this.description = description;
        this.totalTime = totalTime;
        this.tags = tags;
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
    public String getTagsAsString(){
        String tagString = "";
        String[] array = this.tags;
        for(int i =0; i< array.length;i++){
            if(i< array.length) {
                tagString += this.tags[i] + ",";
            }else{
                tagString += this.tags[i];
            }
        }

        Log.d("tagString",tagString);

        return tagString;
    }



}
