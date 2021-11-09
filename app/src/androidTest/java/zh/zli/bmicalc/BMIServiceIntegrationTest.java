package zh.zli.bmicalc;

import static org.junit.Assert.assertEquals;

import android.content.Intent;
import android.os.IBinder;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.rule.ServiceTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

@RunWith(AndroidJUnit4.class)
public class BMIServiceIntegrationTest {

    @Rule
    public final ServiceTestRule serviceTestRule = new ServiceTestRule();

    @Test
    public void calcBMIScore() throws TimeoutException {
        Intent serviceIntent = new Intent(ApplicationProvider.getApplicationContext(), BMIService.class);
        IBinder serviceBinder = serviceTestRule.bindService(serviceIntent);
        BMIService bmiService = ((BMIService.BMIBinder) serviceBinder).getService();
        assertEquals(bmiService.calcBMIScore(80, 2), 20, 0.1);
        assertEquals(bmiService.calcBMIScore(70, 1.752), 22.8, 0.1);
    }

    @Test
    public void testBMICalculation() {
        BMIService bmiService = new BMIService();

        assertEquals(24.69135, bmiService.calcBMIScore(80, 1.80), 0.00001);
    }
}