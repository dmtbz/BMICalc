package zh.zli.bmicalc;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BMIService extends Service {
    // Binder given to clients
    private final IBinder binder = new BMIBinder();

    public BMIService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public double calcBMIScore(int weight, double height) {
        return weight / (height * height);
    }

    public String getBmiClassification(double bmi_score) {
        String classification = "";
        if (bmi_score < 18.5) {
            classification = "Untergewichtig";
        } else if (bmi_score >= 18.5 && bmi_score <= 24.9) {
            classification = "Normalgewichtig";
        } else if (bmi_score >= 25 && bmi_score <= 29.9) {
            classification = "Vorfettleibigkeit";
        } else if (bmi_score >= 30 && bmi_score <= 34.9) {
            classification = "Fettleibigkeit I";
        } else if (bmi_score >= 35 && bmi_score <= 39.9) {
            classification = "Fettleibigkeit II";
        } else if (bmi_score > 40) {
            classification = "Fettleibigkeit II";
        }
        return classification;
    }

    public class BMIBinder extends Binder {
        BMIService getService() {
            return BMIService.this;
        }
    }
}