package drawapptutorial.com.example.rem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddRecipeForm extends AppCompatActivity implements View.OnClickListener {

    Button addRecipeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        addRecipeBtn = (Button) findViewById(R.id.addRecipeBtn);
        addRecipeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addRecipeBtn){
            //do stuff
            finish();
        }
    }
}
