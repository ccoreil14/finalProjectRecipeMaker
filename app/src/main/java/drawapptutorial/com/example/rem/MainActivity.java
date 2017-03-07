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

//        addRecipe = (Button) findViewById(R.id.addRecipe);

    }

    public void addNew(View v){
        Intent i = new Intent(this,AddRecipeForm.class);
        startActivity(i);
        //comment for christian
    }

    public void convCalc(View v){
        //Go to convCalc activity
        Intent i = new Intent(this, convCalc.class);
        startActivity(i);
    }


}
