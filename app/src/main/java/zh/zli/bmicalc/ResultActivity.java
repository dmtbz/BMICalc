package zh.zli.bmicalc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    BMIService mService;
    boolean mBound = false;
    private int weightInKilogram = 0;
    private double heightInMeters = 0;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BMIService.BMIBinder binder = (BMIService.BMIBinder) service;
            mService = binder.getService();
            mBound = true;
            if (mBound) {
                // Call a method from the LocalService.
                // However, if this call were something that might hang, then this request should
                // occur in a separate thread to avoid slowing down the activity performance.
                double bmiScore = mService.calcBMIScore(weightInKilogram, heightInMeters);
                String bmiClassification = mService.getBmiClassification(bmiScore);
                TextView textView = (TextView) findViewById(R.id.bmi_value);
                textView.setText(String.valueOf(bmiScore));
                TextView textView1 = (TextView) findViewById(R.id.classification_value);
                textView1.setText(String.valueOf(bmiClassification));
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        heightInMeters = intent.getDoubleExtra(MainActivity.HEIGHT, 0);
        weightInKilogram = intent.getIntExtra(MainActivity.WEIGHT, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, BMIService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
}