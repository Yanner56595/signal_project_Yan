package data_management;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alerts.Alert;
import com.alerts.AlertTypes.AlertCondition;
import com.alerts.AlertTypes.CombinedHypotensionHypoxiaAlert;
import com.data_management.PatientRecord;

import java.util.ArrayList;

public class CombinedHypotensionHypoxiaAlertTest {

    AlertCondition alertChecker;

    PatientRecord condition1 = new PatientRecord(1, 90, "SystolicPressure", 1L);
    PatientRecord condition2 = new PatientRecord(1, 92, "Saturation", 1L);

    PatientRecord condition3 = new PatientRecord(1, 89, "SystolicPressure", 2L);
    PatientRecord condition4 = new PatientRecord(1, 91, "Saturation", 2L);

    @BeforeEach
    void setUp() {
        alertChecker = new CombinedHypotensionHypoxiaAlert();
    }

    @Test
    void noDataTest() {
        // Edge case with no data
        ArrayList<PatientRecord> records1 = new ArrayList<PatientRecord>();
        assertEquals(null, alertChecker.evaluate("1", records1));
    }

    @Test
    void test1() {
        // Edge case data, no alert
        ArrayList<PatientRecord> records1 = new ArrayList<PatientRecord>();
        records1.add(condition1);
        records1.add(condition2);
        assertEquals(null, alertChecker.evaluate("1", records1));
    }

    @Test
    void test2() {
        // Edge case data, alert
        ArrayList<PatientRecord> records1 = new ArrayList<PatientRecord>();
        records1.add(condition1);
        records1.add(condition2);
        records1.add(condition3);
        records1.add(condition4);
        assertEquals(new Alert("1", "Hypotensive Hypoxemia Alert", 2L), 
                alertChecker.evaluate("1", records1));
    }

    @Test
    void test3() {
        // Only 1 condition met, no alert
        ArrayList<PatientRecord> records2 = new ArrayList<PatientRecord>();
        records2.add(condition1);
        records2.add(condition4);
        assertEquals(null, alertChecker.evaluate("1", records2));
    }

    @Test
    void test4() {
        // Only 1 condition met, no alert
        ArrayList<PatientRecord> records3 = new ArrayList<PatientRecord>();
        records3.add(condition2);
        records3.add(condition3);
        assertEquals(null, alertChecker.evaluate("1", records3));
    }
    
}
