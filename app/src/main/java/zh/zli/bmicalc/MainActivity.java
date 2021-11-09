package zh.zli.bmicalc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    private int weightInKilogram = 0;
    private double heightInMeters = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.button_calculate);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText text = (EditText) findViewById(R.id.editTextDecimalHeightInMeters);
                heightInMeters = Double.parseDouble(text.getText().toString());
                EditText text1 = (EditText) findViewById(R.id.editTextNumberWeightInKilogram);
                weightInKilogram = Integer.parseInt(text1.getText().toString());
                Intent intent = new Intent(getApplication(), ResultActivity.class);
                intent.putExtra(HEIGHT, heightInMeters);
                intent.putExtra(WEIGHT, weightInKilogram);
                startActivity(intent);
            }
        });
    }
}