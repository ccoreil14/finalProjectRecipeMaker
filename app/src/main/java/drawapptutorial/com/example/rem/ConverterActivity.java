package drawapptutorial.com.example.rem;

import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class ConverterActivity extends AppCompatActivity {

    Button convertBtn;
    Button returnBtn;

    EditText quantityText;
    TextView outputText;

    Spinner measurementFrom;
    Spinner measurementTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        convertBtn = (Button) findViewById(R.id.convertBtn);
        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
            }
        });

        returnBtn = (Button) findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        quantityText = (EditText) findViewById(R.id.userInput);
        outputText = (TextView) findViewById(R.id.conversionOutput);

        measurementFrom = (Spinner) findViewById(R.id.fromSpinner);
        measurementTo = (Spinner) findViewById(R.id.toSpinner);

        ArrayAdapter<String> namesAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, MeasurementUnit.nameArray);
        measurementFrom.setAdapter(namesAdapter);
        measurementTo.setAdapter(namesAdapter);
    }

    public void convert() {
        double quantity;
        try {
             quantity = Double.parseDouble(quantityText.getText().toString());
        }
        catch (NumberFormatException e) {
            Log.i("ConverterActivity", "Invalid string for quantity: " + quantityText.getText());
            return;
        }

        double output = Converter.convert(
                MeasurementUnit.fromString(measurementFrom.getSelectedItem().toString()),
                MeasurementUnit.fromString(measurementTo.getSelectedItem().toString()),
                quantity
        );
        outputText.setText(String.format(Locale.US, "%.3f", output));
    }
}
