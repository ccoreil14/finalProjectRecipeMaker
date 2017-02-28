package drawapptutorial.com.example.rem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button addRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addRecipe = (Button) findViewById(R.id.addRecipe);

    }

    public void addNew(View v){
        Intent i = new Intent(this,RecipeForm.class);
        startActivity(i);
    }

    public void convCalc(View v){
        //Go to convCalc activity
    }
}
