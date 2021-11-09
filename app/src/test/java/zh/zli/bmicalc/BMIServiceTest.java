package zh.zli.bmicalc;

import static org.junit.Assert.*;

import org.junit.Test;

public class BMIServiceTest {

    @Test
    public void getBmiClassification() {
        BMIService service = new BMIService();
        assertEquals(service.getBmiClassification(17), "Untergewichtig");
        assertEquals(service.getBmiClassification(20), "Normalgewichtig");
        assertEquals(service.getBmiClassification(37), "Fettleibigkeit II");
        assertEquals(service.getBmiClassification(45), "Fettleibigkeit III");
        assertEquals(service.getBmiClassification(27), "Vorfettleibigkeit");
        assertEquals(service.getBmiClassification(32), "Fettleibigkeit I");
    }
}