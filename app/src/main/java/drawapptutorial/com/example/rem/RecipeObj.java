package drawapptutorial.com.example.rem;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Christian Coreil on 3/1/2017.
 */

public class RecipeObj {
    private int id;
    private String name;
    private String description;
    private int totalTime;
    private List<String> tags;
    private List<String> ingredients;

    public RecipeObj(){

    }

    public RecipeObj(int id, String name, String description, int totalTime, List<String> tags, List<String> ingredients){
        this.id = id;
        this.name = name;
        this.description = description;
        this.totalTime = totalTime;
        this.tags = tags;
        this.ingredients = ingredients;
    }

    public RecipeObj(String name, String description, int totalTime, List<String> tags, List<String> ingredients){
        this.name = name;
        this.description = description;
        this.totalTime = totalTime;
        this.tags = tags;
        this.ingredients = ingredients;
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

    public void setTags(List<String> tags) {this.tags = tags; }
    public List<String> getTags() {return this.tags;}
    public String getListAsString(List<String> input){
        String tagString = "";
        int i = 0;
        List<String> tagList = input;
        for (String yo: tagList) {
            if(i < tagList.size()) {
                i++;
                tagString += yo + ",";
            }else{
                tagString += yo;
            }
        }
//        for(int i =0; i< tagList.size();i++){
//            if(i< tagList.size()) {
//                tagString += tagList.f + ",";
//            }else{
//                tagString += tagList[i];
//            }
//        }

        Log.d("tagString",tagString);

        return tagString;
    }

    public void setIngredients(List<String> ingredients){ this.ingredients = ingredients;}
    public List<String> getIngredients(){
        return this.ingredients;
    }

    public String toString(){
        String result = "";
        List<String> tagToAdd = this.getTags();
        result += this.name + "\n\n"  +  "Prep Time: " + this.getTotalTime() + " mins\n\nTags: ";
        for(String t: tagToAdd){
            result += t + "     ";
        }
        return result;
    }



}
